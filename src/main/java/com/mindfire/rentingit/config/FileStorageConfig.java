package com.mindfire.rentingit.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "file")
public class FileStorageConfig {
	private String uploadDir;
	private String baseDir;
	private String actualpath;

	public String getUploadDir() {
		return uploadDir;
	}

	public void setUploadDir(String uploadDir) {
		this.uploadDir = uploadDir;
	}

	public String getBaseDir() {
		return baseDir;
	}

	public void setBaseDir(String baseDir) {
		this.baseDir = baseDir;
	}

	public String getActualpath() {
		return actualpath;
	}

	public void setActualpath(String baseDir, String uploadDir) {
		this.actualpath = baseDir + uploadDir;
	}
	
	
}
