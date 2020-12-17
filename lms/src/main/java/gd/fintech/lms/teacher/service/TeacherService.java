package gd.fintech.lms.teacher.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gd.fintech.lms.teacher.mapper.TeacherMapper;
import gd.fintech.lms.teacher.vo.Teacher;

@Service
@Transactional
public class TeacherService {
	//TeacherMapper 객체 주입
	@Autowired private TeacherMapper teacherMapper;
	
	//강사 아이디를 조회하여 정보결과를 가져오는 메서드
	//매개변수:로그인뷰에 넣은 계정 ID
	//리턴값:로그인한 계정 ID에 값이 있는 ID에 관련된 정보를 반환
	public Teacher getTeacherOne(String accountId){
		Teacher teacher = teacherMapper.selectTeacherOne(accountId);
		return teacher;

	}
	
	//강사 아이디를 조회아여 정보를 수정하게 해주는 메서드
	//매개변수:로그인뷰에 넣은 계정 ID
	//리턴값:수정된 값
	public int getTeacherUpdate(Teacher teacher) {
		return teacherMapper.updateTeacherInfo(teacher);
	}
}
