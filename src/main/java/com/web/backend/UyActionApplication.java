package com.web.backend;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.*;

@SpringBootApplication
@PropertySource("classpath:application-${spring.profiles.active:default}.properties")
public class UyActionApplication {

    public static void main(String[] args) {
        SpringApplication.run(UyActionApplication.class, args);
    }
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:4200") // Replace with your Angular app URL
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allowed HTTP methods
                        .allowedHeaders("*") // Allowed request headers (you can customize this based on your requirements)
                        .allowCredentials(true);
            }
        };
    }
}
