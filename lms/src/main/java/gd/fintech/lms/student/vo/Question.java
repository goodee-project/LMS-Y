package gd.fintech.lms.student.vo;

import lombok.Data;
//학생이 질문을 등록하는 질문 게시판 vo
@Data
public class Question {
	
	private int question_no; // 번호
	private int lecture_no; // 강좌번호
	private String account_id; // 학생의 id
	private String question_writer; // 질문 작성자
	private String question_title;// 질문 제목
	private String question_contenxt; //질문 내용 
	private int qeustion_count; //질문 조회수
	private String question_password;// 질문 비밀번호
	//진행중
}
