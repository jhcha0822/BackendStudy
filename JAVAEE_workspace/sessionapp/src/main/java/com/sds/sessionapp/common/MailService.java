package com.sds.sessionapp.common;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailService {
	String host="smtp.gmail.com"; // 구글 smpt 메일 주소
	String user="dalguji000@gmail.com";
	String password="hhgm wtui ondv iwhv";
	
	Properties props = new Properties(); //java.util 의 객체중 Map의 자식 객체 
	
	public void send(String name, String to) { // to는 상대방 주소
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", 465);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		props.put("mail.smtp.ssl.protocols", "TLSv1.2"); // java 1.8 이하는 이 설정도 추가 
		
		// 구글로부터 인증받기
		
		// java.mail.Session
		Session session = Session.getDefaultInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});
		
		// 메일의 내용을 구성하기 위한 객체인 MimeMessage 객체
		MimeMessage message = new MimeMessage(session);
		
		try {
			// 받는 사람 설정
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			// 보내는 사람 설정
			message.setFrom(new InternetAddress(user));
			// 메일 제목
			message.setSubject(name+"님, 회원 가입을 축하드립니다!");
			// 메일 내용
			message.setContent("<h1>저희 사이트 가입을 축하드립니다.</h1>", "text/html;charset=utf-8");
			// 메일 발송
			Transport.send(message);
			
			System.out.println("메일 발송 성공");
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	/*
	public static void main(String[] args) {
		MailService ms = new MailService();
		ms.send("Kingdomcome", "jhcha0822@hanyang.ac.kr");
	}
	*/
}
