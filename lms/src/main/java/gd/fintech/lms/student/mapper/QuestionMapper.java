package gd.fintech.lms.student.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.student.vo.Question;
// 질문게시판 Mapper 
@Mapper
public interface QuestionMapper {
		
	//질문 게시판 질문입력
	//매게변수: 질문게시판 정보를 가지고옴
	//리턴값:질문 리스트 행
	int insertQuestion(Question question);
	
	//학생의(자신의) 질문을 수정할 폼
	//매개변수: 질문게시판 게시물 번호를 가져옴
	//리턴값: 변경될 질문폼
	Question updateQuestionForm(int questionNo);
		
	//학생의(자신의) 질문 수정액션
	//매개변수: 질문 게세판 정보를 가지고옴
	//리턴값: 변경한 질문
	Question updateQuestion(Question question);
		
	//학생의 질문 삭제
	//매개변수: 질문게시판 질문 번호
	//리턴값: 삭제된 질문
	int deleteQuestion(String accountId);

	//학생의 질문 리스트 출력(페이징)
	//매개변수: map을 이용해 beginRow,rowPerPage 
	//리턴값: 페이지의 질문 게시판(리스트)
	List<Question> selectQuestionListByPage(Map<String,Integer>map);
	
	//학생들이 질문한 질문의 리스트
	//매개변수: 
	//리턴값: 학생들의 모든 질문
	int selectQuestionCount();
	
	//학생의 질문 상세보기(강사의 댓글과 파일보기)
	//매개변수: 질문 번호
	//리턴값: 질문의 모든 정보
	Question selectQuestionOne(int questionNo);
	
}
