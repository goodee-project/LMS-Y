package gd.fintech.lms.teacher.vo;

import lombok.Data;

//강사가 학생들을 출석할때 쓰는 VO
//학생들의 출석 여부를 날짜별로 기록함
@Data
public class Attendance {
	private int lectureNo;//강좌아이디
	private String accountId;//계정 아이디(학생)
	private String attendanceDay;//출석 날짜
	private String attendanceCreateDate;//출석 입력날짜
	private String attendanceUpdateDate;//출석 수정날짜
	private String attendanceState;//출석 유형
	private String attendanceRemark;//출석 비고
}
