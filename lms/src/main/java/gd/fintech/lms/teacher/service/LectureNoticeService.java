package gd.fintech.lms.teacher.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gd.fintech.lms.teacher.mapper.LectureNoticeMapper;
import gd.fintech.lms.teacher.vo.LectureNotice;


@Service
@Transactional
public class LectureNoticeService {
	// Logger
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	// LectureNoticeMapper 객체 주입
	@Autowired
	private LectureNoticeMapper lectureNoticeMapper;

	// 강좌별 공지사항 목록 출력 메서드
	// 매개변수:강좌 고유번호
	// 리턴값:강좌 고유번호를 조회하여 공지사항 목록을 반환
	public Map<String,Object> getLectureNoticeListByPage(int lectureNo, int currentPage) {
		// 현재 페이지 표시할 데이터 수
		int rowPerPage = 10;
		// 시작 페이지
		int beginRow = (currentPage - 1) * rowPerPage;
		// 전체 페이지 개수
		int lectureNoticeCount = lectureNoticeMapper.selectLectureNoticeCount(lectureNo);
		// 마지막 페이지
		int lastPage = lectureNoticeCount / rowPerPage;
		// 10 미만의 개수의 데이터가 있는 페이지 표시
		if (lectureNoticeCount % rowPerPage != 0) {
			lastPage += 1;
		}
		// 전체 페이지가 0개이면 현재 페이지도 0으로 표시
		if (lastPage == 0) {
			currentPage = 0;
		}
		Map<String,Object> paramMap = new HashMap<String,Object>();
		
		paramMap.put("rowPerPage", rowPerPage);
		paramMap.put("beginRow", beginRow);
		paramMap.put("lectureNo", lectureNo);

		List<LectureNotice> lectureNoticeList = lectureNoticeMapper.selectLectrueNoticeListByPage(paramMap);
		logger.trace(lectureNoticeList + "<---lectureNoticeList");
	
		Map<String,Object>map = new HashMap<String,Object>();
		map.put("lastPage", lastPage);
		map.put("lectureNoticeList", lectureNoticeList);
		
		

		return map;
	}
	
	//강좌별 공지사항 상세보기 메서드
	//매개변수:강좌별 공지 고유번호
	//리턴값:목록출력
	public LectureNotice getLectureNoticeOne(int lectureNoticeNo) {
		LectureNotice lectureNotice = lectureNoticeMapper.selectLectureNoticeOne(lectureNoticeNo);
		return lectureNotice;
	}
	
	//강좌별 공지사항 추가 메서드
	//매개변수:lectureNotice
	//리턴값:공지사항 추가
	public int createLectureNotice(LectureNotice lectureNotice) {
		return lectureNoticeMapper.insertLetureNotice(lectureNotice);
	}
	
	//강좌별 공지사항 수정 메서드
	//매개변수:공지사항 고유번호
	//리턴값:공지사항 수정
	public int modifyLectureNotice(LectureNotice lectureNotice) {
		return lectureNoticeMapper.upldateLetureNotice(lectureNotice);
	}
	
	//강좌별 공지사항 삭제
	//매개변수:공지사항 고유번호
	//리턴값:공지사항 삭제
	public void removeLectureNotice(int lectureNoticeNo) {
		lectureNoticeMapper.deleteLetureNotice(lectureNoticeNo);
	}
}
