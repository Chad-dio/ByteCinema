package org.chad.bytecinema.controller;

import lombok.RequiredArgsConstructor;
import org.chad.bytecinema.domain.entity.Result;
import org.chad.bytecinema.domain.vo.Captcha;
import org.chad.bytecinema.service.LoginService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/bytecinema/login/")
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    /**
     * 获取图像验证码
     * @param
     * @return 图像验证码对应的uuid
     * @throws IOException
     */
    @GetMapping("captcha")
    public Result createCaptcha() throws IOException {
        return Result.success(loginService.createCaptcha());
    }
    @GetMapping("getCode")
    private Result getCode(@RequestParam String email){
        loginService.getCode(email);
        return Result.success();
    }
}
