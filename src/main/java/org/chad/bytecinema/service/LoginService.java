package org.chad.bytecinema.service;


import org.chad.bytecinema.domain.vo.Captcha;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface LoginService {
    Captcha createCaptcha() throws IOException;

    void getCode(String email);
}
