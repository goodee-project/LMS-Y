package gd.fintech.lms.manager.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gd.fintech.lms.manager.mapper.TextbookMapper;
import gd.fintech.lms.manager.vo.Textbook;

// 교재 정보 관련 Service

@Service
@Transactional
public class TextbookService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// 교재 정보 Mapper
	@Autowired private TextbookMapper textbookMapper;
	
	// 교재 목록을 페이징하여 출력하는 메소드
	// 매개변수:
	// #1. currentPage(현재 페이지)
	// #2. rowPerPage(페이지 당 표시할 항목수)
	// 리턴값: 교재 목록
	public List<Textbook> getTextbookList(int currentPage, int rowPerPage) {
		// 해당 페이지에 표시한 항목
		int beginRow = (currentPage - 1) * rowPerPage;
		
		// Map의 객체 생성 후 값(beginRow, rowPerPage) 저장
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("beginRow", beginRow);
		map.put("rowPerPage", rowPerPage);
		
		List<Textbook> textbookList = textbookMapper.selectTextbookList(map);
		return textbookList;
	}
	
	// 교재 목록을 페이징 하기 위해 총 항목수를 출력하는 메소드
	// 매개변수: 없음
	// 리턴값: 교재 정보 총 항목수
	public int getTextbookCount() {
		return textbookMapper.selectTextbookCount();
	}
	
	// 교재 정보를 출력하는 메소드
	// 매개변수: textbookISBN(교재 고유번호)
	// 리턴값: 고유번호에 해당하는 교재 정보
	public Textbook getTextbookDetail(String textbookISBN) {
		Textbook textbookDetail = textbookMapper.selectTextbookDetail(textbookISBN);
		logger.debug(textbookDetail.toString());
		return textbookDetail;
	}
	
	// 교재 정보를 입력하는 메소드
	// 매개변수: textbook(교재 정보)
	// 리턴값: 없음
	// 교재 정보 입력
	public void createTextbook(Textbook textbook) {
		logger.debug(textbook.toString());
		textbookMapper.insertTextbook(textbook);
	}
	
	// 교재 정보를 수정하는 메소드
	// 매개변수: textbook(교재 정보)
	// 리턴값: 없음 
	// 교재 정보 수정
	public void modifyTextbook(Textbook textbook) {
		logger.debug(textbook.toString());
		textbookMapper.updateTextbook(textbook);
	}	
}
