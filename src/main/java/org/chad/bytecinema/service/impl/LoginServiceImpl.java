package org.chad.bytecinema.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.extra.mail.MailUtil;
import lombok.RequiredArgsConstructor;
import org.chad.bytecinema.domain.vo.Captcha;
import org.chad.bytecinema.service.LoginService;
import org.chad.bytecinema.utils.RandomGenerator;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.chad.bytecinema.common.constant.RedisConstant.*;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final StringRedisTemplate stringRedisTemplate;

    @Override
    public Captcha createCaptcha() throws IOException {
        // 定义图形验证码的长和宽
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100);
        //将验证码储存到redis中，加上TTL
        String uuid = UUID.randomUUID().toString();
        stringRedisTemplate.opsForValue().set(LOGIN_CAPTCHA + uuid, lineCaptcha.getCode(), LOGIN_CAPTCHA_TTL, TimeUnit.MINUTES);
        return new Captcha(uuid, lineCaptcha.getImageBase64());
    }

    @Override
    public void getCode(String email) {
        int in = email.indexOf('@');
        if(in == -1){
            throw new RuntimeException("邮箱地址不合法");
        }

        long currentTimeMillis = System.currentTimeMillis();
        long start = currentTimeMillis - LOGIN_EMAIL_WINDOW;
        Long count = stringRedisTemplate.opsForZSet().count(LOGIN_EMAIL + email, start, currentTimeMillis);
        if(count != null && count > 2){
            throw new RuntimeException("操作过于频繁，请稍后再试");
        }

        String code = RandomGenerator.generateRandom(6);
        MailUtil.send(email, "注册验证码", code, false);
        stringRedisTemplate.opsForValue().set(LOGIN_CODE + email, code, LOGIN_CODE_TTL, TimeUnit.MINUTES);
        stringRedisTemplate.opsForZSet().add(LOGIN_EMAIL + email, email, currentTimeMillis);
    }
}
