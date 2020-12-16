package gd.fintech.lms.student.mapper;

import java.util.List;
import java.util.Map;

import gd.fintech.lms.student.vo.ClassRegistration;

public interface ClassRegistrationMapper {
	
	//학생이 신청한 수강목록 리스트 페이징
	//매개변수: map에 beginRow,rowPerPage 넣어줌
	//리턴값: 수강신청한 목록 리스트
	List<ClassRegistration> selectClassRegistrationListByPage(Map<String,Integer>Map);
	 
	//고민해보기
	//수강 신청한 학생 상세보기(정보보기)
	//매개변수: 
	//리턴값:
	List<ClassRegistration> selectClassRegistrationOne();
	
	
}
