package com.portal.service;

import java.io.File;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements IEmailService {
	
	 @Autowired 
	 private JavaMailSender javaMailSender;
	 
	 private String sender;
	 
	 private String recipients;
	 
	 private String type;
	 
	 private String payuotRecipients;

	@Override
	public String sendSimpleMail(EmailDetails details) {
		try {
			String[] strArr = recipients.split(",");

			// Creating a simple mail message
			SimpleMailMessage mailMessage
			= new SimpleMailMessage();

			// Setting up necessary details
			mailMessage.setFrom(sender);
			System.out.println(sender);
			mailMessage.setTo(strArr);
			mailMessage.setText(details.getMsgBody());
			mailMessage.setSubject(details.getSubject());

			// Sending the mail
			javaMailSender.send(mailMessage);
			return "Mail Sent Successfully...";
		}catch (Exception e) {
			e.printStackTrace();
			return "Error while Sending Mail";
		}
	}

	@Override
	public String sendMailWithAttachment(EmailDetails details) {
		// Creating a mime message
		MimeMessage mimeMessage
		= javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper;
		try {
			// Setting multipart as true for attachments to
			// be send
			mimeMessageHelper
			= new MimeMessageHelper(mimeMessage, true);
			mimeMessageHelper.setFrom(sender);
			String[] strArr = recipients.split(",");
			mimeMessageHelper.setTo(strArr);
			mimeMessageHelper.setText(details.getMsgBody());
			mimeMessageHelper.setSubject(
					details.getSubject());
			// Adding the attachment
			FileSystemResource file
			= new FileSystemResource(
					new File(details.getAttachment()));
			mimeMessageHelper.addAttachment(
					file.getFilename(), file);
			// Sending the mail
			javaMailSender.send(mimeMessage);
			return "Mail sent Successfully";
		}catch (Exception e) {
			// Display message when exception occurred
			e.printStackTrace();
			return "Error while sending mail!!!";
		}	
	}
	
	@Override
	public String sendPaypoutMailWithAttachment(EmailDetails details) {
		// Creating a mime message
		MimeMessage mimeMessage
		= javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper;
		try {
			// Setting multipart as true for attachments to
			// be send
			mimeMessageHelper
			= new MimeMessageHelper(mimeMessage, true);
			mimeMessageHelper.setFrom(sender);
			if(type.equalsIgnoreCase("TEST")) {
				String[] strArr = payuotRecipients.split(",");
				mimeMessageHelper.setTo(strArr);
			}
			mimeMessageHelper.setText(details.getMsgBody());
			mimeMessageHelper.setSubject(
					details.getSubject());
			// Adding the attachment
			FileSystemResource file
			= new FileSystemResource(
					new File(details.getAttachment()));
			mimeMessageHelper.addAttachment(
					file.getFilename(), file);
			// Sending the mail
			javaMailSender.send(mimeMessage);
			return "Mail sent Successfully";
		}catch (Exception e) {
			// Display message when exception occurred
			e.printStackTrace();
			return "Error while sending mail!!!";
		}	
	}

}
