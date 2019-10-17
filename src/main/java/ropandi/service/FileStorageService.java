package ropandi.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import ropandi.entity.Image;
import ropandi.exception.FileNotFoundException;
import ropandi.exception.FileStorageException;
import ropandi.repository.ImageRepository;


@Service
public class FileStorageService implements IFileStorageService {

	@Value("${file.upload.dir}")
	private String dir;
	
	@Autowired
	private ImageRepository imageRepository;
	
	
	@Override
	public String storeFile(MultipartFile file) {
		// TODO Auto-generated method stub
		final String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		try {
			Path fileStorageLocation = Paths.get(dir)
		            .toAbsolutePath().normalize();
			
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }
            String uuid = UUID.randomUUID().toString();
            //save to DB
            imageRepository.save(Image.newImage(uuid, fileName, file.getBytes(), new Date(), "SYSTEM", null, "").build());
            //save to Path
			Path targetLocation = fileStorageLocation.resolve(fileName);
			Files.copy(file.getInputStream(), targetLocation,StandardCopyOption.REPLACE_EXISTING);
			 
            
            return fileName;
        } catch (IOException ex) {
        	
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
	}


	@Override
	public Resource loadFileAsResource(String fileName) {
		try {
			Path fileStorageLocation = Paths.get(dir)
		            .toAbsolutePath().normalize();
			Path filePath = fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new FileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new FileNotFoundException("File not found " + fileName, ex);
        }
	}

}
