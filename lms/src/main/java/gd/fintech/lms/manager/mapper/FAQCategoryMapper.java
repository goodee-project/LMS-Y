package gd.fintech.lms.manager.mapper;

import java.util.List;


import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.manager.vo.Classroom;
import gd.fintech.lms.manager.vo.FAQCategory;

// FAQ의 카테고리에 대한 메퍼 인터페이스

@Mapper
public interface FAQCategoryMapper {
	//FAQ 카테고리 
	//리턴값: 카테고리 리스트
	List<FAQCategory> selectFAQCategoryList(); 
	
	// 강의실 입력
	// 매개변수:  강의실 정보
	// 리턴값: 행의 추가 
	int insertFAQCategory(FAQCategory faqCategory); 
		
	// 강의실 수정
	// 매개변수: 강의실 정보
	// 리턴값: 행의 수정
	int updateClassroom(FAQCategory faqCategory);
		
	// 강의실 삭제
	// 매개변수: 강의실 번호 
	// 리턴값:  행의 삭제
	int deleteClassroom(int classroomNo);	
	

}
