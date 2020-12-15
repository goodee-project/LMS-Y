package gd.fintech.lms.student.vo;

import lombok.Data;
//학생 vo
@Data
public class Student {
	private String studentId;			// 학생id
	private String studentEmail;		// 학생 이메일
	private String studentName;			// 학생 이름 
	private String studentPhone;		// 학생 번호
	private String studentGender;		// 학생 성별
	private String studentBirth;		// 학생 생일
	private String studentAddressMain;	//학생 주소
	private String studentAddressSub;	// 학생 상세주소
	private String studentImage;		// 학생 이미지
	private String studentAccessDate;	// 학생 승인날짜 now
	private String studentUpdateDate;	// 학생 개인정보 수정날짜
	private String mangerIdAccess;		// 운영자가 학생등록을 승인한 날짜 
}
