package com.openclassroom.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

/**
 * Définition des options pour le Swagger
 */
@OpenAPIDefinition(
		info=@Info(
				contact=@Contact(
						name="Julien FAUJANET",
						email="julienfaujanet@gmail.com"
						),
				description="Documentation pour OpenAPI avec Spring Security",
				title="Spécifications pour OpenAPI - Julien FAUJANET",
				version="1.0",
				license=@License(
						name="Licence name"
						)
				),
		/* Les servers sont les différentes instances de l'application.
		 * Ex:
		 * DEV:Environement de dev
		 * PROD:Environement de prod
		*/
		servers= {
				@Server(
						description="Local ENV",
						url="http://localhost:8080"
						),
				@Server(
						description="Prod ENV",
						url="http://faked-url.com:8080"
						),
		},
		/**
		 * En mettant le requirement du security ici, il n'est plus nécessaire de le
		 * mettre au dessus de chaque controller.
		 */
		security=@SecurityRequirement(
				name="bearerAuth"
				)
)
/**
 * Les paramètres de sécurité. 
 * En mettant cette option au pluriel, on peut en définir plusieurs
 * @SecuritySchemes( puis à l'intérieur: plusieurs @SecurityScheme 
 * Ex:
 * un pour les Utilisateurs, un pour les Admins etc...
 */
@SecurityScheme(
		name="bearerAuth",
		description="JWT auth description",
		scheme="bearer",
		type=SecuritySchemeType.HTTP,
		bearerFormat="JWT", 
		in=SecuritySchemeIn.HEADER
		)
public class OpenApiConfig {

}
