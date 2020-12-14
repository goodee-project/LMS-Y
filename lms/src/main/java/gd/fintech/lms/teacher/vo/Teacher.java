package gd.fintech.lms.teacher.vo;

import lombok.Data;

//강사 VO
@Data
public class Teacher {
	private String teacherId; //강사 아이디
	private String teacherEmail;//강사 이메일
	private String teacherName;//강사 이름
	private String teacherPhone;//강사 전화번호
	private String teacherGender;//강사 성별
	private String teacherBirth;//강사 생년월일
	private String teacherAddressMain;//강사 메인주소
	private String teacherAddressSub;//강사 서브주소
	private String teacherImage;//강사 이미지
	private String teacherAccessdate;//강사 승인날짜
	private String teacherUpdatedate;//강사 업데이트날짜
	private String managerIdAccess;//승인한 운영자 아이디
	private String teacherInfo;//강사 한줄소개  
}
