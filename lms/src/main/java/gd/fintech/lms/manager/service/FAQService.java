package gd.fintech.lms.manager.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gd.fintech.lms.manager.mapper.FAQCategoryMapper;
import gd.fintech.lms.manager.mapper.FAQMapper;
import gd.fintech.lms.manager.vo.FAQ;
import gd.fintech.lms.manager.vo.FAQCategory;

// FAQ 와 FAQCategory 를 관리하는 서비스 

@Service
@Transactional
public class FAQService {
	//Logger
	private final Logger logger = LoggerFactory.getLogger(FAQService.class);
	
	// FAQ에 대한 메퍼
	@Autowired private FAQMapper faqMapper;
	
	// FAQ Category에 대한 매퍼
	@Autowired private FAQCategoryMapper faqCategoryMapper;
	
	
	// FAQ 리스트를 보여주는 서비스
	// 매개변수: 현재 페이지, 보여줄 데이터 개수
	// 리턴값: 현재 페이지의 FAQ 리스트
	public List<FAQ>getFAQListByPage(int currentPage, int rowPerPage){
		int beginRow = (currentPage-1)*rowPerPage;
		Map<String,Integer>map = new HashMap<String, Integer>();
		
		map.put("beginRow", beginRow);
		logger.debug("beginRow "+beginRow );
		
		map.put("rowPerPage", rowPerPage);
		logger.debug("rowPerPage" +rowPerPage );
		
		return faqMapper.selectFAQListByPage(map);
		
	}
	
	// FAQ를 입력하는 서비스
	// 매개변수: FAQ의 정보 
	// 리턴값: 입력받은 정보가 들어간 행의 추가
	public int createFAQ(FAQ faq){
		return faqMapper.insertFAQ(faq);
		
	}
	
	// FAQ를 수정하는 서비스 
	// 매개변수: FAQ의 정보
	// 리턴값:  선택한 행을 입력받은 정보로 수정
	public int modifyFAQ(FAQ faq){
		return faqMapper.updateFAQ(faq);
	}
	
	// FAQ를 상세히 보는 서비스
	// 매개변수: FAQ의 
	// 리턴값: faqNo에  대한 상세한 정보 
	public FAQ  getFAQDetail (int faqNo) {
		return faqMapper.selectFAQDetail(faqNo);
	}
	
	// 선택한 FAQ의 게시글을 지우는 서비스
	// 매개변수: FAQ에서 선택한 1
	// 리턴값: faqNo의 행 삭제
	public int removeFAQ(int faqNo) {
		return faqMapper.deleteFAQ(faqNo);	
	}
	
	// FAQ의 카테고리의 목록 
	// 리턴값: FAQ의 카테고리의 리스트
	public List<FAQCategory>getFAQCategoryLisqt(){
		 return faqCategoryMapper.selectFAQCategoryList();
	}
	
	// FAQ의 카테고리를 입력하는 서비스 
	// 매개변수: FAQCateogry 에 대한 정보
	// 리턴값: 입력받은 정보가 추가된 행의 개수
	public int createFAQCategory(FAQCategory faqCategory) {
		return faqCategoryMapper.insertFAQCategory(faqCategory);
	}
	
	// FAQ의 카테고리를 수정하는 서비스 
	// 매개변수: FAQCategory에 대한 정보 
	// 리턴값: 선택한 행을 입력받은 정보로 수정
	public int modifyFAQCategory(FAQCategory faqCategory){
		return faqCategoryMapper.updateFAQCategory(faqCategory);
	}
	

}
