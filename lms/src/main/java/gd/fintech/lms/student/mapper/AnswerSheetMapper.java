package gd.fintech.lms.student.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.student.vo.AnswerSheet;

@Mapper
public interface AnswerSheetMapper {
	
	//학생이 낸 답안지들 리스트 페이징
	//매개변수: map으로 beginRow, rowPerPage 넣음
	//리턴값: 답안지의 리스트
	List<AnswerSheet> selectAnswerSheetByPage(Map<String,Integer>map);
	
	//학생이 낸 답지 상세보기
	//매개변수: 답안지의 번호
	//리턴값: 해당 답안지의 상세보기
	List<AnswerSheet> selectAnswerSheetOne(int multipleChoiceNo);
	
	//답안지의 점수
	//매개변수: 답안지의 점수
	//리턴값: 해당 답지의 점수
	int insertAnswerSheetScore(String answerScore);
	
}
