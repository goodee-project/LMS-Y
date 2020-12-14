package gd.fintech.lms.teacher.vo;

import lombok.Data;

// 강의별 질문게시판의 첨부파일 관련 정보 vo
@Data
public class QuestionCommentFile {
	private String questionCommentFileUUID;			// 첨부파일 고유 식별자 (UUID)
	private String questionCommentFileOriginal;		// 원본 첨부파일명
	private int questionCommentNo;					// 첨부파일을 등록한 특정 질문의 고유번호
	private int questionCommentFileSize;			// 첨부파일 용량
	private String questionCommentFileType;			// 첨부파일 MIME 타입
	private int questionCommentFileCount;			// 첨부파일 다운로드 횟수
	private String questionCommentFileCreateDate;	// 이 행을 생성한 날짜
}
