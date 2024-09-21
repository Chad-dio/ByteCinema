package org.chad.bytecinema.controller;

import lombok.RequiredArgsConstructor;
import org.chad.bytecinema.domain.dto.RegisterDTO;
import org.chad.bytecinema.domain.entity.Result;
import org.chad.bytecinema.domain.vo.Captcha;
import org.chad.bytecinema.service.LoginService;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("checkCaptcha")
    public Result checkCaptcha(@RequestParam String uuid, @RequestParam String code){
        loginService.checkCaptcha(uuid, code);
        return Result.success();
    }

    @GetMapping("getCode")
    private Result getCode(@RequestParam String email){
        loginService.getCode(email);
        return Result.success();
    }
    @PostMapping("register")
    public Result register(@RequestBody RegisterDTO registerDTO){
        loginService.register(registerDTO);
        return Result.success();
    }
}
