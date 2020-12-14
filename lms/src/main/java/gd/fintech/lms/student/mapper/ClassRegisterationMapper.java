package gd.fintech.lms.student.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.student.vo.ClassRegistration;

@Mapper
public interface ClassRegisterationMapper {
	
	List<ClassRegistration> selectClassRegistrationListByPage(Map<String,Integer>Map);
	//학생이 신청한 수강목록 리스트 페이징 
	
	int selectClassRegistrationAll();
	//수강신청한 학생 전체보기
	
	List<ClassRegistration> selectClassRegistrationOne();
	//수강 신청한 학생 상세보기(정보보기)
	//내부에서 수강 신청/취소
	
	//고민해봐야함 - > cancle페이지 따로 만들건지 바로 폼으로 넘어갈지
	ClassRegistration updateClassRegistrationForm(String studentId);
	//수강 신청 취소페이지 폼
	//수정페이지-> 취소페이지+ 사유
	
	//수강신청 취소 목록
}
