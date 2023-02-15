package com.graduate_ok.graduate_ok.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://www.hs-graduate-ok.com", "http://hs-graduate-ok.com", "http://13.125.25.62")
                .allowedMethods("GET", "POST", "PUT", "DELETE" , "OPTIONS")
                .allowedHeaders("Origin","Accept","X-Requested-With","Content-Type","Access-Control-Request-Method","Access-Control-Request-Headers","Authorization")
                .exposedHeaders("*")
                .maxAge(3600);
    }

}