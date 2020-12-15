package gd.fintech.lms.student.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.student.vo.ClassRegistration;

@Mapper
public interface ClassRegistrationCancelMapper {
	List<ClassRegistration> selectClassRegistrationByPage(Map<String,Integer>Map);
	//학생이 신청한 수강 목록
	
	int deleteClassRegistrationcancel(int classRegistrationNo);
	//수강 취소(삭제)
	
	int insertCancel(String cancelContent);
	//취소 사유 입력
}
