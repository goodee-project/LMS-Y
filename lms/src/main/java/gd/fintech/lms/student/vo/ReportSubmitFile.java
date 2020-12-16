package gd.fintech.lms.student.vo;

import lombok.Data;

// 과제제출 첨부파일 vo : 과제제출에 올라갈 파일의 vo

@Data
public class ReportSubmitFile {
	// 과제제출 파일UUID
	private String reportSubmitFileUUID;
	
	// 과제제출 파일의 원래 이름
	private String reportSubmitFileOriginal;
	
	// 과제제출 번호
	private int reportSubmitNo;
	
	// 과제제출 파일 사이즈
	private long reportSubmitFileSize;
	
	// 과제제출 파일 확장자
	private String reportSubmitFileType;
	
	// 과제제출 파일 다운로드수
	private int reportSubmitFileCount;
	
	// 과제제출 파일 입력날짜
	private String reportSubmitFileCreateDate;
}
