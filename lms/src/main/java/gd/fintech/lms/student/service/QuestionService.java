package gd.fintech.lms.student.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gd.fintech.lms.student.mapper.QuestionMapper;
import gd.fintech.lms.student.vo.Question;

@Service
@Transactional
public class QuestionService {
	private final Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Autowired QuestionMapper questionMapper;
	
	//학생의 질문 리스트 페이징 
	//매개변수:currentPage , rowPerPage 10행
	//리턴값:질문게시판의 페이징 값 
	//리턴값:강좌에 대한 학생들의 질문
	public Map<String,Object> getQuestionListByPage(String accountId,int currentPage){
		//보여줄 행(데이터)갯수
		int rowPerPage=10;
		int beginRow=(currentPage-1)*rowPerPage;
		//전체 페이지 수
		int questionCount = questionMapper.selectQuestionCount(accountId);
		int lastPage = questionCount/rowPerPage;
		//rowPerPage 미만의 데티어가 있는 페이지 보여줌
		if(questionCount%rowPerPage!=0) {
			lastPage +=1;
		}
		//마지막 페이지 0이면 현재도 0 페이지
		if(lastPage ==0) {
			currentPage=0;
		}
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		paramMap.put("rowPerPage", rowPerPage);
		paramMap.put("beginRow", beginRow);
		paramMap.put("accountId", accountId);
		
		List<Question>questionList = questionMapper.selectQuestionListByPage(paramMap);
		Map<String,Object>map = new HashMap<String,Object>();
		map.put("lastPage", lastPage);
		map.put("questionList", questionList);
		
		return map;
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
		Question questionDetail = questionMapper.selectQuestionOne(questionNo);
		return questionDetail;
	}
	
	//학생의 질문 삭제
	//매개변수:학생의 번호
	//리턴값:학생의 번호별로 질문을 삭제함
	public int removeQuestion(String accountId) {
		return questionMapper.deleteQuestion(accountId);
	}
	
	//학생의 질문 입력 
	//매개변수:질문 vo
	//리턴값:입력할 질문의 양식
	public int addQeustion(Question question) {
		return questionMapper.insertQuestion(question);
	}
	
	//학생의 질문 조회수
	
}
