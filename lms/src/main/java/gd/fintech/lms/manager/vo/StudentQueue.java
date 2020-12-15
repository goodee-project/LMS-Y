package gd.fintech.lms.manager.vo;

import lombok.Data;

// 학생승인대기 vo : 학생이 회원가입 후 운영자에게 승인 받기 전 대기 vo

@Data
public class StudentQueue {
	// 학생 id
	private String accountId; 
	
	// 학생 email
	private String studentEmail;
	
	// 학생 이름
	private String studentName;
	
	// 학생 전화번호
	private String studentPhone; 
	
	// 학생 성별
	private String studentGender; 
	
	// 학생 생년월일
	private String studentBirth; 
	
	// 학생 주소
	private String studentAddressMain; 
	
	// 학생 세부주소
	private String studentAddressSub;
}
