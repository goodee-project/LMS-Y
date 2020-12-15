package gd.fintech.lms.student.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.student.vo.Student;


@Mapper
public interface StudentMapper {
	
	//학생 정보 입력
	int insertStudent(Student student);
	
	//학생리스트 페이징
	List<Student> selectStudentListByPage(Map<String,Integer>Map);
	
	//학생 상세보기(정보보기)
	List<Student> selectStudentOne();
	
	//학생 정보 수정 폼
	Student updateStudentForm(String studentId);
	
	//학생 정보 수정액션
	int updateStudent(Student student);
	
	//학생 삭제 
	int deleteStudent(String studentId);
	
	
}
