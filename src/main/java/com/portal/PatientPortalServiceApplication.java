package com.portal;

import java.io.File;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableScheduling
@EnableAutoConfiguration
public class PatientPortalServiceApplication {
	
	private static ConfigurableApplicationContext context;

	public static void main(String[] args) {
		context = SpringApplication.run(PatientPortalServiceApplication.class, args);
	}
	
	public static void restart() {
		ApplicationArguments args = context.getBean(ApplicationArguments.class);
		Thread thread = new Thread(() -> {
			context.close();
			context = SpringApplication.run(PatientPortalServiceApplication.class, args.getSourceArgs());
		});
		thread.setDaemon(false);
		thread.start();
	}
	
	@Bean
	public RestTemplate restTemplate() {
		CloseableHttpClient httpClient
	      = HttpClients.custom()
	        .setSSLHostnameVerifier(new NoopHostnameVerifier())
	        .build();
	    HttpComponentsClientHttpRequestFactory requestFactory 
	      = new HttpComponentsClientHttpRequestFactory();
	    requestFactory.setHttpClient(httpClient);
	    return new RestTemplate(requestFactory);
	}
	
}
