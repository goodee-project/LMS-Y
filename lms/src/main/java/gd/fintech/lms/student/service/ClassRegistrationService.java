  package gd.fintech.lms.student.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	//매개변수:currentPage,rowPerPage
	//리턴값:학생이 수강신청한 리스트의 페이징값
	public List<ClassRegistration> getClassRegistrationListByPage(int currentPage,int rowPerPage){
		Map<String, Integer>map = new HashMap<>();
		map.put("beginRow", (currentPage-1)*rowPerPage);
		map.put("rowPerPage",rowPerPage);
		return classRegistrationMapper.selectClassRegistrationListByPage(map);
	}
	
	//수강 신청한 과목 상세보기
	//매개변수:과목의 번호
	//리턴값:학생이 수강신청한 리스트에 있는 과목의 정보
	public ClassRegistration getClassRegistrtaionOne(int subjectNo){
		return classRegistrationMapper.selectClassRegistrationOne(subjectNo);
	}
	
	//수강 신청한 갯수
	//매개변수:
	//리턴값: 수강신청한 갯수
	public int getRegistrationCount() {
		return classRegistrationMapper.selectRegistrationCount();
	}
}
