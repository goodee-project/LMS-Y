package gd.fintech.lms.student.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

//수강신청 취소 게시판
@Mapper
public interface ClassRegistrationCancelMapper {
	
	//수강 취소(삭제)
	//매개변수: 신청한 수강의 번호 
	//리턴값: 수강신청 목록(삭제됨)
	int updateClassRegistration(int classRegistrationNo);
	
	//취소 사유 입력
	//매개변수: 취소(거절)사유
	//리턴값: 취소(거절)에 대한 내용
	int insertCancel(Map<String, Object> map);
	
	
	
}
