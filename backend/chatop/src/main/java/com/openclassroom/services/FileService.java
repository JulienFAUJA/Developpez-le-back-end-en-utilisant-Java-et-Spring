package com.openclassroom.services;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;



import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.openclassroom.configuration.LocationHelpers;
import com.openclassroom.services.Interfaces.IFileService;

@Service
public class FileService implements IFileService{
	
	private final Path rootLocation;

    public FileService() {
        this.rootLocation = Paths.get(LocationHelpers.STATIC_DIR);
    }
  
    public String save(MultipartFile file) throws Exception {
    	String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        Path uploadPath = Paths.get("src/main/resources/static/images/");

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        Path filePath = uploadPath.resolve(fileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        String serverUrl="http://localhost:8080";
        // Construit l'URL pour accéder à l'image via le serveur et la retourne.
        return serverUrl + "/images/" + fileName;
    }
    
    

}
