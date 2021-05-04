package com.mindfire.rentingit.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mindfire.rentingit.constants.Message;
import com.mindfire.rentingit.dto.response.UploadFileResponse;
import com.mindfire.rentingit.services.FileStorageService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rentingIt/product/resources/")
public class FileStorageController {
	
	@Autowired
    private FileStorageService fileStorageService;
	
	@Autowired
	private Message msg;
	
	@PostMapping("/upload-single-image")
    public UploadFileResponse uploadSingleImage(@RequestParam("file") MultipartFile file) throws IOException  {
		
		if(file.isEmpty()) {
	        return new UploadFileResponse(msg.EMPTY_FILE_SELECTED, null, null,null, 0);
		}

		String fileName = fileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download-file/")
                .path(fileName)
                .toUriString();

        return new UploadFileResponse(msg.FILE_UPLOADED, fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
		
    }
	 
	@PostMapping("/upload-multiple-images")
	public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
		 return Arrays.asList(files)
	                .stream()
	                .map(file -> {
						try {
							return uploadSingleImage(file);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						return null;
					})
	                .collect(Collectors.toList());
    }

    @GetMapping("/download-image/{imgName:.+}")
    public ResponseEntity<Resource> downloadImage(@PathVariable String imgName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(imgName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            System.out.print("Could not determine file type.");
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

}
