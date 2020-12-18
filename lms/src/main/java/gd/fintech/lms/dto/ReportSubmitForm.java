package gd.fintech.lms.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

// 과제제출 vo : 학생이 과제 제출 할 내용의 vo 

@Data
public class ReportSubmitForm {
	// 과제제출 번호
	private int reportSubmitNo;
	
	// 과제 번호
	private int reportNo;
	
	// 계정 id
	private String accountId;
	
	// 과제제출 제목
	private String reportSubmitTitle;
	
	// 과제제출 내용
	private String reportSubmitContent;
	
	// 과제 점수 
	private int reportSubmitPoint;
	
	// 과제 피드백
	private String reportSubmitFeedback;
	
	// 과제제출 입력날짜
	private String reportSubmitCreateDate;
	
	// 과제제출 수정날짜
	private String reportSubmitUpdateDate;
	
	// 과제제출 첨부파일 리스트
	private List<MultipartFile> ReportSubmitFileList;
}
