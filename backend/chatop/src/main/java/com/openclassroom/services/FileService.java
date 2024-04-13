package com.openclassroom.services;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.UUID;


import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.openclassroom.configuration.LocationHelpers;

@Service
public class FileService {
	
	private final Path rootLocation;

    public FileService() {
        this.rootLocation = Paths.get(LocationHelpers.STATIC_DIR);
    }
    
    public String save1(MultipartFile file) throws Exception {
        if (file.isEmpty()) {throw new IOException("[file.isEmpty] Erreur de l'enregistrement du fichier...");}

        String originalName = file.getOriginalFilename();
        Path destFile = this.rootLocation.resolve(Paths.get(originalName)).normalize().toAbsolutePath();
        System.out.println("destFile:"+destFile.toString());
        if (Files.exists(destFile)) {
            String fileExt = originalName.substring(originalName.lastIndexOf("."));
            String baseName = originalName.substring(0, originalName.lastIndexOf("."));
            
            // Générer nouveau filename pour éviter d'écraser l'existant...
            String newFileName = baseName + "_" + UUID.randomUUID().toString() + fileExt;
            destFile = this.rootLocation.resolve(Paths.get(newFileName)).normalize().toAbsolutePath();
            originalName=newFileName;
        }

        if (!destFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
            throw new IOException("[AbsolutePathError] Impossible d'enregistrer le fichier. Mauvais répertoire...");
        }

        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, destFile, StandardCopyOption.REPLACE_EXISTING);
        }
        //return destFile.toString();
        
     // Retournez l'URL de l'image
        return "http://localhost:8080/api/static/images/" +destFile;
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
