package gd.fintech.lms.student.vo;

import lombok.Data;

@Data
public class AnswerSheet {
	private int multipleChoiceNo;	//객관식 답안 번호
	private String acountId;		// 학생id
	private String answerSelect;	//답 선택지 (ENUM)
	private String answerScore;		//점수
}
