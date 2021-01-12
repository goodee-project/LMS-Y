  package gd.fintech.lms.student.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gd.fintech.lms.student.mapper.ClassRegistrationMapper;
import gd.fintech.lms.student.vo.ClassRegistration;

@Service
@Transactional
public class ClassRegistrationService {
	private final Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Autowired ClassRegistrationMapper classRegistrationMapper;
	
	//학생이 수강신청한 목록(페이징)
	//매개변수:accountId,currentPage
	//리턴값:학생이 수강신청한 리스트와 페이징값
	public Map<String,Object> getClassRegistrationListByPage(String accountId,int currentPage){
		
		//보여줄 데이터 갯수
		int rowPerPage=5;
		int beginRow=(currentPage-1)*rowPerPage;
		//전체 페이지
		int classRegistrationCount =classRegistrationMapper.selectRegistrationCount(accountId);

		//마지막 페이지
		int lastPage = classRegistrationCount/rowPerPage;
		//5 미만의 데이터가 있는 페이지 보여주기
		if(classRegistrationCount % rowPerPage !=0) {
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
		
		Map<String, Object>paramMap = new HashMap<String,Object>();
		paramMap.put("beginRow", beginRow);
		paramMap.put("rowPerPage",rowPerPage);
		paramMap.put("accountId", accountId);
		paramMap.put("classRegistrationCount", classRegistrationCount);
		//담아주기
		List<ClassRegistration>classRegistrationList=classRegistrationMapper.selectClassRegistrationListByPage(paramMap);
		
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
	public Map<String,Object> getClassRegistrationAllListByPage(int currentPage){
	
		//보여줄 데이터 갯수
		int rowPerPage=5;
		//보여줄 데이터 갯수
		int beginRow=(currentPage-1)*rowPerPage;
		//전체 페이지
		int classRegistrationAllCount = classRegistrationMapper.selectRegistrationAllCount();
		//마지막 페이지
		int lastPage = classRegistrationAllCount/rowPerPage;
		//10개 미만의 데이터가 있는 페이지 보여주기
		if(classRegistrationAllCount % rowPerPage !=0) {
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
		
		Map<String,Object>paramMap=new HashMap<>();
		paramMap.put("rowPerPage",rowPerPage);
		paramMap.put("beginRow", beginRow);
		paramMap.put("classRegistrationAllCount",classRegistrationAllCount);
		
		
		List<ClassRegistration> classRegistrationAllList = classRegistrationMapper.selectClassRegistrationAll(paramMap);
		logger.debug(classRegistrationAllList.toString());
		
		Map<String,Object> map = new HashMap<>();
		map.put("classRegistrationAllList",classRegistrationAllList);
		map.put("lastPage", lastPage);
		map.put("navPerPage", navPerPage);
		map.put("navBeginPage", navBeginPage);
		map.put("navLastPage", navLastPage);
		
		return map;
	}
	//학생 강좌 상세보기
	//매개변수:강좌의 번호
	//리턴값:강좌의 상세보기
	public ClassRegistration getClassRegistrationLectureDetail(int lectureNo) {
		ClassRegistration classRegistration = classRegistrationMapper.selectClassRegistrationLectureDetail(lectureNo);
		return classRegistration;
	}
	
}
