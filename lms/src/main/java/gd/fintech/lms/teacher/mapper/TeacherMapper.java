package gd.fintech.lms.teacher.mapper;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.teacher.vo.Teacher;

//강사 Mapper

@Mapper
public interface TeacherMapper {
	//강사 상세보기
	Teacher selectTeacherOne(String teacher);
	
	//강사 정보 수정
	int updateTeacherInfo(Teacher teacher);
	
}
