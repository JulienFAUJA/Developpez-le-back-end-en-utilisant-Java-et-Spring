package com.openclassroom.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.Resource;
import com.openclassroom.configuration.LocationHelpers;

@Service
public class FileService {
	
	private final Path rootLocation;

    public FileService() {
        //this.rootLocation = Paths.get(storageLocation);
        this.rootLocation = Paths.get(LocationHelpers.STATIC_DIR);
    }
    
    public String save(MultipartFile file) throws Exception {
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
        return getImageUrl(originalName, destFile).toString();
    }
    
    public String getImageUrl(String filename, Path destFile) throws Exception {
//    	Resource resource;
//		try {
//			resource = new UrlResource(destFile.toString());
//		} catch (MalformedURLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			resource = new UrlResource("http://localhost:8080/api/images/" +filename);
//		}
//		System.out.println("resource:"+resource);
//    	return resource;
    	return "http://localhost:8080/api/images/" +filename;
        //return LocationHelpers.STATIC_DIR+"/" + filename;
    }

}
