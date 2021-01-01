package gd.fintech.lms.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

//강사 DTO

@Data
public class TeacherForm {
	// 강사 아이디
	private String	accountId;					
	// 강사 이메일
	private String	teacherEmail;				
	// 강사 연락처
	private String	teacherPhone;				
	// 강사 이름
	private String	teacherName;				
	// 강사 성별 (남, 여)
	private String	teacherGender;				
	// 강사 생년월일
	private String	teacherBirth;				
	// 강사 메인주소
	private String	teacherAddressMain;			
	// 강사 상세주소
	private String	teacherAddressSub;			
	// 강사 프로필 사진
	private MultipartFile teacherImage;			
	// 강사 계정 활성화 일시
	private String	teacherAccessdate;			
	// 강사 계정 업데이트 일시
	private String	teacherUpdatedate;			
	// 계정을 승인한 운영자의 아이디
	private String	managerIdAccess;			
	// 강사 한 줄 자기소개
	private String	teacherInfo;	
	//multipart
	private List<MultipartFile> imageFileList;
	
}
