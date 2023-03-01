package com.portal;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "upload")
public class StorageProperties {
	
	@Value("${upload.path}")
	private String uploadPath;
	
	private String path;

	public String getPath() {
		this.path = uploadPath;
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	
}
