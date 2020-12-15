package gd.fintech.lms.teacher.vo;

import lombok.Data;

// 과제 정보 관련 vo

@Data
public class Report {
	// 과제 고유번호 (PK, AUTO_INCREMENT)
	private int reportNo;
	
	// 과제를 등록한 강사가 속한 강좌의 고유번호 (FK)
	private int lectureNo;
	
	// 과제 제목
	private String reportTitle;
	
	// 과제의 설명 및 내용
	private String reportContent;
	
	// 이 행을 생성한 날짜 (사용자 임의 기입 불가, NOW())
	private String reportCreateDate;
	
	// 이 행을 수정한 날짜 (사용자 임의 기입 불가, NOW())
	private String reportUpdateDate;
	
	// 과제 시작(예정)일
	private String reportStartDate;
	
	// 과제 마감일
	private String reportEndDate;
}
