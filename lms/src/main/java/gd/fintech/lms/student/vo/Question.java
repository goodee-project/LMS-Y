package gd.fintech.lms.student.vo;

import java.util.List;

import gd.fintech.lms.teacher.vo.QuestionComment;
import gd.fintech.lms.teacher.vo.QuestionCommentFile;
import lombok.Data;
//학생이 질문을 등록하는 질문 게시판 vo
@Data
public class Question {
	//해당 질문의 번호 (AutoIncrement)
	private int questionNo;
	
	//강좌번호
	private int lectureNo;
	
	//학생의 id
	private String accountId;
	
	//질문 작성자
	private String questionWriter;
	
	//질문 제목
	private String questionTitle;		
	
	//질문 내용
	private String questionContext;		
	
	//질문 생성 날짜
	private String questionCreateDate;	
	
	//질문 수정 날짜
	private String questionUpdateDate;	
	
	//질문 조회수
	private int questionCount;
	
	//질문 비밀번호 (비밀글 사용시)
	private String questionPassword;
	
	//질문게시판 강사의 댓글
	private List<QuestionComment>questionComment;
	
	//질문게시판 강사의 파일
	private List<QuestionCommentFile>questionCommnetFile;
	
}
