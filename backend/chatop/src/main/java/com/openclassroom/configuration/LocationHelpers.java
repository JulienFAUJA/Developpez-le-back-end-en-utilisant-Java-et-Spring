package com.openclassroom.configuration;

import java.io.File;

public class LocationHelpers {
	
	public final static String ROOT_DIR = System.getProperty("user.dir");
	public final static String STATIC_DIR = LocationHelpers.GeneratePath("src,main,resources,static");
	public final static String ROOT_URI = "/api";
	public final static String AUTH_URI = LocationHelpers.ROOT_URI+"/auth";
	public final static String REGISTER_URI = LocationHelpers.AUTH_URI+"/register";
	public final static String LOGIN_URI = LocationHelpers.AUTH_URI+"/login";
	public final static String ME_URI = LocationHelpers.AUTH_URI+"/me";
	public final static String USER_URI = LocationHelpers.ROOT_URI+"/user";
	public final static String RENTALS_URI = LocationHelpers.ROOT_URI+"/rentals";
	public final static String MESSAGES_URI = LocationHelpers.ROOT_URI+"/messages";
	
	public static String GeneratePath(String folder_names) {
		String folder_tree_path = folder_names.replace(",", File.separator);
		String fullPath = ROOT_DIR+File.separator+folder_tree_path;
		return fullPath;
	}
	
	
	
	
	

}
