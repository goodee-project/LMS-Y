package gd.fintech.lms.manager.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.manager.vo.Textbook;

// 교재 정보 Mapper

@Mapper
public interface TextbookMapper {
	// 교재 목록을 페이징하여 출력
	// 매개변수: map에 저장된 페이징 변수 beginRow(해당 페이지), rowPerPage(페이지에 표시할 항목수)와 검색 변수 searchType(검색조건), searchKeyword(검색어)
	// 리턴값: 교재 목록
	// 검색을 했다면 검색 결과에 따른 교재 목록 출력
	List<Textbook> selectTextbookList(Map<String, Object> map);
	
	// 교재 목록의 페이징을 위해 총 항목수를 출력
	// 매개변수:
	// #1. searchType(검색조건)
	// #2. searchKeyword(검색어)
	// 리턴값: 교재 정보의 총 항목수
	// 검색을 했다면 검색 결과에 따른 교재의 총 항목수 출력
	int selectTextbookCount(String searchType, String searchKeyword);
	
	// 교재 정보를 출력
	// 매개변수: textbookISBN(교재 ISBN)
	// 리턴값: 교재 ISBN에 해당하는 교재 정보
	Textbook selectTextbookDetail(String textbookISBN);
	
	// 교재 ISBN의 중복 여부를 확인하기 위해 교재 ISBN을 출력
	// 매개변수: textbookISBN(교재 ISBN)
	// 리턴값: 교재 ISBN에 해당하는 교재 ISBN
	String selectTextbookISBN(String textbookISBN);
	
	// 교재 정보를 입력
	// 매개변수: textbook(교재 정보)
	// 리턴값: 변경된 행의 갯수
	int insertTextbook(Textbook textbook);
	
	// 교재 정보를 수정
	// 매개변수: textbook(교재 정보)
	// 리턴값: 변경된 행의 갯수
	int updateTextbook(Textbook textbook);
}
