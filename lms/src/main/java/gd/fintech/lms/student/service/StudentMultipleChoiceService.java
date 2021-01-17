package gd.fintech.lms.student.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gd.fintech.lms.student.mapper.StudentMultipleChoiceMapper;
import gd.fintech.lms.student.vo.MultipleChoice;

//학생 강좌별 시험 리스트

@Service
@Transactional
public class StudentMultipleChoiceService {
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	@Autowired StudentMultipleChoiceMapper studentMultipleChoiceMapper;
	
	//강좌별 시험 리스트
	//매개변수:lectureNo,currentPage
	//리턴값:강좌별 시험 리스트
	public Map<String,Object> getStudentMultipleListByPage(int lectureNo,int currentPage){
		
		// 페이지의 데이터 갯수
		int rowPerPage = 10;
		int beginRow = (currentPage-1)*rowPerPage;
				
		//전체 페이지 갯수
		int multipleChoiceCount = studentMultipleChoiceMapper.selectMultipleCount(lectureNo);
		logger.debug(multipleChoiceCount+"문제 리스트 갯수");
		// 마지막 페이지
		int lastPage = multipleChoiceCount / rowPerPage;
				
		// 10 미만의 개수의 데이터가 있는 페이지 표시
		if (multipleChoiceCount % rowPerPage != 0) {
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
				
		Map<String,Object>parmMap = new HashMap<>();
		parmMap.put("rowPerPage", rowPerPage);
		parmMap.put("beginRow", beginRow);
		parmMap.put("lectureNo", lectureNo);
	
		//담아주기
		List<MultipleChoice> multipleChoice = studentMultipleChoiceMapper.selectStudentMultipleListByPage(parmMap);
		logger.debug(multipleChoice.toString());
				
		Map<String,Object>map = new HashMap<>();
		map.put("multipleChoice", multipleChoice);
		map.put("lastPage", lastPage);
		map.put("navPerPage", navPerPage);
		map.put("navBeginPage", navBeginPage);
		map.put("navLastPage", navLastPage);
		return map;	
	}
}
