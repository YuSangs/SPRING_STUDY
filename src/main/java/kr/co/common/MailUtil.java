package kr.co.common;

import java.io.File;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class MailUtil {
	
	/**
	 * 
	 * JavaMailSender bean으로 등록
	 * 메일 전달하는 계정은 bean에 설정된 계정
	 * 
	 * 
	 * */
	
	private JavaMailSender mailSender;
	private MimeMessage message;
	private MimeMessageHelper helper;
	
	public MailUtil(JavaMailSender mailSender) throws Exception {
		this.mailSender = mailSender;
		message = this.mailSender.createMimeMessage();
		helper = new MimeMessageHelper(message, true, "UTF-8");
	}
	
	public void setTo(String email) throws Exception {
		helper.setTo(email);
	}
	
	public void setFrom(String email, String name) throws Exception {
		helper.setFrom(email, name);
	}
	
	public void setSubject(String subject) throws Exception {
		helper.setSubject(subject);
	}
	
	public void setText(String htmlContent) throws Exception {
		helper.setText(htmlContent, true); //메일 내용 (true : html 형식 / false : text 형식)
	}
	
	public void setFile(String fileName, File file) throws Exception {
		helper.addAttachment(fileName, file); //첨부파일
	}
	
	public void mailSend() throws Exception {
		mailSender.send(message);
	}
}
