package gd.fintech.lms.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

//학생 DTO

@Data
public class StudentForm {
	// 학생 아이디
	private String	accountId;					
	// 학생 이메일
	private String	studentEmail;				
	// 학생 연락처
	private String	studentPhone;				
	// 학생 이름
	private String	studentName;				
	// 학생 성별 (남, 여)
	private String	studentGender;				
	// 학생 생년월일
	private String	studentBirth;				
	// 학생 메인주소
	private String	studentAddressMain;			
	// 학생 상세주소
	private String	studentAddressSub;			
	// 학생 프로필 사진
	private MultipartFile studentImage;			
	// 학생 계정 활성화 일시
	private String	studentAccessdate;			
	// 학생 계정 업데이트 일시
	private String	studentUpdatedate;			
	// 계정을 승인한 운영자의 아이디
	private String	managerIdAccess;		
	//multipart
	private List<MultipartFile> imageFileList;
}
