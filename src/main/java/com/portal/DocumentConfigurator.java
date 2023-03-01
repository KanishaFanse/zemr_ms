package com.portal;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class DocumentConfigurator {

	@Value("${upload.path}")
	private String uploadPath;
	
	public void setUploadPath() {
		validateAndCreateFolder(uploadPath);
	}
	
	
	
	private void validateAndCreateFolder(String folderPath) {
		System.out.println("FOLDER PATH::"+folderPath);
		File file = new File(folderPath);
		if (!file.exists()) {
			file.mkdirs();
		}
	}
}
