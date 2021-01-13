package gd.fintech.lms.manager.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import gd.fintech.lms.manager.vo.FAQ;

// FAQ에 대한 메퍼 인터페이스

@Mapper 
public interface FAQMapper {
	// FAQ 목록 페이지 
	// 매개변수: MAP beginRow, rowPerPage
	// 리턴값: FAQ 리스트
  	List<FAQ> selectFAQListByPage(Map<String, Object> map);  
  	
  	// FAQ 개수
  	// 매개변수: 선택한 faqCategory
  	// 리턴값: FAQ의 행의 총 개수
	int selectFAQCount(String categoryFaqSearch);
	
	// FAQ 입력
	// 매개변수: FAQ의 정보  
	// 리턴값: 행의 추가
	int insertFAQ(FAQ faq); 
	
	// FAQ 수정
	// 매개변수: FAQ의 정보
	// 리턴값: 선택한 행의 수정
	int updateFAQ(FAQ faq);
	
	// FAQ 삭제
	// 매개변수: FAQ의 번호
	// 리턴값: 선택한 행의 삭제
	int deleteFAQ(int faqNo); 
	
	// FAQ 상세보기
	// 매개변수: FAQ의 번호
	// 리턴값: 선택한 FAQ번호 해당하는 FAQ 상세한 정보  
	FAQ selectFAQDetail(int faqNo); 
	
	// FAQ count+1
	// 매개변수: FAQ의 번호
	// 리턴값: 선택한 FAQ의 번호에 해당하는 FAQ의 조회수 1증가
	int updateFAQCountUp(int faqNo);  
	
}
