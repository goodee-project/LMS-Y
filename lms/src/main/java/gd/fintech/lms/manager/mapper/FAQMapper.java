package gd.fintech.lms.manager.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import gd.fintech.lms.manager.vo.FAQ;





@Mapper 
public interface FAQMapper {
  	List<FAQ> selectFAQListByPage(Map<String, Integer> map);   //FAQ 목록 페이지 
  	
	int selectFAQTotalCount();//FAQ  개수
	int insertFAQ(FAQ faq); // FAQ 입력
	int updateFAQ(FAQ faq); // FAQ 수정
	int deleteFAQ(int faqNo); // FAQ 삭제
	FAQ selectFAQOne(int faqNo); // FAQ 상세보기

	
	
	
}
