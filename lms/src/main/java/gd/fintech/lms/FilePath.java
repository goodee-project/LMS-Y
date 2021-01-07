package gd.fintech.lms;

import java.io.File;

// 첨부파일의 경로를 자동으로 잡아주도록 도와주는 유틸리티 클래스

public class FilePath {
	// 리눅스 배포 환경일 경우 사용되는 Tomcat 경로
	private static final String LINUX_TOMCAT_PATH = "/home/ubuntu/tomcat9";
	
	// 첨부파일이 저장될 절대경로를 출력
	// 리턴값: 첨부파일이 저장될 절대경로(C:/workspace/lms/src/main/resources/static/upload/ or /home/ubuntu/tomcat9/webapps/ROOT/lms-upload/)
	public static String getFilePath() {
		String filePath = null;
		
		// 현재 프로젝트를 구동시키는 JVM과 연결된 OS를 받아옴
		String os = System.getProperty("os.name").toLowerCase();
		if (os.indexOf("linux") >= 0) {										// 리눅스 운영체제일 경우 (LMS-Y AWS 인스턴스 배포 환경)
			// Tomcat 경로를 붙여주고, upload/lms 폴더에 저장
			// 컨트롤러의 매핑을 이용하여 파일을 받게끔 유도할것이므로, 실제 URL로 접속하지 않아도 됨
			// 따라서 임의의 폴더를 만들어 배정함
			filePath = LINUX_TOMCAT_PATH+"/upload/lms";
		} else if (os.indexOf("windows") >= 0 || os.indexOf("mac") >= 0) {	// 윈도우즈 혹은 맥 운영체제일 경우 (Windows/Mac + STS 개발 환경)
			// 현재 프로젝트 경로를 받아옴, 또한 \\(역슬래시)를 /(슬래시)로 바꿔줌 (정규표현식에선 \\\\가 \\역할을 함)
			String projectPath = System.getProperty("user.dir").replaceAll("\\\\", "/");
			
			// System.getProperty("user.dir") 참고 자료
			// #1: https://docs.oracle.com/javase/tutorial/essential/environment/sysprop.html
			// #2: https://stackoverflow.com/questions/16239130/java-user-dir-property-what-exactly-does-it-mean
			
			// 테스트용으로 첨부파일 자체에 접근할 수 있도록 static 리소스 폴더 내에 집어넣음
			filePath = projectPath+"/src/main/resources/static/upload";
		} else {									// 리눅스도 윈도우즈도 아닐 경우
			// 지원되지 않는 운영체제라는 예외를 던짐
			throw new RuntimeException("FilePath.getFilePath() 예외: 지원되지 않는 운영체제입니다! (리눅스, 윈도우즈 혹은 맥 계열만 지원)");
		}
		
		// mkdirs() 메서드를 이용해 디렉터리가 없을 경우 디렉터리 일괄 생성
		File directory = new File(filePath);
		directory.mkdirs();
		
		// 바로 UUID를 붙여 사용할 수 있게 하기 위해 /를 삽입해줌
		return filePath+"/";
	}
}
