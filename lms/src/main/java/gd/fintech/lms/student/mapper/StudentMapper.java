package gd.fintech.lms.student.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.student.vo.Student;
//학생의 정보를 보여주는 게시판
@Mapper
public interface StudentMapper {
	//학생 회원가입 정보(기본정보)
	//매개변수:학생Vo
	//리턴값:해당하는 행 수정한 값
	int insertStudentFromQueue(Map<String, Object> map);
	
	//학생 상세보기(정보보기)
	//매개변수: 학생계정의id
	//리턴값: 학생의 모든 정보(id,email,name,phone,gender,birth,address,image,accessdate)
	Student selectStudentOne(String accountId);
	
	//학생 정보 수정액션
	//매개변수: 학생Vo
	//리턴값:해당 행 수정한 값
	int updateStudent(Student student);
	
	//학생이 볼 과제
	//매개변수: 학생계정id
	//리턴값:해당행 출력
	List<Student> selectReportOne(String accountId);
	
}
