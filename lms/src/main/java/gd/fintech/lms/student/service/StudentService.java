package gd.fintech.lms.student.service;

import java.util.List;

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
	
	//학생 자신의 정보 수정하기
	//매개변수:학생의 정보
	//리턴값:수정된 값
	public int modifyStudent(Student student) {
		return studentMapper.updateStudent(student);
	}
	
	//학생이 볼 해당 강좌의 자신의 과제
	//매개변수:학생의 id
	//리턴값:학생에 해당하는 과제물 출력
	public List<Student> getReportDetail(String accountId) {
		return studentMapper.selectReportOne(accountId);
	}
	
}
