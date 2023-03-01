package com.portal.service;

public interface IEmailService {

	String sendSimpleMail(EmailDetails details);
	
    String sendMailWithAttachment(EmailDetails details);

	String sendPaypoutMailWithAttachment(EmailDetails details);
}
