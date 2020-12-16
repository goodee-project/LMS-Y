package gd.fintech.lms.teacher.vo;

import lombok.Data;

// 강의별 질문게시판의 첨부파일 관련 정보 vo

@Data
public class QuestionCommentFile {
	// 첨부파일 고유 식별자 (PK, UUID)
	private String questionCommentFileUUID;
	
	// 원본 첨부파일명
	private String questionCommentFileOriginal;
	
	// 첨부파일을 등록한 특정 질문의 고유번호 (FK)
	private int questionCommentNo;
	
	// 첨부파일 용량
	private long questionCommentFileSize;
	
	// 첨부파일 MIME 타입
	private String questionCommentFileType;
	
	// 첨부파일 다운로드 횟수 (기본값 0)
	private int questionCommentFileCount;
	
	// 이 행을 생성한 날짜 (사용자 임의 기입 불가, NOW())
	private String questionCommentFileCreateDate;
}
