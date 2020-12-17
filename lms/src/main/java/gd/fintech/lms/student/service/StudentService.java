package gd.fintech.lms.student.service;

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
	//매개변수:학생의 계정번호
	//리턴값: 학생의 정보를 출력
	public Student getStudentOne(String accountId){
		return studentMapper.selectStudentOne(accountId);
	}
}
