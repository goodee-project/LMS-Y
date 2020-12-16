package gd.fintech.lms.student.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gd.fintech.lms.student.mapper.StudentMapper;
import gd.fintech.lms.student.vo.Student;

@Service
@Transactional
public class StudentService {
	@Autowired StudentMapper studentMapper;

	//학생 자신의 정보 상세보기
	//
	//
	public List<Student> getStudentOne(String accountId){
		return studentMapper.selectStudentOne(accountId);
	}
	
	//학생 자신의 정보 수정폼
	//
	//
	public Student setStudentUPdateForm(String accountId) {
		return studentMapper.updateStudentForm(accountId);
	}
	
	//학생 자신의 정보 수정액션
	//
	//
	public int getStudentUpdate(Student student) {
		return studentMapper.updateStudent(student);
	}
}
