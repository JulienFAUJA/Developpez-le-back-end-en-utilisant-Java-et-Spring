package com.openclassroom;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;


@Configuration
@ConfigurationProperties(prefix="com.openclassroom")
public class CustomProperties {

	private String api_prefix;

	public String getApi_prefix() {
		return api_prefix;
	}

	public void setApi_prefix(String api_prefix) {
		this.api_prefix = api_prefix;
	}
	
}
