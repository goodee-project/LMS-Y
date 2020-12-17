package gd.fintech.lms.student.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.student.vo.ClassRegistration;
//수강신청 게시판
@Mapper
public interface ClassRegistrationCancelMapper {
	
	//학생이 신청한 수강 목록
	//매개변수: map에 beginRow,rowPerPage 넣어줌
	//리턴값: 수강신청 리스트
	List<ClassRegistration> selectClassRegistrationByPage(Map<String,Integer>Map);
	
	//수강 취소(삭제)
	//매개변수: 신청한 수강의 번호 
	//리턴값: 수강신청 목록(삭제됨)
	int deleteClassRegistrationcancel(int classRegistrationNo);
	
	//취소 사유 입력
	//매개변수: 취소(거절)사유
	//리턴값: 취소(거절)에 대한 내용
	int insertCancel(String cancelContent);
	
}
