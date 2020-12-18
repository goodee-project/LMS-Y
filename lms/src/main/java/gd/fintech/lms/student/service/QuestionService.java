package gd.fintech.lms.student.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gd.fintech.lms.student.mapper.QuestionMapper;
import gd.fintech.lms.student.vo.Question;

@Service
@Transactional
public class QuestionService {
	@Autowired QuestionMapper questionMapper;
	
	//학생들의 질문 리스트 페이징 
	//매개변수:currentPage , rowPerPage 10행
	//리턴값:질문게시판의 페이징 값
	public List<Question> getQuestionListByPage(int currentPage,int rowPerPage){
		Map<String,Integer>map = new HashMap<>();
		map.put("beginRow", (currentPage-1)*rowPerPage);
		map.put("rowPerPage",rowPerPage);
		return questionMapper.selectQuestionListByPage(map);
	}
	
	//학생의 질문을 수정하는 폼 
	//매개변수:질문의 번호
	//리턴값:수정할 정보들을 보는 폼
	public Question modifyQuestionForm(int questionNo) {
		return questionMapper.updateQuestionForm(questionNo);
	}
	
	//학생의 질문을 수정 액션 
	//매개변수:질문의 vo
	//리턴값:수정 실행
	public Question modifyQuestion(Question question) {
		return questionMapper.updateQuestion(question);
	}
	
	//학생의 질문 상세보기 
	//매개변수:해당 질문의 번호
	//리턴값:해당 질문의 상세보기
	public Question getQuestionOne(int questionNo) {
		return questionMapper.selectQuestionOne(questionNo);
	}
}
