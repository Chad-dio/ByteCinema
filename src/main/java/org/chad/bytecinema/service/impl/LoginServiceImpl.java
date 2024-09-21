package org.chad.bytecinema.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.extra.mail.MailUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.chad.bytecinema.domain.dto.RegisterDTO;
import org.chad.bytecinema.domain.po.User;
import org.chad.bytecinema.domain.vo.Captcha;
import org.chad.bytecinema.service.LoginService;
import org.chad.bytecinema.service.user.UserService;
import org.chad.bytecinema.utils.RandomGenerator;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.chad.bytecinema.common.constant.RedisConstant.*;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final StringRedisTemplate stringRedisTemplate;

    private final UserService userService;

    @Override
    public Captcha createCaptcha(){
        // 定义图形验证码的长和宽
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100);
        //将验证码储存到redis中，加上TTL
        String uuid = UUID.randomUUID().toString();
        stringRedisTemplate.opsForValue().set(LOGIN_CAPTCHA + uuid,
                lineCaptcha.getCode(), LOGIN_CAPTCHA_TTL, TimeUnit.MINUTES);
        return new Captcha(uuid, lineCaptcha.getImageBase64());
    }

    @Override
    public void checkCaptcha(String uuid, String code) {
        String s = stringRedisTemplate.opsForValue().get(uuid);
        if(s == null || !s.equalsIgnoreCase(code)){
            throw new RuntimeException("图形验证码错误");
        }
    }

    @Override
    public void getCode(String email) {
        //0.合法性检验，虽然前端会检验邮箱合法性，但后端最好还是也做一些保底的检验
        //TODO:更多的校验步骤
        int in = email.indexOf('@');
        if(in == -1){
            throw new RuntimeException("邮箱地址不合法");
        }
        //1.获取当前的时间窗口
        long currentTimeMillis = System.currentTimeMillis();
        long start = currentTimeMillis - LOGIN_EMAIL_WINDOW;
        //2.执行限流操作前，检查用户是否达到了限制条件
        Long count = stringRedisTemplate.opsForZSet().count(
                LOGIN_EMAIL + email,
                start, currentTimeMillis);//时间窗口里面的操作次数
        if(count != null && count > 2){
            //3.达到限流条件，进行限制（deny user）
            throw new RuntimeException("操作过于频繁，请稍后再试");
        }
        //4.未达到，执行操作
        String code = RandomGenerator.generateRandom(6);
        MailUtil.send(email, "注册验证码", code, false);
        stringRedisTemplate.opsForValue().set(LOGIN_CODE + email, code, LOGIN_CODE_TTL, TimeUnit.MINUTES);
        //5.记录此次操作
        stringRedisTemplate.opsForZSet().add(LOGIN_EMAIL + email, email, currentTimeMillis);
    }

    @Override
    public void register(RegisterDTO registerDTO) {
        String email = registerDTO.getEmail();
        LambdaQueryWrapper<User> queryWrapper = Wrappers.lambdaQuery(User.class)
                .eq(User::getEmail, email)
                .eq(User::getDelFlag, 1);
        User user = userService.getOne(queryWrapper);
        if(BeanUtil.isNotEmpty(user)){
            throw new RuntimeException("改邮箱已经注册过了。。。");
        }
        String code = registerDTO.getCode();
        if(code == null || !stringRedisTemplate.opsForValue().get(LOGIN_CODE + email).equalsIgnoreCase(code)){
            throw new RuntimeException("验证码异常");
        }
        user.setUsername(registerDTO.getUsername());
        user.setEmail(email);
        user.setPassword(registerDTO.getPassword());
        userService.save(user);

        //TODO:收藏夹的初始化
    }
}
