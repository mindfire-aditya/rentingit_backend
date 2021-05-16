package com.mindfire.rentingit.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.UrlResource;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mindfire.rentingit.constants.Message;
import com.mindfire.rentingit.dto.response.UploadFileResponse;
import com.mindfire.rentingit.exception.FileStorageException;
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
	public UploadFileResponse uploadSingleImage(@RequestParam("file") MultipartFile file) throws IOException {

		if (file.isEmpty()) {
			return new UploadFileResponse(msg.EMPTY_FILE_SELECTED, null, null, null, null, 0);
		}

		//String fileName = fileStorageService.storeFile(file);
		String fileName = LocalDateTime.now().toString() +StringUtils.cleanPath(file.getOriginalFilename());
		fileName  = fileName.replaceAll(":","_");
		fileName = fileName.replaceAll(" ", "_");
		
		// Check if the file's name contains invalid characters
		if (fileName.contains("..")) {
			throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
		}
		
		Path filePath = fileStorageService.getPath(file);

		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/rentingIt/product/resources/download-file/").path(fileName).toUriString();

		Resource resource;

		resource = new UrlResource(filePath.toUri());

		File f = new File(resource.getFile().getAbsolutePath());
		byte[] data = Files.readAllBytes(f.toPath());

		return new UploadFileResponse(msg.FILE_UPLOADED, fileName, fileDownloadUri, file.getContentType(), data,
				file.getSize());

	}

	@PostMapping("/upload-multiple-images")
	public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
		return Arrays.asList(files).stream().map(file -> {
			try {
				return uploadSingleImage(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}).collect(Collectors.toList());
	}

	@GetMapping("/download-image/{imgName}")
	public ResponseEntity<byte[]> downloadImage(@PathVariable String imgName, HttpServletRequest request)
			throws IOException {

		// Load file as Resource
		Resource resource = fileStorageService.loadFileAsResource(imgName);
		byte[] data = fileStorageService.getByteOfImage(imgName);

		// Try to determine file's content type
		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (IOException ex) {
			System.out.print("Could not determine file type.");
		}

		// Fallback to the default content type if type could not be determined
		if (contentType == null) {
			contentType = "application/octet-stream";
		}

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(data);
	}
	
	@GetMapping("/get-image/{imgName}")
	public @ResponseBody Map<String,String> getImage(@PathVariable String imgName) throws IOException { 
  
		Resource resource;
		Path filePath = fileStorageService.getPathByImageName(imgName);
		
		resource = new UrlResource(filePath.toUri());
		File file = new File(resource.getFile().getAbsolutePath());
		     
		String encodeImage = Base64.getEncoder().withoutPadding().encodeToString(Files.readAllBytes(file.toPath())); 
		 
		Map<String, String> jsonMap = new HashMap<>(); 
		 
		jsonMap.put("content", encodeImage); 
		 
		return jsonMap; 
		
	} 

}
