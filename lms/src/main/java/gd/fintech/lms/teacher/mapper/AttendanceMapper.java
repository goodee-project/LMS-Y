package gd.fintech.lms.teacher.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.teacher.vo.Attendance;

//출석 Mapper
@Mapper
public interface AttendanceMapper {
	List<Attendance> selectStudentList(); //출석에 필요한 학생 목록
	int insertAttendance(Attendance attendance);//출석 입력
	int updateAttendance(Attendance attendance);//출석 수정
	
	
}
