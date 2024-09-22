package org.chad.bytecinema.controller;

import lombok.RequiredArgsConstructor;
import org.chad.bytecinema.domain.dto.LoginDTO;
import org.chad.bytecinema.domain.dto.RegisterDTO;
import org.chad.bytecinema.domain.entity.Result;
import org.chad.bytecinema.service.LoginService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bytecinema/login/")
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    /**
     * 获取图像验证码
     * @return 图像验证码对应的uuid
     */
    @GetMapping("captcha")
    public Result createCaptcha(){
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

    @PostMapping
    public Result login(@RequestBody LoginDTO loginDTO){
        return Result.success(loginService.login(loginDTO));
    }

    @PostMapping("refresh")
    public Result refresh(@RequestHeader String refreshToken){
        return Result.success(loginService.refresh(refreshToken));
    }
}
