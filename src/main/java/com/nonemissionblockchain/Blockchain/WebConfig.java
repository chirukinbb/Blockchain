package com.nonemissionblockchain.Blockchain;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*") // Разрешаем запросы из всех источников
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Разрешаем определенные методы запросов
                .allowedHeaders("*"); // Разрешаем все заголовки запросов
    }
}
