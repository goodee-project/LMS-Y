package gd.fintech.lms.teacher.vo;

import lombok.Data;

// 시험 정보 관련 vo

@Data
public class Test {
	// 시험을 등록한 강사가 속한 강좌의 고유번호
	private int lectureNo;
	
	// 시험 시작(예정)일
	private String testStartDate;
	
	// 시험 종료일
	private String testEndDate;
	
	// 시험의 설명 및 내용
	private String testContent;
	
	// 이 행을 생성한 날짜
	private String testCreateDate;
	
	// 이 행을 수정한 날짜
	private String testUpdateDate;
}
