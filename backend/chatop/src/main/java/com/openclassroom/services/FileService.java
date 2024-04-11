package com.openclassroom.services;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import com.openclassroom.configuration.LocationHelpers;

@Service
public class FileService {
	
	private final Path rootLocation;

    public FileService(@Value("${file_storage.location}") String storageLocation) {
        //this.rootLocation = Paths.get(storageLocation);
        this.rootLocation = Paths.get(LocationHelpers.STATIC_DIR);
    }
    
    public String save(MultipartFile file) throws IOException {
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
        }

        if (!destFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
            throw new IOException("[AbsolutePathError] Impossible d'enregistrer le fichier. Mauvais répertoire...");
        }

        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, destFile, StandardCopyOption.REPLACE_EXISTING);
        }
        return destFile.toString();
    }

}
