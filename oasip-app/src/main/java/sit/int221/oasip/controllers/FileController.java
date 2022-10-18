package sit.int221.oasip.controllers;

import org.apache.tomcat.jni.FileInfo;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import sit.int221.oasip.services.FileService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/files")
public class FileController {

    private final FileService fileService;
    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }


    @GetMapping("")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@RequestParam("path") String filename, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileService.loadFileAsResource(filename);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST , "Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

//    @PostMapping("")
//    public String fileUpload(@Valid @RequestParam("file") MultipartFile file , Authentication auth) throws ResponseStatusException{
//        try{
//            fileService.store(file, auth);
//        }catch (Exception ex ) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "File size exceeded.");
//        }
//        return "You successfully uploaded " + file.getOriginalFilename() + "!";
//    }
}

