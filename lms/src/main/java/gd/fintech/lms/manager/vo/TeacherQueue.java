package gd.fintech.lms.manager.vo;

import lombok.Data;

// 강사승인대기vo : 운영자가 강사의 아이디를 승인하기 전 대기 vo

@Data
public class TeacherQueue {
	// 강사 id
	private String accountId;
	
	// 강사 email
	private String teacherEmail;
	
	// 강사 이름
	private String teacherName; 
	
	// 강사 전화번호
	private String teacherPhone;
	
	// 강사 성별
	private String teacherGender;
	
	// 강사 생년월일
	private String teacherBirth;
	
	// 강사 주소
	private String teacherAddressMain;
	
	// 강사 세부주소
	private String teacherAddressSub;
}
