package gd.fintech.lms.teacher.vo;

import lombok.Data;

// 강의계획서 첨부파일 vo

@Data
public class SyllabusFile {
	// 강의계획서 첨부파일 UUID
	private String syllabusFileUUID;
	
	// 강의계획서 첨부파일 원본 이름
	private String syllabusFileOriginal;
	
	// 강의계획서 고유번호
	private int lectureNo;
	
	// 강의계획서 첨부파일 크기
	private long syllabusFileSize;
	
	// 강의계획서 첨부파일 타입
	private String syllabusFileType;
	
	// 강의계획서 첨부파일 다운로드 횟수
	private int syllabusFileCount;
	
	// 강의계획서 첨부파일 생성일자
	private String syllabusFileCreateDate;
}
