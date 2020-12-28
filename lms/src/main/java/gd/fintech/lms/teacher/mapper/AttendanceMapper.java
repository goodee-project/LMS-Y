package gd.fintech.lms.teacher.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.teacher.vo.Attendance;

//출석 Mapper

@Mapper
public interface AttendanceMapper {
	//출석에 필요한 달력 메서드
	//리턴값: 출석에 필요한 달력출력
	List<Attendance> selectCalendarAttendanceList(Map<String, Object> map);
	
	//출석 학생목록 상세보기
	//리턴값: 학생목록 출력
	List<Attendance> selectCalendarAttendanceListOne(Map<String,Object>map);
	
	//출석 입력
	//매개변수:setter를 사용하여 attenaanceState 및 attendanceRemark 넣음
	//리턴값:변경된 행 갯수
	int insertAttendance(Attendance attendance);
	
	//출석 수정
	//매개변수:강좌아이디에 따라 출석 날짜 정보
	//리턴값:변경된 행 갯수
	int updateAttendance(Attendance attendance);
	
	
}
