package org.chad.bytecinema.intercept;

import cn.hutool.core.util.StrUtil;
import org.chad.bytecinema.common.user.UserDTO;
import org.chad.bytecinema.common.user.UserHolder;
import org.chad.bytecinema.utils.JwtTokenUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtAuthenticationIntercept implements HandlerInterceptor {
    private JwtTokenUtil jwtTokenUtil;

    public JwtAuthenticationIntercept(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String header = request.getHeader("Authorization");
        if (StrUtil.isNotBlank(header) && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            if (jwtTokenUtil.validateToken(token)) {
                String email = jwtTokenUtil.getEmailFromToken(token);
                UserHolder.saveUser(new UserDTO(email));
                return true;
            }
            response.setHeader("accessToken", "outdated");
            response.getWriter().write("访问token过期");
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        UserHolder.removeUser();
    }
}
