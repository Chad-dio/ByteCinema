package org.chad.bytecinema.service;


import org.chad.bytecinema.domain.dto.LoginDTO;
import org.chad.bytecinema.domain.dto.RegisterDTO;
import org.chad.bytecinema.domain.vo.Captcha;
import org.chad.bytecinema.domain.vo.LoginVO;

public interface LoginService {
    Captcha createCaptcha();

    void getCode(String email);

    void register(RegisterDTO registerDTO);

    void checkCaptcha(String uuid, String code);

    LoginVO login(LoginDTO loginDTO);

    LoginVO refresh(String refreshToken);
}
