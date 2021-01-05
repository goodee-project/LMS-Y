package gd.fintech.lms.student.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.student.vo.ClassRegistration;
// 수강신청
@Mapper
public interface ClassRegistrationMapper {
	
	//학생이 신청한 수강목록 리스트 페이징
	//매개변수: map에 accountId,currentPage 넣어줌
	//리턴값: 수강신청한 목록 리스트
	List<ClassRegistration> selectClassRegistrationListByPage(Map<String,Object>map);
	 
	//수강 신청한 수강 과목 상세보기(과목 정보보기)
	//매개변수:과목의 번호
	//리턴값:과목의 정보
	ClassRegistration selectClassRegistrationOne(int subjectNo);
	
	//학생\이 수강신청한 갯수 
	//매개변수:학생의Id 
	//리턴값: 학생의 수강신청 수
	int selectRegistrationCount(String accountId);
	
	//수강신청이 가능한 모든 항목 리스트 페이징
	//매개변수:map에 수강번호,currentPage 입력
	//리턴값:수강신청이 가능한 모든 항목
	List<ClassRegistration> selectClassRegistrationAll(Map<String,Object>map);
	
	//모든 수강신청 가능한 강좌 갯수
	//매개변수:강좌 번호
	//리턴값:수강 신청이 가능한 강좌의 수
	int selectRegistrationAllCount();
}
