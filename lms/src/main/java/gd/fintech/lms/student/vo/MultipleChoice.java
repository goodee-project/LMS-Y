package gd.fintech.lms.student.vo;

import java.util.List;

import gd.fintech.lms.teacher.vo.MultipleChoiceExample;
import lombok.Data;

// 객관식 문제 VO

@Data
public class MultipleChoice {
	// 객관식 고유번호 
	private int multipleChoiceNo;
	
	// 강좌의 고유번호 
	private int lectureNo;
	
	// 문제의 번호 
	private String multipleChoiceId;
	
	// 객관식 문제 (txt)
	private String multipleChoiceQuestion;
	
	// 객관식 답변 (객관식 선택) 
	private String multipleChoiceAnswer;
	
	// 객관식 점수->객관식 문제에 대한 점수 
	private int multipleChoiceScore;
	
	// 문제 생성일
	private String multipleChoiceCreateDate;
	
	// 문제 수정일
	private String multipleChoiceUpdateDate;
	
	// 문제 보기 목록
	private List<MultipleChoiceExample> multipleChoiceExampleList;
}
