package org.chad.bytecinema.controller;

import lombok.RequiredArgsConstructor;
import org.chad.bytecinema.service.LoginService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bytecinema/login/")
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;
}
