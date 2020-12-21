package gd.fintech.lms.teacher.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.teacher.vo.LectureNotice;

//강좌별 공지사항 Mapper

@Mapper
public interface LectureNoticeMapper {
	//강좌별 공지사항 목록 및 페이징
	//매개변수:Map.put()을 사용해 강좌고유번호,페이징 변수 및 공지사항 목록을 삽입 
	//리턴값:공지사항 전체 목록
	List<LectureNotice> selectLectrueNoticeListByPage(Map<String, Object> map);
	
	//강좌별 공지사항 상세보기
	//매개변수:강좌 공지사항 고유번호
	//리턴값:강좌 공지사항 고유번호에 해당하는 자료실 정보
	LectureNotice selectLectureNoticeOne(int lectureNoticeNo);
	
	//강좌별 공지사항 입력
	//매개변수:set을 사용하여 제목,내용에 정보를 추가
	//리턴값:변경된 행 갯수
	int insertLetureNotice(LectureNotice lectureNotice);
	
	//강좌별 공지사항 수정
	//매개변수:set 사용하여
	//리턴값:변경된 행 갯수
	int upldateLetureNotice(LectureNotice lectureNotice);
	
	//강좌별 공지사항 삭제
	//매개변수:삭제할 공지사항 고유번호
	//리턴값:변경된 행 갯수
	int deleteLetureNotice(int lectureNoticeNo);
	
	//강좌별 공지사항 카운트
	//매개변수:강좌 고유번호
	int selectLectureNoticeCount(int lectureNo);

	
}
