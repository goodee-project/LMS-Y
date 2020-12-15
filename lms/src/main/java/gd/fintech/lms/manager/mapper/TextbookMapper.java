package gd.fintech.lms.manager.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.manager.vo.Textbook;

// 교재 정보 Mapper
@Mapper
public interface TextbookMapper {
	// 교재정보 입력
	int insertTextbook();
	
	// 교재정보 수정
	int updateTextbook();
	
	// 교재정보를 페이징하여 리스트로 출력
	List<Textbook> selectTextbookList(Map<String, Integer> map);
	
	// 교재정보 상세보기
	Textbook selectTextbookDetail(String textbookISBN);
}
