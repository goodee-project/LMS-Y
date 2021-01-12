package gd.fintech.lms.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class StudentForm {
	// 학생 아이디
	private String	accountId;					
	// 강사 이메일
	private String	studentEmail;				
	// 강사 연락처
	private String	studentPhone;				
	// 강사 이름
	private String	studentName;				
	// 강사 성별 (남, 여)
	private String	studentGender;				
	// 강사 생년월일
	private String	studentBirth;				
	// 강사 메인주소
	private String	studentAddressMain;			
	// 강사 상세주소
	private String	studentAddressSub;			
	// 강사 프로필 사진
	private MultipartFile studentImage;			
	// 강사 계정 활성화 일시
	private String	studentAccessdate;			
	// 강사 계정 업데이트 일시
	private String	studentUpdatedate;			
	// 계정을 승인한 운영자의 아이디
	private String	managerIdAccess;		
	//multipart
	private List<MultipartFile> imageFileList;
}
