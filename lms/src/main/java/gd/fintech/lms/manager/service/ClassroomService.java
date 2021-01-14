package gd.fintech.lms.manager.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gd.fintech.lms.manager.mapper.ClassroomMapper;
import gd.fintech.lms.manager.vo.Classroom;


// Classroom 을 관리하는 서비스 

@Service
@Transactional
public class ClassroomService {
	//debugLogger
	private final Logger logger = LoggerFactory.getLogger(ClassroomService.class);	
	
	// Classroom 에 대한 메퍼
	@Autowired private ClassroomMapper classroomMapper;
	
	
	// 강의실 리스트를 보여주는 서비스
	// 매개변수: 현재 페이지 
	// 리턴값: 현재 페이지의 강의실 리스트
	public Map<String, Object> getClassroomListByPage(int currentPage){
		//  페이지당 표시되는 데이터 수
		int rowPerPage = 10;
		// 현재 페이지에서 시작하는 데이터 
		int beginRow = (currentPage -1)*rowPerPage;
		// 전체 페이지 개수
		int countClassroom = classroomMapper.selectClassroomCount();
		// 마지막 페이지
		int lastPage = countClassroom / rowPerPage;
		if (countClassroom % rowPerPage !=0) {
			lastPage +=1;
		}
		// 마지막 페이지가 0일때 현재페이지0
		if(lastPage == 0) {
			currentPage =0;
		}
		
		// 페이지 네비에서 표시할 페이지 수
		int navPerPage = 10;
		// 페이지 네비에서의 처음 페이지
		int navBeginPage = (currentPage -1)/navPerPage*navPerPage +1;
		// 페이지 네비에서의 마지막 페이지
		int navLastPage =(navBeginPage + navPerPage) -1;
		
		// navLastPage 가 lastPage 보다 크면 navLastPage의 값은 lastPage
		if(navLastPage > lastPage) {
			navLastPage = lastPage;
		}
		
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("beginRow", beginRow);
		map.put("rowPerPage", rowPerPage);
		
		Map<String, Object> paramMap = new HashMap<String,Object>();
		List<Classroom> classroomList = classroomMapper.selectClassroomListByPage(map);
		paramMap.put("classroomList",classroomList);
		paramMap.put("lastPage",lastPage);
		paramMap.put("navBeginPage",navBeginPage);
		paramMap.put("navLastPage",navLastPage);
		paramMap.put("navPerPage",navPerPage);
		logger.debug("classroomList"+classroomList);
		return paramMap;
	}
	
	
	// 강의실 추가하는 서비스 
	// 매개변수: 강의실의 정보 
	// 리턴값:  입력한 정보의 행 추가 
	public int createClassroom(Classroom classroom) {
		return classroomMapper.insertClassromm(classroom);
	}
	
	
	// 강의실 수정하는 서비스
	// 매개변수: 강의실의 정보 
	// 리턴값:  입력한 정보의 행 수정 
	public int modifyClassroom(Classroom classroom){
		return classroomMapper.updateClassroom(classroom);
	}
	
	
	// 강의실 삭제하는 서비스
	// 매개변수: 강의실 고유번호 
	// 리턴값: 강의실 고유번호에 해당하는 행 삭제   
  	public int removeClassroom(int classroomNo) {
		return classroomMapper.deleteClassroom(classroomNo);
	}
	
	
	// 강의실 상세정보 서비스
	// 매개변수: 강의실의 고유번호
	// 리턴값:  강의실 고유번호에 해당하는 상세정보
	public Classroom getClassroomDetail(int classroomNo) {
		return classroomMapper.classroomDetail(classroomNo);
	}
}
