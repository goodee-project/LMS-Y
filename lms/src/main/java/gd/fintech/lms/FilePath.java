package gd.fintech.lms;

import java.io.File;

// 첨부파일의 경로를 자동으로 잡아주도록 도와주는 유틸리티 클래스

public class FilePath {
	// 리눅스 배포 환경일 경우 사용되는 Tomcat 경로
	private static final String TOMCAT_PATH = "/home/ubuntu/tomcat9";
	
	// 첨부파일이 저장될 절대경로를 출력
	// 리턴값: 첨부파일이 저장될 절대경로(C:/workspace/lms/src/main/resources/static/upload/ or /home/ubuntu/tomcat9/webapps/ROOT/lms-upload/)
	public static String getFilePath() {
		String filePath = null;
		
		if (System.getProperty("os.name").toLowerCase().equals("linux")) { // 리눅스 운영체제일 경우 (LMS-Y AWS 인스턴스)
			// Tomcat 경로를 붙여주고, ROOT 패스에 lms-upload 경로를 만들어 사용
			// war파일과 연관된 경로로 설정 시 첨부파일이 계속 초기화되기에, 부득이하게 편법을 사용함
			filePath = TOMCAT_PATH+"/webapps/ROOT/lms/upload";
		} else { // 리눅스 운영체제가 아닐 경우 (Windows + STS 개발 환경)
			// 프로젝트 경로를 받아오고, \\(역슬래시)를 /(슬래시)로 바꿔줌
			String projectPath = System.getProperty("user.dir").replaceAll("\\\\", "/");
			
			filePath = projectPath+"/src/main/resources/static/upload";
		}
		
		// mkdirs() 메서드를 이용해 디렉터리가 없을 경우 디렉터리 일괄 생성
		File directory = new File(filePath);
		directory.mkdirs();
		
		// 바로 UUID를 붙여 사용할 수 있게 하기 위해 /를 삽입해줌
		return filePath+"/";
	}
}
