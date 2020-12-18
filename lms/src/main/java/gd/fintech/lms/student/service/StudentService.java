package gd.fintech.lms.student.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gd.fintech.lms.student.mapper.StudentMapper;
import gd.fintech.lms.student.vo.Student;

@Service
@Transactional
public class StudentService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired StudentMapper studentMapper;

	//학생 자신의 정보 상세보기
	//매개변수:학생의 계정번호
	//리턴값: 학생의 정보를 출력
	public Student getStudentDetail(String accountId){
		Student student=studentMapper.selectStudentOne(accountId);
		return student;
	}
	
	//학생 과제 보기
	//매개변수:학생의 게정번호
	//리턴값: 학생번호에 해당하는 과제
	public Student getStudentResult(String accountId) {
		return studentMapper.resultStudentReport(accountId);
	}
	
	//학생 회원가입 정보(기본정보)
	//매개변수:학생의 
	//리턴값: 승인대기 
	public int createStudentFromQueue(Student student) {
		return studentMapper.insertStudentFromQueue(student);
	}
	
}
