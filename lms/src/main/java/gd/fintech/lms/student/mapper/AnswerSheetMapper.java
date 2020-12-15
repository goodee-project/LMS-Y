package gd.fintech.lms.student.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.student.vo.AnswerSheet;

@Mapper
public interface AnswerSheetMapper {
	
	List<AnswerSheet> selectAnswerSheetByPage(Map<String,Integer>map);
	//학생이 낸 답안지들 리스트 페이징
	
	List<AnswerSheet> selectAnswerSheetOne();
	//학생이 낸 답지 상세보기
	
	int insertAnswerSheetScore(String answerScore);
	//답안지의 점수
}
