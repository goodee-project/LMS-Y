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

import gd.fintech.lms.manager.vo.Lecture;
import gd.fintech.lms.student.mapper.ClassRegistrationMapper;
import gd.fintech.lms.student.vo.ClassRegistration;

//학생 수강신청 서비스

@Service
@Transactional
public class ClassRegistrationService {
	private final Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Autowired ClassRegistrationMapper classRegistrationMapper;
	
	//학생이 수강신청한 목록(페이징)
	//매개변수:accountId,currentPage
	//리턴값:학생이 수강신청한 리스트와 페이징값
	public Map<String, Object> getClassRegistrationListByPage(int currentPage, HttpSession session){
		
		//보여줄 데이터 갯수
		int rowPerPage = 5;
		int beginRow = (currentPage-1)*rowPerPage;
		
		//전체 페이지
		int classRegistrationCount = classRegistrationMapper.selectRegistrationCount((String)session.getAttribute("accountId"));

		//마지막 페이지
		int lastPage = classRegistrationCount/rowPerPage;
		//5 미만의 데이터가 있는 페이지 보여주기
		if(classRegistrationCount % rowPerPage !=0) {
			lastPage +=1;
		}
		//전체 페이지 0일시 현재도 0
		if(lastPage ==0) {
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
		
		Map<String, Object> pageMap = new HashMap<String,Object>();
		pageMap.put("beginRow", beginRow);
		pageMap.put("rowPerPage",rowPerPage);
		pageMap.put("accountId", session.getAttribute("accountId"));
		//담아주기
		List<Map<String, Object>> classRegistrationList = classRegistrationMapper.selectClassRegistrationListByPage(pageMap);
		
		Map<String,Object>map = new HashMap<>();
		map.put("classRegistrationList", classRegistrationList);
		map.put("lastPage", lastPage);
		map.put("navPerPage", navPerPage);
		map.put("navBeginPage", navBeginPage);
		map.put("navLastPage", navLastPage);
		
		return map;
	}
	
	
	//수강신청할 수 있는 목록전체(페이징)
	//매개변수:lectureNo,currentPage
	//리턴값:학생이 수강신청할 수 있는 모든 리스트
	public Map<String,Object> getAvailableLectureList(int currentPage,HttpSession session){
	
		//보여줄 데이터 갯수
		int rowPerPage=5;
		//보여줄 데이터 갯수
		int beginRow=(currentPage-1)*rowPerPage;
		//전체 페이지
		int availableLectureCount = classRegistrationMapper.selectAvailableLectureCount();
		//마지막 페이지
		int lastPage = availableLectureCount/rowPerPage;
		//10개 미만의 데이터가 있는 페이지 보여주기
		if(availableLectureCount % rowPerPage !=0) {
			lastPage +=1;
		}
		//전체 페이지 0일시 현재도 0
		if(lastPage ==0) {
			currentPage =0; 
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
		
		Map<String,Object> pageMap = new HashMap<>();
		pageMap.put("rowPerPage",rowPerPage);
		pageMap.put("beginRow", beginRow);
		
		List<Lecture> availableLectureList = classRegistrationMapper.selectAvailableLectureList(pageMap);
		logger.debug(availableLectureList.toString());
		
		List<Map<String, Object>> availableLectureListMap = new ArrayList<>();
		for(Lecture l : availableLectureList) {
			Map<String,Object>map = new HashMap<>();
			map.put("accountId",session.getAttribute("accountId"));
			map.put("lectureNo", l.getLectureNo());
			ClassRegistration cr= classRegistrationMapper.selectClassRegistrationDetailByAccountAndLecture(map);
			Map<String, Object> lectureMap = new HashMap<>();
			lectureMap.put("lecture", l);
			lectureMap.put("isRegisterable", (cr == null));
			availableLectureListMap.add(lectureMap);
		}
		
		Map<String,Object> map = new HashMap<>();
		map.put("availableLectureListMap",availableLectureListMap);
		map.put("lastPage", lastPage);
		map.put("navPerPage", navPerPage);
		map.put("navBeginPage", navBeginPage);
		map.put("navLastPage", navLastPage);
		
		return map;
	}
	
	//학생 수강신청 상태 수료로 변경
	//매개변수:session
	//리턴값:id에 따른 학생의 수강상태 변경
	public int modifyRegistrationState(HttpSession session) {
		String accountId = session.getAttribute("accountId").toString();
		return classRegistrationMapper.updateRegistrationState(accountId); 
	}
	
	
	//학생 강좌 상세보기
	//매개변수:강좌의 번호,session
	//리턴값:강좌의 상세보기
	public Map<String,Object> getClassRegistrationLectureDetail(int lectureNo,HttpSession session) {
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("accountId",session.getAttribute("accountId"));
		paramMap.put("lectureNo", lectureNo);
		ClassRegistration classRegistration = classRegistrationMapper.selectClassRegistrationLectureDetail(lectureNo);
		ClassRegistration ck= classRegistrationMapper.selectClassRegistrationDetailByAccountAndLecture(paramMap);
		Map<String,Object> map = new HashMap<>();
		map.put("classRegistration",classRegistration);
		map.put("isRegisterable",(ck==null));
		
		return map;
	}
	
	//학생 수강신청하기
	//매개변수:강좌의 번호,session
	//리턴값:추가되는 학생의 행
	public int insertRegistration(int lectureNo,HttpSession session) {

		Map<String,Object> map = new HashMap<>();
		
		map.put("lectureNo",lectureNo);
		map.put("accountId",session.getAttribute("accountId"));
		int classRegistrationCk = classRegistrationMapper.selectRegistrationNoCount(map);
		logger.debug(classRegistrationMapper.selectRegistrationNoCount(map)+"값넘기기");
		map.put("classRegistrationCk",classRegistrationCk);
		logger.debug(classRegistrationCk+"값");
		return classRegistrationMapper.insertRegistration(map);
	}
	
}
