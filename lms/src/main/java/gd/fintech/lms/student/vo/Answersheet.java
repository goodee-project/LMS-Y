package gd.fintech.lms.student.vo;

import lombok.Data;
//학생이 제출하는 답안지
@Data
public class Answersheet {
	private int multiplechoiceNo; // 객관식 선택
	private String acountId; // 학생id
	private String answerSelect; //학생이 선택한 번호
	private int answerScore; // 문제의 점수
	
}
