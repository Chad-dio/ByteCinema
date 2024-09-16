package org.chad.bytecinema.service.impl.user;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.chad.bytecinema.domain.po.User;
import org.chad.bytecinema.mapper.user.UserMapper;
import org.chad.bytecinema.service.user.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
