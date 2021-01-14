package gd.fintech.lms.manager.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gd.fintech.lms.manager.mapper.FAQMapper;
import gd.fintech.lms.manager.mapper.ManagerMapper;
import gd.fintech.lms.manager.vo.FAQ;

// FAQ 를 관리하는 서비스 

@Service
@Transactional
public class FAQService {
	//debugLogger
	private final Logger logger = LoggerFactory.getLogger(FAQService.class);
	
	// FAQ에 대한 메퍼
	@Autowired private FAQMapper faqMapper;
	
	// 운영자 관련 메퍼
	@Autowired private ManagerMapper managerMapper;
	
	// FAQ 리스트를 보여주는 서비스
	// 매개변수: 현재 페이지, 카테고리에 해당하는 FAQ 리스트
	// 리턴값: 현재 페이지의 FAQ 리스트
	public Map<String, Object> getFAQListByPage(int currentPage, String categoryFaqSearch){
		//  페이지당 표시되는 데이터 수
		int rowPerPage = 15;
		// 현재 페이지에서 시작하는 데이터 
		int beginRow = (currentPage - 1) * rowPerPage;
		// 전체 페이지 개수
		int countFAQ = faqMapper.selectFAQCount(categoryFaqSearch);
		// 마지막 페이지
		int lastPage = countFAQ / rowPerPage;
		if (countFAQ % rowPerPage != 0) {
			lastPage += 1;
		} 
		// 마지막 페이지가 0일때 현재페이지0
		if (lastPage == 0) {
			currentPage = 0;
		}
		
		// 페이지 네비에서 표시할 페이지 수 
		int navPerPage = 10;
		// 페이지 네비에서의 처음 페이지	                  
		int navBeginPage = (currentPage-1)/navPerPage*navPerPage + 1;
		// 페이지 네비에서의  마지막 페이지
		int navLastPage = (navBeginPage + navPerPage) - 1;
		
		// navLastPage 가 lastPage 보다 크면 navLastPage의 값은 lastPage
		if (navLastPage > lastPage) {
			navLastPage = lastPage;
		}
		
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("beginRow", beginRow);
		map.put("rowPerPage",rowPerPage);
		map.put("categoryFaqSearch", categoryFaqSearch);
		
		Map<String, Object> paramMap = new HashMap<String,Object>();
		List<FAQ> faqList = faqMapper.selectFAQListByPage(map);
		paramMap.put("faqList",faqList);
		paramMap.put("lastPage",lastPage);
		paramMap.put("navBeginPage",navBeginPage);
		paramMap.put("navLastPage",navLastPage);
		paramMap.put("navPerPage",navPerPage);
		return paramMap;
	}
	
	// FAQ를 입력하는 서비스
	// 매개변수: FAQ의 정보 
	// 리턴값: 입력받은 정보가 들어간 행의 추가
	public int createFAQ(FAQ faq, HttpSession session){
		// 세션을 가져오고 
		String sessionAcountId = (String)session.getAttribute("accountId");
		logger.debug("현재 로그인한 사용자 ID:"+sessionAcountId);
		faq.setAccountId(sessionAcountId);
	    faq.setFaqWriter(managerMapper.selectManagerName(sessionAcountId));
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
	public FAQ getFAQDetail(int faqNo) {
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
	public int increaseFAQCountUp(int faqNo) {
		return faqMapper.updateFAQCountUp(faqNo);
	}
	

}
