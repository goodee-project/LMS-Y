package gd.fintech.lms.teacher.vo;

import lombok.Data;

// 시험의 객관식 문제 관련 정보 vo
@Data
public class MultipleChoice {
	private int multipleChoiceNo;				// 객관식 문제 고유번호 (AUTO_INCREMENT)
	private int lectureNo;						// 객관식 문제를 등록한 시험이 있는 강좌 고유번호 (강좌당 시험은 1회만 시행)
	private int multipleChoiceId;				// 시험지에 표시되는 객관식 문제의 번호
	private String multipleChoiceQuestion;		// 객관식 문제 내용
	private String multipleChoiceAnswer;		// 객관식 문제 정답
	private String multipleChoiceCreateDate;	// 이 행을 생성한 날짜
	private String multipleChoiceUpdateDate;	// 이 행을 수정한 날짜
}
