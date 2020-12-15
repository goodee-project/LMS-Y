package gd.fintech.lms.student.vo;

import lombok.Data;

// 과제제출 vo : 학생이 과제 제출 할 내용의 vo 
@Data
public class ReportSubmit {
	private int reportSubmitNo;				// 과제제출 번호
	private int reportNo;					// 과제 번호
	private String accountId;				// 계정 id
	private String reportSubmitTitle;		// 과제제출 제목
	private String reportSubmitContent; 	// 과제제출 내용
	private int reportSubmitPoint;			// 과제 점수 
	private String reportSubmitFeedback; 	// 과제 피드백
	private String reportSubmitCreateDate; 	// 과제제출 입력날짜
	private String reportSubmitUpdateDate; 	// 과제제출 수정날짜 
}
