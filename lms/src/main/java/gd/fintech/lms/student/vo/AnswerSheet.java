package gd.fintech.lms.student.vo;

import lombok.Data;
// 학생이 제출하는 답안지의 vo
@Data
public class AnswerSheet {
	//객관식 답안 번호
	private int multipleChoiceNo;
	
	//학생id
	private String accountId;
	
	//답 선택지 (ENUM)
	private String answerSelect;
	
	//점수
	private String answerScore;		
}
