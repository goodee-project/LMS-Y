package gd.fintech.lms.manager.vo;

import lombok.Data;

// 강의 계획서 정보 vo
@Data
public class Syllabus {
	private int syllabusNo; // 강의 계획서 고유번호
	private int lectureNo; // 강좌 고유번호
	private String syllabusContent; // 강의 계획서 내용
	private String syllabusTeacherSign; // 강의 계획서 강사 서명
	private String syllabusManagerSign; // 강의 계획서 운영자 서명
	private String syllabusCreateDate; // 강의 계획서 생성일자
	private String syllabusUpdateDate; // 강의 계획서 수정일자
}
