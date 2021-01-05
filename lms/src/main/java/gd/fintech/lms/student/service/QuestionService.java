package gd.fintech.lms.student.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gd.fintech.lms.student.mapper.QuestionMapper;
import gd.fintech.lms.student.mapper.StudentMapper;
import gd.fintech.lms.student.vo.Question;

@Service
@Transactional
public class QuestionService {
	private final Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Autowired QuestionMapper questionMapper;
	@Autowired StudentMapper studentMapper;
	
	//학생의 질문 리스트 페이징 
	//매개변수:lectureNo , rowPerPage 
	//리턴값:질문게시판의 페이징 값 ,강좌에 대한 모든 학생들의 질문
	public Map<String,Object> getQuestionListByPage(int lectureNo,int currentPage){
		
		// 페이지의 데이터 갯수
		int rowPerPage = 10;
		int beginRow = (currentPage-1)*rowPerPage;
		
		//전체 페이지 갯수
		int questionCount = questionMapper.selectQuestionCount(lectureNo);
		logger.debug(questionCount+"질문갯수");
		// 마지막 페이지
		int lastPage = questionCount / rowPerPage;
		
		// 10 미만의 개수의 데이터가 있는 페이지 표시
		if (questionCount % rowPerPage != 0) {
			lastPage += 1;
			logger.debug("진입");
		}
		
		// 전체 페이지가 0개이면 현재 페이지도 0으로 표시
		if (lastPage == 0) {
			currentPage = 0;
		}
		
		//페이지 네비바에 표시할 페이지 수
		int navPerPage = 10;
		//네비바 첫번째 페이지
		int navBeginPage = (currentPage-1)/navPerPage*navPerPage + 1;
		// 네비바 마지막 페이지
		int navLastPage = (navBeginPage + navPerPage) - 1;
		// 네비바의 마지막 페이지와 라스트페이지가 달라질 경우 같게 설정
		if (navLastPage > lastPage) {
			navLastPage = lastPage;
		}
		
		Map<String,Object>parmMap = new HashMap<String,Object>();
		parmMap.put("rowPerPage", rowPerPage);
		parmMap.put("beginRow", beginRow);
		parmMap.put("lectureNo", lectureNo);
		parmMap.put("questionCount", questionCount);
		//담아주기
		List<Question>questionAllList = questionMapper.selectQuestionListByPage(parmMap);
		
		Map<String,Object>map = new HashMap<>();
		map.put("questionAllList", questionAllList);
		map.put("lastPage", lastPage);
		map.put("navPerPage", navPerPage);
		map.put("navBeginPage", navBeginPage);
		map.put("navLastPage", navLastPage);
		return map;
	}
	

	//학생의 질문 상세보기 (강사의 댓글 포함)
	//매개변수:해당 질문의 번호
	//리턴값:해당 질문의 상세보기
	public Question getQuestionOne(int questionNo) {
		return questionMapper.selectQuestionOne(questionNo);
	}

	
	//학생의 질문을 수정 액션 
	//매개변수:질문의 vo
	//리턴값:수정 실행
	public int modifyQuestion(Question question,HttpSession session) {
		return questionMapper.updateQuestion(question);
	}
	
	//학생의 질문 삭제
	//매개변수:학생의 번호
	//리턴값:학생의 번호별로 질문을 삭제함
	public int removeQuestion(int questionNo) {
		return questionMapper.deleteQuestion(questionNo);
	}
	
	//학생의 질문 입력 
	//매개변수:질문 vo
	//리턴값:입력할 질문의 양식
	public int addQuestion(Question question,HttpSession session) {
		String sessionAccountId = (String)session.getAttribute("accountId");
		logger.debug(sessionAccountId+"현재 로그인한 학생");
		question.setAccountId(sessionAccountId);
		question.setQuestionWriter(studentMapper.selectStudentName(sessionAccountId));
		return questionMapper.insertQuestion(question);
	}


	//학생의 질문 조회수 증가
	//매개변수:질문의 번호
	//리턴값:해당 질문의 조회수증가
	public int increaseQuestionCount(int questionNo) {
		return questionMapper.increaseQuestionCount(questionNo);
	}
	
}
