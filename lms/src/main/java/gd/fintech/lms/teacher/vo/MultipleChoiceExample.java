package gd.fintech.lms.teacher.vo;

import lombok.Data;

// 객관식 문제별로 등재된 보기 관련 정보 vo

@Data
public class MultipleChoiceExample {
	// 객관식 보기 고유번호 (PK, AUTO_INCREMENT)
	private int multipleChoiceExampleNo;
	
	// 객관식 보기를 등록한 객관식 문제의 고유번호 (FK)
	private int multipleChoiceNo;
	
	// 시험지에 표시되는 객관식 보기의 번호
	private String multipleChoiceExampleId;
	
	// 객관식 보기 내용
	private String multipleChoiceExampleContent;
	
	// 이 행을 생성한 날짜 (사용자 임의 기입 불가, NOW())
	private String multipleChoiceExampleCreateDate;
	
	// 이 행을 수정한 날짜 (사용자 임의 기입 불가, NOW())
	private String multipleChoiceExampleUpdateDate;
}
