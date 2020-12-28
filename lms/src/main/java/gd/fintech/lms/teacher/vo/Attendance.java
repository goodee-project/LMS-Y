package gd.fintech.lms.teacher.vo;

import java.lang.System.Logger;
import java.util.List;

import gd.fintech.lms.student.vo.Student;
import lombok.Data;


//강사가 학생들을 출석할때 쓰는 VO
//학생들의 출석 여부를 날짜별로 기록함

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
	
	//학생 vo
	private List<Student> student;
}
