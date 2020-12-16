package gd.fintech.lms.manager.mapper;

import java.util.List;


import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.manager.vo.FAQCategory;

// FAQ의 카테고리에 대한 메퍼 인터페이스

@Mapper
public interface FAQCategoryMapper {
	// FAQ의 카테고리 목록 
	// 리턴값: 카테고리 리스트
	List<FAQCategory> selectFAQCategoryList(); 
	
	// FAQ카테고리 입력
	// 매개변수: 카테고리 정보
	// 리턴값: 행의 추가 
	int insertFAQCategory(FAQCategory faqCategory); 
		
	// FAQ카테고리 수정
	// 매개변수: 카테고리 수정
	// 리턴값: 행의 수정
	int updateFAQCategory(FAQCategory faqCategory);
		
	// FAQ카테고리 삭제
	// 매개변수: 카테고리  
	// 리턴값:  행의 삭제
	int deleteFAQCategory(int faqCategoryNo);	
	

}
