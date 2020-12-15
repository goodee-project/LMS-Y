package gd.fintech.lms.manager.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.manager.vo.Textbook;

// 교재 정보 Mapper
@Mapper
public interface TextbookMapper {
	// 교재 정보 입력
	// 매개변수: 교재 정보
	// 리턴값: 변경된 행의 갯수
	int insertTextbook(Textbook textbook);
	
	// 교재 정보 수정
	// 매개변수: 교재 정보
	// 리턴값: 변경된 행의 갯수
	int updateTextbook(Textbook textbook);
	
	// 교재 정보를 페이징하여 리스트로 출력
	// 매개변수:
	// 리턴값: 교재 정보의 리스트
	List<Textbook> selectTextbookList(Map<String, Integer> map);
	
	// 교재 정보 상세보기
	// 매개변수: 교재의 고유번호(국제 표준 도서 번호 ISBN)
	// 리턴값: 교재 고유번호에 해당하는 교재 상세정보
	Textbook selectTextbookDetail(String textbookISBN);
}
