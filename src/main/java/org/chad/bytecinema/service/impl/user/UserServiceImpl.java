package org.chad.bytecinema.service.impl.user;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.chad.bytecinema.common.user.UserHolder;
import org.chad.bytecinema.domain.po.User;
import org.chad.bytecinema.domain.vo.FansPageInfoVO;
import org.chad.bytecinema.mapper.user.UserMapper;
import org.chad.bytecinema.service.user.UserService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;

import static org.chad.bytecinema.common.constant.RedisConstant.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final StringRedisTemplate stringRedisTemplate;

    @Override
    public Boolean followOrUnfollow(String followId) {
        //查看该用户是否已经关注
        String email = UserHolder.getUser().getEmail();
        Double score = stringRedisTemplate.opsForZSet().score(USER_FAN + followId, email);
        if(score != null){
            //关注，取关
            stringRedisTemplate.opsForZSet().remove(USER_FAN + followId, email);
            stringRedisTemplate.opsForZSet().remove(USER_SUBSCRIBE + email, followId);
        }else{
            //未关注，关注
            stringRedisTemplate.opsForZSet().add(USER_FAN + followId, email, System.currentTimeMillis());
        }
        return true;
    }

    @Override
    public IPage<FansPageInfoVO> getFans(String userId, Integer pageNo, Integer pageSize) {
        Set<String> fans = stringRedisTemplate.opsForZSet().range(USER_FAN + userId, 0, pageSize);
        //拿到用户的所有userId后
        LambdaQueryWrapper<User> queryWrapper = Wrappers.lambdaQuery(User.class)
                .in(User::getId, fans);
        Page<User> page = page(new Page<>(pageNo, pageSize), queryWrapper);
        return page.convert(bean -> {
            FansPageInfoVO fansPageInfoVO = BeanUtil.copyProperties(bean, FansPageInfoVO.class);
            Double score = stringRedisTemplate.opsForZSet().score(USER_FAN + userId, fansPageInfoVO.getEmail());
            fansPageInfoVO.setFollowed((score == null));
            return fansPageInfoVO;
        });
    }
}
