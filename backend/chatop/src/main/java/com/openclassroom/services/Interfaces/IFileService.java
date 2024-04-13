package com.openclassroom.services.Interfaces;

import org.springframework.web.multipart.MultipartFile;

public interface IFileService {
	
	String save(MultipartFile file) throws Exception;

}
