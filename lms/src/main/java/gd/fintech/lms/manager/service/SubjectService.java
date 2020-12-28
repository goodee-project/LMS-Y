package gd.fintech.lms.manager.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gd.fintech.lms.manager.mapper.SubjectMapper;
import gd.fintech.lms.manager.vo.Subject;

// 과목 정보 관련 Service

@Service
@Transactional
public class SubjectService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// 과목 정보 Mapper
	@Autowired private SubjectMapper subjectMapper;
	
	// 과목 목록을 페이징하여 출력하는 메소드
	// 매개변수: currentPage(현재 페이지)
	// 리턴값: 과목 목록
	public Map<String, Object> getSubjectList(int currentPage) {
		// 한 페이지에 보여줄 항목수 15개
		int rowPerPage = 15;
		// 해당 페이지에 표시한 항목
		int beginRow = (currentPage - 1) * rowPerPage;
		// 총 항목수
		int totalCount = subjectMapper.selectSubjectCount();
		// 마지막 페이지
		int lastPage = totalCount / rowPerPage;
		// 페이지 네비게이션 바에 표시할 페이지 수
		int pageNaviSize = 10;
		// 페이지 네비게이션 바의 첫번째 값
		int pageNaviBegin = (currentPage - 1) / pageNaviSize * pageNaviSize + 1;
		// 페이지 네비게이션 바의 마지막 값
		int pageNaviEnd = (pageNaviBegin + pageNaviSize) - 1;
		
		// 한 페이지에 보여줄 항목수 미만의 항목이 남을 경우 마지막 페이지를 한 페이지 추가
		if (totalCount % rowPerPage != 0) {
			lastPage += 1;
		}
				
		// 만약 마지막 페이지가 0이라면 현재 페이지도 0이 됨
		if (lastPage == 0) {
			currentPage = 0;
		}
		
		// 만약 네비게이션 바의 마지막 값이 마지막 페이지보다 크다면 네비게이션 바의 마지막 값은 마지막 페이지가 됨
		if (pageNaviEnd > lastPage) {
			pageNaviEnd = lastPage;
		}
		// 리턴값 저장
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("lastPage", lastPage);
		returnMap.put("pageNaviSize", pageNaviSize);
		returnMap.put("pageNaviBegin", pageNaviBegin);
		returnMap.put("pageNaviEnd", pageNaviEnd);
		
		// 파라미터값 저장
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("rowPerPage", rowPerPage);
		paramMap.put("beginRow", beginRow);
		
		List<Subject> subjectList = subjectMapper.selectSubjectList(paramMap);
		returnMap.put("subjectList", subjectList);
		
		return returnMap;
	}
	
	// 과목 정보를 출력하는 메소드
	// 매개변수: subjectNo(과목 고유번호)
	// 리턴값: 고유번호에 해당하는 과목 정보
	public Subject getSubjectDetail(int subjectNo) {
		Subject subjectDetail = subjectMapper.selectSubjectDetail(subjectNo);
		logger.debug(subjectDetail.toString());
		return subjectDetail;
	}
	
	// 운영자가 과목 정보를 입력하는 메소드
	// 매개변수: subject(과목 정보)
	// 리턴값: 과목 정보 입력
	public void createSubject(Subject subject) {
		logger.debug(subject.toString());
		subjectMapper.insertSubject(subject);
	}
	
	// 운영자가 과목 정보를 수정하는 메소드
	// 매개변수: subject(과목 정보)
	// 리턴값: 과목 정보 수정
	public void modifySubject(Subject subject) {
		logger.debug(subject.toString());
		subjectMapper.updateSubject(subject);
	}
}
