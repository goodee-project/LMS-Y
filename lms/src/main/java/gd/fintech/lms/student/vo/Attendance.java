package gd.fintech.lms.student.vo;

import java.util.List;

import gd.fintech.lms.manager.vo.Lecture;
import lombok.Data;

@Data
public class Attendance {
	//강좌아이디
	private int lectureNo;
		
	//계정 아이디(학생)
	private String accountId;
		
	//출석 날짜
	private String attendanceDay;
		
	//출석 입력날짜
	private String attendanceCreateDate;
		
	//출석 수정날짜
	private String attendanceUpdateDate;
		
	//출석 유형
	private String attendanceState;
		
	//출석 비고
	private String attendanceRemark;
		
	//수강신청 vo
	private List<ClassRegistration> classRegistrationList;
	
	//강의 정보 리스트
	private Lecture lectureInfo;
}
