package com.mindfire.rentingit.services;

import java.io.File;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.mindfire.rentingit.config.FileStorageConfig;
import com.mindfire.rentingit.exception.FileStorageException;
import com.mindfire.rentingit.exception.ResourceNotFoundException;

/**
 * @author ujjwalk
 *
 */
/**
 * @author ujjwalk
 *
 */
@Service
public class FileStorageService {

	private final Path fileStorageLocation;

	@Autowired
	public FileStorageService(FileStorageConfig fileStoragePojo) {
		this.fileStorageLocation = Paths.get(fileStoragePojo.getBaseDir() + fileStoragePojo.getUploadDir())
				.toAbsolutePath().normalize();

		try {
			Files.createDirectories(this.fileStorageLocation);
		} catch (Exception ex) {
			throw new FileStorageException("Unable to create the directory where the uploaded files will be stored.",
					ex);
		}
	}

//	public String storeFile(MultipartFile file) {
//		// Normalize file name
//		String fileName = LocalDateTime.now().toString() +StringUtils.cleanPath(file.getOriginalFilename());
//		fileName  = fileName.replaceAll(":","_");
//
//		try {
//			// Check if the file's name contains invalid characters
//			if (fileName.contains("..")) {
//				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
//			}
//
//			// Copy file to the target location (Replacing existing file with the same name)
//			Path targetLocation = this.fileStorageLocation.resolve(fileName);
//			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
//
//			return fileName;
//		} catch (IOException ex) {
//			throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
//		}
//	}

	/**
	 * getting image path
	 * 
	 * @param file
	 * @return path
	 * @throws IOException
	 */
	public Path getPath(MultipartFile file) throws IOException {
		// Normalize file name
		String fileName = LocalDateTime.now().toString() + StringUtils.cleanPath(file.getOriginalFilename());
		fileName = fileName.replaceAll(":", "_");

		// Check if the file's name contains invalid characters
		if (fileName.contains("..")) {
			throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
		}

		// Copy file to the target location (Replacing existing file with the same name)
		Path targetLocation = this.fileStorageLocation.resolve(fileName);
		Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

		return targetLocation;
	}

	public Resource loadFileAsResource(String fileName) throws IOException {
		try {
			Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
			Resource resource = new UrlResource(filePath.toUri());
			if (resource.exists()) {
				return resource;
			} else {
				throw new ResourceNotFoundException("File not found " + fileName);
			}
		} catch (MalformedURLException ex) {
			throw new ResourceNotFoundException("File not found " + fileName, ex);
		}
	}

	public byte[] getByteOfImage(String fileName) throws IOException {
		try {
			Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
			Resource resource = new UrlResource(filePath.toUri());
			if (resource.exists()) {

				File f = new File(resource.getFile().getAbsolutePath());
				byte[] data = Files.readAllBytes(f.toPath());
				return data;
			} else {
				throw new ResourceNotFoundException("File not found " + fileName);
			}
		} catch (MalformedURLException ex) {
			throw new ResourceNotFoundException("File not found " + fileName, ex);
		}
	}

	public Path getPathByImageName(String fileName) throws IOException {
		try {
			Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
			Resource resource = new UrlResource(filePath.toUri());
			if (resource.exists()) {
				return filePath;
			} else {
				throw new ResourceNotFoundException("File not found " + fileName);
			}
		} catch (MalformedURLException ex) {
			throw new ResourceNotFoundException("File not found " + fileName, ex);
		}

	}
}
