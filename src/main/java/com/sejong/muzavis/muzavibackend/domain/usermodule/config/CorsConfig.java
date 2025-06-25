package com.sejong.muzavis.muzavibackend.domain.usermodule.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class CorsConfig implements WebMvcConfigurer {

    //@Value("${app.cors.allowed-origins}")
    private String[] allowedOrigins;

    // 하드코딩 예시 – application.yml 없어도 OK
    private static final String[] ALLOWED = {
            "http://localhost:5173",
            "http://localhost:3000"
    };

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")               // 모든 API
                .allowedOrigins(ALLOWED)  // 위 프로퍼티
                .allowedMethods("GET","POST","PUT","DELETE","PATCH","OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true)          // 쿠키·세션 쓸 때
                .maxAge(3600);                   // pre-flight 캐시(1h)
    }
}
