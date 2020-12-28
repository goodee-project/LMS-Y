package gd.fintech.lms.manager.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gd.fintech.lms.manager.mapper.FAQMapper;
import gd.fintech.lms.manager.vo.FAQ;


// FAQ 를 관리하는 서비스 

@Service
@Transactional
public class FAQService {
	//debugLogger
	private final Logger logger = LoggerFactory.getLogger(FAQService.class);
	
	// FAQ에 대한 메퍼
	@Autowired private FAQMapper faqMapper;
	
	
	// FAQ 리스트를 보여주는 서비스
	// 매개변수: 현재 페이지, 보여줄 데이터 개수
	// 리턴값: 현재 페이지의 FAQ 리스트
	public Map<String, Object> getFAQListByPage(int currentPage ){
		// 현재 페이지 표시할 데이터 수
		int rowPerPage = 10;
		// 시작 페이지
		int beginRow = (currentPage - 1) * rowPerPage;
		// 전체 페이지 개수
		int countFAQ = faqMapper.selectFAQCount();
		// 마지막 페이지
		int lastPage = countFAQ / rowPerPage;
		if (countFAQ % rowPerPage == 0) {
			lastPage += 1;
		} 
		if (lastPage == 0) {
			currentPage = 0;
		}
		int navPerPage = 10 ;
		int navFirstPage = currentPage - (currentPage % navPerPage);
		int navLastPage = (navFirstPage + navPerPage)-1;
		
		
		Map<String, Object> map = new HashMap<String,Object>();
	
		
		return map;
	}
	
	// FAQ의 행의 총 합을 보여주는 서비스
	// 리턴값: FAQ 행의 총합
	public int getFAQCount() {
		return faqMapper.selectFAQCount();
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
	// 매개변수: FAQ의 고유번호
	// 리턴값: faqNo에  대한 상세한 정보 
	public FAQ  getFAQDetail (int faqNo) {
		return faqMapper.selectFAQDetail(faqNo);
	}
	
	// 선택한 FAQ의 게시글을 지우는 서비스
	// 매개변수: FAQ의 고유번호
	// 리턴값: faqNo의 행 삭제
	public int removeFAQ(int faqNo) {
		return faqMapper.deleteFAQ(faqNo);	
	}
	
	// FAQ의 조회수가 +1 증가하는 서비스 
	// 매개변수 : FAQ의 고유번호
	// 리턴값: faqNo의 해당하는 count +1 
	public int modifyFAQCountUp(int faqNo) {
		return faqMapper.updateFAQCountUp(faqNo);
	}
	

}
