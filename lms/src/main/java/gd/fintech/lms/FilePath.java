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
		if (os.matches("linux")) {			// 리눅스 운영체제일 경우 (LMS-Y AWS 인스턴스 배포 환경)
			// Tomcat 경로를 붙여주고, ROOT 경로에 lms-upload 경로를 만들어 사용
			// war파일과 연관된 경로로 설정 시 war 파일을 배포할 때마다 첨부파일이 계속 초기화되기에, 부득이하게 편법을 사용함
			filePath = LINUX_TOMCAT_PATH+"/webapps/ROOT/lms-upload";
		} else if (os.matches("windows")) {	// 윈도우즈 운영체제일 경우 (Windows + STS 개발 환경)
			// 현재 프로젝트 경로를 받아옴, 또한 \\(역슬래시)를 /(슬래시)로 바꿔줌 (정규표현식에선 \\\\가 \\역할을 함)
			String projectPath = System.getProperty("user.dir").replaceAll("\\\\", "/");
			
			// System.getProperty("user.dir") 참고 자료
			// #1: https://docs.oracle.com/javase/tutorial/essential/environment/sysprop.html
			// #2: https://stackoverflow.com/questions/16239130/java-user-dir-property-what-exactly-does-it-mean
			
			filePath = projectPath+"/src/main/resources/static/upload";
		} else {							// 리눅스도 윈도우즈도 아닐 경우
			// 지원되지 않는 운영체제라는 예외를 던짐
			throw new RuntimeException("FilePath.getFilePath() 예외: 지원되지 않는 운영체제입니다! (리눅스 혹은 윈도우즈만 지원)");
		}
		
		// mkdirs() 메서드를 이용해 디렉터리가 없을 경우 디렉터리 일괄 생성
		File directory = new File(filePath);
		directory.mkdirs();
		
		// 바로 UUID를 붙여 사용할 수 있게 하기 위해 /를 삽입해줌
		return filePath+"/";
	}
}
