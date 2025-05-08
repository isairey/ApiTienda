package com.ejemplo.demo1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {
   @Bean
   public WebMvcConfigurer corsConfigurer() {
       return new WebMvcConfigurer() {
           @Override
           public void addCorsMappings(@NonNull CorsRegistry registry) {
               registry.addMapping("/**")
                       .allowedOrigins("https://soft-torrone-90dd2d.netlify.app") 
                       .allowedMethods("GET", "POST", "PUT", "DELETE")
                       .allowedHeaders("*");
           }
       };
   }
}

