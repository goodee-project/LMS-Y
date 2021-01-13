package gd.fintech.lms.student.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.student.vo.Attendance;

@Mapper
public interface StudentAttendanceMapper {
	
	//학생 별 출석 리스트
	//매개변수:currentPage,lectureNo
	//리턴값:강좌별 출석리스트
	List<Attendance> studentAttendanceListByPage(Map<String,Object>map);
	
	//학생 출석 전체 갯수
	//매개변수:학생id
	//리턴값:학생들의 출석 갯수
	int attendanceCount(String accountId);
	

}
