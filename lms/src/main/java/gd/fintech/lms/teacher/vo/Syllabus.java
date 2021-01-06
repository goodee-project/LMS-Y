package gd.fintech.lms.teacher.vo;

import lombok.Data;

// 강의계획서 vo

@Data
public class Syllabus {
	// 강의계획서 고유번호(강좌 고유번호와 동일)
	private int lectureNo;
	
	// 강의계획서 작성자 아이디
	private String accountId;
	
	// 강의계획서 작성자
	private String syllabusWriter;

	// 강의계획서 내용
	private String syllabusContent;
	
	// 강의계획서 강사 서명
	private String syllabusTeacherSign;
	
	// 강의계획서 강사 서명일자
	private String syllabusTeacherSignDate;
	
	// 강사계획서 운영자 서명
	private String syllabusManagerSign;
	
	// 강의계획서 운영자 서명일자
	private String syllabusManagerSignDate;
	
	// 강의계획서 작성일자
	private String syllabusCreateDate;
	
	// 강의계획서 수정일자
	private String syllabusUpdateDate;
}
