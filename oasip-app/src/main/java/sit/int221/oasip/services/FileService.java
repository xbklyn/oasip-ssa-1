package sit.int221.oasip.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import sit.int221.oasip.entities.Event;
import sit.int221.oasip.properties.FileProperties;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileService {

    private final Path fileStorageLocation;
    @Autowired
    public FileService(FileProperties fileProperties) {
        this.fileStorageLocation = Paths.get(fileProperties.getUpload_dir()).toAbsolutePath().normalize();
        try{
            Files.createDirectories(this.fileStorageLocation);
        }catch (Exception e) {
            throw new RuntimeException(
                "Could not create the directory where the uploaded files will be stored.", e);
        }
    }

    public String store(MultipartFile file , Event event) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String user_dir = event.getUser() == null ? "guest" : "user/" + "user_" + event.getUser().getId().toString();
        String event_id = "event_" + event.getBookingId().toString();

        try {
            if (fileName.contains("..")) { throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sorry! Filename contains invalid path sequence " + fileName);}
            Path current_dir = this.fileStorageLocation.resolve(user_dir + "/" + event_id);
            Path target = Files.createDirectories(current_dir.resolve(fileName));
            System.out.println(target.toString()    );
            Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        } catch (Exception ex) {
//            throw new RuntimeException("Could not store file " + fileName + ". Please try again!", ex); }
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not store file " + fileName + ". Please try again!");
        }
    }

    public Resource loadFileAsResource(String fileName) { try {
        Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
        Resource resource = new UrlResource(filePath.toUri());
        if (resource.exists()) {
            return resource;
        }
        else {
            throw new RuntimeException("File not found " + fileName); }
    } catch (MalformedURLException ex) {
        throw new RuntimeException("File not found " + fileName, ex); }
    }

}
