package gd.fintech.lms;

import org.apache.commons.mail.*;

// 이메일를 사용하기 위한 클래스
// SMTP: 우편 전송 프로토콜
// TSL: 네트워크 통신시에 암호와를 통해 기밀성을 유지해주는 규약(SSL을 기반)
// SSL: 서버와 클라이언트간 암호화된 통신을 구현하는 기술

public class MailUtil {
	// 이메일을 보내는 메소드
	// 매개변수: 수신자 이메일, 제목, 내용
	public static void sendMail(String email,String subject,String msg) throws Exception{		
		// 메일 서버 설정
		String charSet = "utf-8";
		String hostSMTP = "smtp.naver.com";
		String hostSMTPId = "gdsmtp777";
		String hostSMTPPw = "@qqui!5890cv";	
		// 발신자 메일 설정
		String fromEmail = "gdsmtp777@naver.com";
		String fromName = "LMS관리자";
		
		// 이메일 전송
		try {
			HtmlEmail mail = new HtmlEmail();
			mail.setDebug(true);
			mail.setCharset(charSet);
			mail.setSSLOnConnect(true);	// SSL 사용(TLS 없는경우)
			mail.setHostName(hostSMTP);
			mail.setSmtpPort(587);	// SMTP 포트 번호
			mail.setAuthentication(hostSMTPId, hostSMTPPw);
			mail.setStartTLSEnabled(true);	// TLS 사용
			mail.addTo(email);	// 보내는 이메일
			mail.setFrom(fromEmail, fromName, charSet);	// 발신자 메일
			mail.setSubject(subject);
			mail.setHtmlMsg(msg);
			mail.send();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
