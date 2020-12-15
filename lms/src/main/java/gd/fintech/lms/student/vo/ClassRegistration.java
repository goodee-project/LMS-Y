package gd.fintech.lms.student.vo;

import lombok.Data;
// 학생수강 신청
@Data
public class ClassRegistration {
	private int classRegistrationNo;			// 학생 수강신청 목록
	private int lectureNo;						// 강좌번호
	private String accountId;					// 학생id
	private String classRegistrationState;		// 수강상태 '대기','수강중','수료','과락','취소'
	private String classRegistrationPoint;		// 학생이 매기는 수강점수
	private String classRegistrationReview;		// 학생이 수강에대한 리뷰
	private String classRegistrationCreateDate;	// 학생이 신청한 날짜
	private String cancelCreateDate;			// 학생 수강 취소일
	private String cancelContent;				// 취소 이유(내용)
}
