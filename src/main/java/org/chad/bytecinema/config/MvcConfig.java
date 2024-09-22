package org.chad.bytecinema.config;

import lombok.RequiredArgsConstructor;
import org.chad.bytecinema.intercept.JwtAuthenticationIntercept;
import org.chad.bytecinema.utils.JwtTokenUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JwtAuthenticationIntercept(new JwtTokenUtil()))
                .excludePathPatterns(
                    "/bytecinema/login/**"
                ).order(1);
    }
}