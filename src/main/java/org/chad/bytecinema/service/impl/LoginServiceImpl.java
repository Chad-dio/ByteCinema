package org.chad.bytecinema.service.impl;

import org.chad.bytecinema.domain.po.User;
import org.chad.bytecinema.service.LoginService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class LoginServiceImpl implements LoginService {
    @Override
    public User login(User user) {
        return null;
    }

    @Override
    public Boolean checkCode(String email, Integer code) {
        return null;
    }

    @Override
    public void captcha(String uuid, HttpServletResponse response) throws IOException {

    }
}
