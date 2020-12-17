package gd.fintech.lms.manager.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.manager.vo.Textbook;

// 교재 정보 Mapper
@Mapper
public interface TextbookMapper {
	// 교재 정보의 일부를 페이징하여 리스트로 출력
	// 매개변수: Map.put()을 사용하여 페이징 변수 beginRow(해당 페이지), rowPerPage(페이지에 표시할 항목수)
	// 리턴값: 교재 정보 리스트
	List<Textbook> selectTextbookList(Map<String, Integer> map);
	
	// 교재 정보 리스트의 페이징을 위해 전체 항목수 출력
	// 매개변수: 없음
	// 리턴값: 전체 항목 수
	int selectTextbookCount();
	
	// 교재 정보의 상세정보 출력
	// 매개변수: 교재 고유번호(국제 표준 도서 번호 ISBN)
	// 리턴값: 교재 고유번호에 해당하는 교재 정보
	Textbook selectTextbookDetail(String textbookISBN);
	
	// 교재 정보를 입력
	// 매개변수: 교재 정보
	// 리턴값: 변경된 행의 갯수
	int insertTextbook(Textbook textbook);
	
	// 교재 정보를 수정
	// 매개변수: 교재 정보
	// 리턴값: 변경된 행의 갯수
	int updateTextbook(Textbook textbook);
}
