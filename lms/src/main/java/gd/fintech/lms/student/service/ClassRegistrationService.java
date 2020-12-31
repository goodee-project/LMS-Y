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
	public List<ClassRegistration> getClassRegistrationListByPage(String accountId,int currentPage){
		
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
		
		Map<String, Object>map = new HashMap<String,Object>();
		map.put("beginRow", beginRow);
		map.put("rowPerPage",rowPerPage);
		map.put("accountId", accountId);
		map.put("lastPage", lastPage);
		
		List<ClassRegistration>classRegistrationList=classRegistrationMapper.selectClassRegistrationListByPage(map);
		map.put("classRegistrationList", classRegistrationList);
		
		return classRegistrationList;
	}
	
	//수강신청할 수 있는 목록전체(페이징)
	//매개변수:lectureNo,currentPage
	//리턴값:학생이 수강신청할 수 있는 모든 리스트
	public List<ClassRegistration> getClassRegistrationAllListByPage(int lectureNo,int currentPage){
	
		//보여줄 데이터 갯수
		int rowPerPage=10;
		//보여줄 데이터 갯수
		int beginRow=(currentPage-1)*rowPerPage;
		//전체 페이지
		int classRegistrationAllCount = classRegistrationMapper.selectRegistrationAllCount(lectureNo);
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
	
		Map<String, Object>map = new HashMap<String,Object>();
		map.put("beginRow", beginRow);
		map.put("rowPerPage",rowPerPage);
		map.put("lectureNo", lectureNo);
		map.put("lastPage", lastPage);
		
		List<ClassRegistration>classRegistrationList=classRegistrationMapper.selectClassRegistrationAll(map);
		map.put("classRegistrationList", classRegistrationList);
	
		return classRegistrationList;
	}
	
	
	
	//수강 신청한 과목 상세보기
	//매개변수:과목의 번호
	//리턴값:학생이 수강신청한 리스트에 있는 과목의 정보
	public ClassRegistration getClassRegistrtaionOne(int subjectNo){
		return classRegistrationMapper.selectClassRegistrationOne(subjectNo);
	}
}
