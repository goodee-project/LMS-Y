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

import gd.fintech.lms.AccountLevel;
import gd.fintech.lms.manager.mapper.LMSNoticeMapper;
import gd.fintech.lms.manager.mapper.ManagerMapper;
import gd.fintech.lms.manager.vo.LMSNotice;

// LMS공지사항 서비스

@Service
@Transactional
public class LMSNoticeService {
	// 디버그 logger
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	// lms 공지사항 mapper
	@Autowired private LMSNoticeMapper lmsNoticeMapper;
	// manager mapper
	@Autowired private ManagerMapper managerMapper;
	
	// LMS공지사항의 리스트를 보여주는 페이징 서비스
	// 매개변수 : 현재 페이지, 검색어
	// 리턴값 : 현재 페이지의 공지사항 리스트
	public Map<String, Object> getLMSNoticeListByPage(int currentPage, String lmsNoticeSearch) {
		// 페이지 당 보여줄 게시물 수 
		int rowPerPage = 7;
		// 전체 게시물 수
		int noticeCount = lmsNoticeMapper.selectLMSNoticeCount(lmsNoticeSearch);
		// 시작하는 게시물의 순번
		int beginRow = (currentPage-1)*rowPerPage;
		// 마지막 페이지
		int lastPage = noticeCount/rowPerPage;
		if(noticeCount%rowPerPage!=0) {
			lastPage += 1;
		}
		if (lastPage == 0) {
			currentPage = 0;
		}
		// 페이지 네비바에 표시할 페이지 수
		int navPerPage = 10;
		// 네비바 첫번째 페이지
		int navBeginPage = (currentPage-1)/navPerPage*navPerPage + 1;
		// 네비바 마지막 페이지
		int navLastPage = (navBeginPage + navPerPage) - 1;
		// 네비바의 마지막 페이지와 라스트페이지가 달라질 경우 같게 설정
		if (navLastPage > lastPage) {
			navLastPage = lastPage;
		}
		
		Map<String, Object> pageMap = new HashMap<>();
		pageMap.put("rowPerPage", rowPerPage);
		pageMap.put("beginRow", beginRow);
		pageMap.put("lmsNoticeSearch", lmsNoticeSearch);
		List<LMSNotice> lmsNoticeList = lmsNoticeMapper.selectLMSNoticeListByPage(pageMap);
		logger.debug(lmsNoticeList.toString());
		
		Map<String, Object> map = new HashMap<>();
		map.put("lmsNoticeList", lmsNoticeList);
		map.put("lastPage", lastPage);
		map.put("navPerPage", navPerPage);
		map.put("navBeginPage", navBeginPage);
		map.put("navLastPage", navLastPage);
		
		return map;
	}
	
	// LMS공지사항의 상세보기를 보여주는 서비스
	// 매개변수 : lms 공지사항 번호
	// 리턴값 : 공지사항 번호의 상세 정보
	public LMSNotice getLMSNoticeDetail(int lmsNoticeNo) {
		return lmsNoticeMapper.selectLMSNoticeDetail(lmsNoticeNo);
	}
	
	// LMS공지사항의 1개의 공지입력 서비스
	// 매개변수 : LMS공지사항의 정보
	// 리턴값 : 추가된 행의 수
	public int createLMSNotice(LMSNotice lmsNotice, HttpSession session) {
		logger.debug(lmsNotice.toString());
		String sessionAccountId = (String)session.getAttribute("accountId");
		logger.debug(sessionAccountId);
		lmsNotice.setAccountId(sessionAccountId);
		lmsNotice.setLmsNoticeWriter(managerMapper.selectManagerName(sessionAccountId));
		return lmsNoticeMapper.insertLMSNotice(lmsNotice);
	}
	
	// LMS공지사항의 1개의 공지수정 서비스
	// 매개변수 : LMS공지사항의 정보
	// 리턴값 : 수정된 행의 수
	public int modifyLMSNotice(LMSNotice lmsNotice) {
		logger.debug(lmsNotice.toString());
		return lmsNoticeMapper.updateLMSNotice(lmsNotice);
	}
	
	// LMS공지사항의 1개의 공지삭제 서비스
	// 매개변수 : LMS공지사항의 번호
	// 리턴값 : 삭제된 행의 수
	public int removeLMSNotice(int lmsNoticeNo) {
		return lmsNoticeMapper.deleteLMSNotice(lmsNoticeNo);
	}
	
	// LMS공지사항의 조회 수 증가
	// 매개변수 : LMS공지사항의 번호
	// 리턴값 : 조회수의 증가
	public int increaseLMSNoticeCountOfViews(int lmsNoticeNo) {
		return lmsNoticeMapper.updateLMSNoticeCountOfViews(lmsNoticeNo);
	}
}
