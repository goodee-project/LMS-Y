package gd.fintech.lms.student.mapper;

import java.util.List;
import java.util.Map;

import gd.fintech.lms.student.vo.ClassRegistration;

public interface ClassRegistrationMapper {
	List<ClassRegistration> selectClassRegistrationListByPage(Map<String,Integer>Map);
	//학생이 신청한 수강목록 리스트 페이징 
	
	int selectClassRegistrationAll();
	//수강신청한 학생 전체보기
	
	List<ClassRegistration> selectClassRegistrationOne();
	//수강 신청한 학생 상세보기(정보보기)
	
}
