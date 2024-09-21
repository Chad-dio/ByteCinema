package org.chad.bytecinema.service;


import org.chad.bytecinema.domain.dto.RegisterDTO;
import org.chad.bytecinema.domain.vo.Captcha;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface LoginService {
    Captcha createCaptcha();

    void getCode(String email);

    void register(RegisterDTO registerDTO);

    void checkCaptcha(String uuid, String code);
}
