package gd.fintech.lms.dto;

import java.util.List;

import lombok.Data;

// 시험의 객관식 문제 및 객관식 문제 보기에 대한 DTO

@Data
public class MultipleChoiceForm {
	// 객관식 문제 고유번호 (PK, AUTO_INCREMENT)
	private int multipleChoiceNo;
	
	// 객관식 문제를 등록한 시험이 있는 강좌 고유번호 (FK)
	private int lectureNo;
	
	// 시험지에 표시되는 객관식 문제의 번호
	private int multipleChoiceId;
	
	// 객관식 문제 내용
	private String multipleChoiceQuestion;
	
	// 객관식 문제 정답
	private String multipleChoiceAnswer;
	
	// 객관식 문제에 부여된 점수
	private int multipleChoiceScore;
	
	// 이 행을 생성한 날짜 (사용자 임의 기입 불가, NOW())
	private String multipleChoiceCreateDate;
	
	// 이 행을 수정한 날짜 (사용자 임의 기입 불가, NOW())
	private String multipleChoiceUpdateDate;
	
	// 시험 객관식 보기 목록
	private List<String> multipleChoiceExampleList;
}
