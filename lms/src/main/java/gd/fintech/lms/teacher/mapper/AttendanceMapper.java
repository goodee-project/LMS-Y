package gd.fintech.lms.teacher.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.teacher.vo.Attendance;

//출석 Mapper

@Mapper
public interface AttendanceMapper {
	//출석에 필요한 학생 목록 메서드
	List<Attendance> selectAttendanceStudentList(Map<String, Object> map);
	
	//출석에 필요한 학생 정보 상세보기
	Attendance selectAttendanceStudentOne(Map<String, Object> map);
	
	//출석 입력
	//매개변수:setter를 사용하여 attenaanceState 및 attendanceRemark 넣음
	//리턴값:변경된 행 갯수
	int insertAttendance(Map<String, Object> map);
	
	//출석 수정
	//매개변수:강좌아이디 및 년/월/일에 따라 출석 날짜 정보
	//리턴값:변경된 행 갯수
	int updateAttendance(Attendance attendance);
	
	
}
