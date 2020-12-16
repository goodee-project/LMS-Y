package gd.fintech.lms.student.vo;

import java.util.List;

import gd.fintech.lms.manager.vo.Subject;
import lombok.Data;
// 학생이 수강신청시 필요한 목록,상태 vo
@Data
public class ClassRegistration {
	//학생 수강신청 번호
	private int classRegistrationNo;
	
	//강좌번호
	private int lectureNo;
	
	//학생id
	private String accountId;
	
	//수강상태 '대기','수강중','수료','과락','취소'
	private String classRegistrationState;	
	
	//학생이 매기는 수강점수
	private String classRegistrationPoint;
	
	//학생이 수강에대한 리뷰
	private String classRegistrationReview;
	
	//학생이 신청한 날짜
	private String classRegistrationCreateDate;
	
	//학생이 수강신청한 수강의 과목상세보기
	private List<Subject>subjectInfoList;
	
	
				
}
