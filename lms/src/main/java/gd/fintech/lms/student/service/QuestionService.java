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
import gd.fintech.lms.student.vo.Question;

@Service
@Transactional
public class QuestionService {
	private final Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Autowired QuestionMapper questionMapper;
	
	
	//강좌별 학생의 모든 질문 리스트 페이징 
	//매개변수:currentPage , rowPerPage 
	//리턴값:질문게시판의 페이징 값 
	//리턴값:강좌에 대한 학생들의 질문
	public Map<String,Object> getQuestionListByPage(int questionNo,int currentPage,HttpSession session){
		//현재 로그인한 학생의 정보
		//String sessionAccountId =(String)session.getAttribute("accountId");
		
		Map<String,Object>map =new HashMap<>();
		
		// rowPerPage 및 beginRow 계산
		int rowPerPage = 10;
		int beginRow = (currentPage-1)*rowPerPage;
				
		// 페이지 네비게이션용 값 연산
		int pageNaviSize = 10;
		int lastPage = questionMapper.selectQuestionCount(questionNo)/pageNaviSize;
		if (questionMapper.selectQuestionCount(questionNo)%pageNaviSize !=0) {
			lastPage +=1;
		}
		int pageNaviBegin = (currentPage-1)/pageNaviSize*pageNaviSize+1;	// 페이지의 첫번째 값을 구한 후(pageNaviSize가 10일 경우 0~9->0, 10~19->10, 20~29->20)후 1을 더해줌(1, 11, 21, ...)
		int pageNaviEnd = pageNaviBegin+pageNaviSize-1;						// pageNaviBegin도 페이지 네비에 포함되므로 -1을 하여 딱 pageNaviSize개의 페이지 네비 리스트가 보이도록 설정함
		if (pageNaviEnd > lastPage) {
			pageNaviEnd = lastPage;
		}
		map.put("pageNaviBegin", pageNaviBegin);
		map.put("pageNaviEnd", pageNaviEnd);
		map.put("lastPage", lastPage);
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		paramMap.put("rowPerPage", rowPerPage);
		paramMap.put("beginRow", beginRow);
		paramMap.put("questionNo", questionNo);
		
		List<Map<String,Object>>questionAllList = new ArrayList<>();
		for(Question question :questionMapper.selectQuestionListByPage(paramMap)) {
			Map<String,Object>questionAll = new  HashMap<>();
			questionAll.put("question",question);
			questionAll.put("questionAllCount", questionMapper.selectQuestionCount(question.getQuestionCount()));
			
			questionAllList.add(questionAll);
		}
		map.put("questionAllList", questionAllList);
		return map;
	}
	
	
	//해당 학생의 질문 리스트 페이징
	//매개변수:학생Id,현재페이지
	//리턴값:학생의 질문 리스트
	public Map<String,Object> getStudentQuestionListByPage(String accountId,int currentPage,HttpSession session){
		//현재 로그인한 학생의 계정
		//String sessionAccountId =(String)session.getAttribute("accountId");
		
		Map<String,Object>map =new HashMap<>();
		//보여줄 행(데이터)개수
		int rowPerPage=10;
		int beginRow=(currentPage-1)*rowPerPage;
		
		//페이지 네비게이션
		int pageNaviSize=10;
		
		int lastPage = questionMapper.studentQuestionCount(accountId)/pageNaviSize;
		if(questionMapper.studentQuestionCount(accountId)%pageNaviSize !=0) {
			lastPage +=1; 
		}
		
		int pageNaviBegin = (currentPage-1)/pageNaviSize*pageNaviSize+1;
		int pageNaviEnd = pageNaviBegin+pageNaviSize-1;						// pageNaviBegin도 페이지 네비에 포함되므로 -1을 하여 딱 pageNaviSize개의 페이지 네비 리스트가 보이도록 설정함
		if (pageNaviEnd > lastPage) {
			pageNaviEnd = lastPage;
		}
		//위의내용 담기
		//페이지 네비게이션 시작
		map.put("pageNaviBegin",pageNaviBegin);
		//페이지 네비게이션 종료
		map.put("pageNaviEnd", pageNaviEnd);
		//페이지 총갯수
		map.put("lastPage",lastPage);
		
		Map<String,Object>paramMap = new HashMap<String,Object>();
		paramMap.put("rowPerPage", rowPerPage);
		paramMap.put("beginRow", beginRow);
		paramMap.put("accountId", accountId);
		
		List<Map<String,Object>>studentQuestionList = new ArrayList<>();
		for(Question question:questionMapper.selectStudentQuestionListByPage(paramMap)) {
			Map<String,Object>studentQuestion= new HashMap<>();
			studentQuestion.put("question",question);
			studentQuestion.put("studentQuestionCount", questionMapper.studentQuestionCount(question.getAccountId()));
			
			studentQuestionList.add(studentQuestion);
		}
		map.put("studentQuestionList", studentQuestionList);
		
		return map;
	}

	
	
	
	//학생의 질문 상세보기 
	//매개변수:해당 질문의 번호
	//리턴값:해당 질문의 상세보기
	public Question getQuestionOne(int questionNo) {
		Question questionDetail = questionMapper.selectQuestionOne(questionNo);
		return questionDetail;
	}
	
	//학생의 질문을 수정 액션 
	//매개변수:질문의 vo
	//리턴값:수정 실행
	public Question modifyQuestion(Question question,HttpSession session) {
		return questionMapper.updateQuestion(question);
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
	public int addQuestion(Question question,HttpSession session) {
		return questionMapper.insertQuestion(question);
	}


	//학생의 질문 조회수
	
}
