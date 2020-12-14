package gd.fintech.lms.student.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.student.vo.Question;

@Mapper
public interface QuestionMapper {
	
	int insertQuestion(Question question);
	//질문 게시판 질문입력
	
	List<Question> selectQuestionListByPage(Map<String,Integer>Map);
	//학생의 질문 리스트 출력
	
	Question updateQuestionForm(int questionNo);
	//학생의 질문을 수정할 폼
	
	Question updateQuestion(Question question);
	//학생의 질문 수정액션
	
	int deleteQuestion(int questionNo);
	//학생의 질문 삭제
	
}
