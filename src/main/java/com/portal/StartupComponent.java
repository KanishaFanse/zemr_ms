package com.portal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartupComponent implements CommandLineRunner{

	private final StorageProperties storageProps;

	   public StartupComponent (StorageProperties storageProps){
	     this.storageProps = storageProps;
	   }

	@Override
	public void run(String... arg0) throws Exception {
		// TODO Auto-generated method stub
		 String path = storageProps.getPath();
	}
}
