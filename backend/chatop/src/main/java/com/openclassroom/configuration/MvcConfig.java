package com.openclassroom.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc                                           // Active la configuration par défaut de Spring MVC
public class MvcConfig implements WebMvcConfigurer {    // Permet de personnaliser la configuration MVC de Spring :

                        // Elle spécifie le répertoire où les fichiers sont téléchargés sur le serveur.

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Handler for images
        registry.addResourceHandler("/images/**")   
                .addResourceLocations("file:" + "src/main/resources/static/images" + "/");

       
    }
}