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
		security=@SecurityRequirement(
				name="bearerAuth"
				)
)
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
