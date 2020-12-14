package gd.fintech.lms.student.vo;

import lombok.Data;
//학생이 질문을 등록하는 질문 게시판 vo
@Data
public class Question {
	
	private int question_no; // 번호
	private int lecture_no; // 강좌번호
	private String account_id; // 학생의 id
	private String question_writer; // 질문 작성자
	//진행중~
}
