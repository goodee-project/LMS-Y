package gd.fintech.lms.student.mapper;

import java.util.List;


import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.student.vo.Student;
//학생의 정보를 보여주는 게시판
@Mapper
public interface StudentMapper {
	//학생 상세보기(정보보기)
	//매개변수: 
	//리턴값: 학생들의 모든 정보(id,email,name,phone,gender,birth,address,image,accessdate)
	List<Student> selectStudentOne(String accountId);
	
	//학생 정보 수정 폼
	//매개변수: 학생Vo값
	//리턴값:해당 행을 수정
	Student updateStudentForm(String accountId);
	
	//학생 정보 수정액션
	//매개변수: 학생Vo
	//리턴값:해당 행 수정한 값
	int updateStudent(Student student);
	
}
