package gd.fintech.lms.teacher.vo;

import java.util.List;

import lombok.Data;

// 강의별 질문게시판의 댓글 관련 정보 vo

@Data
public class QuestionComment {
	// 덧글 고유번호 (PK, AUTO_INCREMENT)
	private int questionCommentNo;
	
	// 덧글을 등록한 특정 질문의 고유번호 (FK)
	private int questionNo;
	
	// 덧글 작성자의 계정 ID
	private String accountId;
	
	// 덧글 내용
	private String questionCommentContent;
	
	// 이 행을 생성한 날짜 (사용자 임의 기입 불가, NOW())
	private String questionCommentCreateDate;
	
	// 이 행을 수정한 날짜 (사용자 임의 기입 불가, NOW())
	private String questionCommentUpdateDate;
	
	// 해당 질문을 참조하고 있는 첨부파일 리스트
	private List<QuestionCommentFile> questionCommentFileList;
}
