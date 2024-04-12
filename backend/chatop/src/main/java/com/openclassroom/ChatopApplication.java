package com.openclassroom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.openclassroom.configuration.LocationHelpers;

@SpringBootApplication
public class ChatopApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(ChatopApplication.class, args);
	}
	
//	@Bean
//    public WebMvcConfigurer webMvcConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addResourceHandlers(ResourceHandlerRegistry registry) {
//                registry.addResourceHandler("/images/**")
//                        .addResourceLocations("file:"+LocationHelpers.STATIC_DIR);
//            }
//        };
//    }

}
