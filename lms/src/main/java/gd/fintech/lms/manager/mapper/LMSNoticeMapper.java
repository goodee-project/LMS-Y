package gd.fintech.lms.manager.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.manager.vo.LMSNotice;

// LMS 공지사항 mapper

@Mapper
public interface LMSNoticeMapper {
	// 공지사항 리스트 페이지
	// 매개변수 : 시작row와 가져올 row의 갯수를 map으로 가져옴
	// 리턴값 : lms공지사항정보의 리스트
	List<LMSNotice> selectLMSNoticeListByPage(Map<String, Object> map);
	
	// 공지사항 상세보기 
	// 매개변수 : lms공지사항의 번호를 가져옴
	// 리턴값 : lms공지사항정보로 출력
	LMSNotice selectLMSNoticeDetail(int lmsNoticeNo);
	
	// 공지사항 검색
	// 매개변수 : 시작row, 가져올 row의 갯수, 검색어를 map으로 가져옴
	// 리턴값 : lms공지사항정보의 리스트
	List<LMSNotice> selectLMSNoticeListSearch(Map<String, Object> map);
	
	// 공지사항 게시물 개수 
	// 리턴값 : 공지사항 게시물 개수
	int selectLMSNoticeCount();

	// 공지사항 작성 매니저 이름 가져오기
	// 매개변수 : 매니저의 계정 ID
	// 리턴값 : 매니저의 이름
	String selectLMSNoticeWriter(String accountId);
	
	// 공지사항 입력
	// 매개변수 : LMS공지사항 정보(계정 id, 제목, 내용, 입력날짜, 수정날짜)
	// 리턴값 : 행 추가
	int insertLMSNotice(LMSNotice lmsNotice); 
	
	// 공지사항 수정
	// 매개변수 : LMS공지사항 정보(번호, 제목, 내용, 수정날짜)
	// 리턴값 : 행 수정
	int updateLMSNotice(LMSNotice lmsNotice); 
	
	// 공지사항 삭제
	// 매개변수 : LMS공지사항 번호를 가져옴
	// 리턴값 : 행 삭제
	int deleteLMSNotice(int lmsNoticeNo);
	
	// 공지사항 조회수 증가
	// 매개변수 : LMS공지사항 번호를 가져옴
	// 리턴값 : 해당 공지사항의 조회수 카운트 증가
	int updateLMSNoticeCountOfViews(int lmsNoticeNo);
}
