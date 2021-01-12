package gd.fintech.lms.student.vo;

import lombok.Data;

@Data
public class MultipleChoice {
	//객관식 고유번호 
	private int multiplechoiceNo;
	//강좌의 고유번호 
	private int lectureNo;
	//문제의 번호 
	private String multiplechoiceId;
	//객관식 문제(txt) 
	private String multiplechoiceQuestion;
	//객관식 답변(객관식 선택) 
	private String multiplechoiceAnswer;
	//객관식 점수->객관식 문제에 대한 점수 
	private int multiplechoiceScore;
	//문제 생성일
	private String multiplechoiceCreateDate;
	//문제 수정일
	private String multiplechoiceUpdateDate;

	
}
