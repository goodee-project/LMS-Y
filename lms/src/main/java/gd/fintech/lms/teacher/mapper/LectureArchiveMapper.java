package gd.fintech.lms.teacher.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.teacher.vo.LectureArchive;

//자료실 Mapper

@Mapper
public interface LectureArchiveMapper {
	//자료실 목록
	//매개변수:Map.put()을 사용해 페이징 및 자료실 목록을 삽입
	//리턴값:자료실 전체 목록
	List<LectureArchive> selectLectureArchiveListByPage(Map<String,Object>map);
	
	//자료실 상세보기
	//매개변수:강좌 자료실 고유번호
	//리턴값:강좌 자료실 고유번호에 해당하는 자료실 정보
	LectureArchive selectLectureArchiveOne(int lectureArchiveNo);
	
	//자료실 페이지 카운트
	//매개변수:강좌 고유번호,검색
	int selectLectureArchiveCount(int lectureNo,String lectureArchiveSearch);
	//자료실 입력
	//매개변수:자료실 입력폼에서 제목,내용 입력
	//리턴값:변경된 행의 갯수
	int insertLectureArchive(LectureArchive lectureArchive);
	
	//자료실 수정
	//매개변수:자료실 수정폼에서 제목,내용 수정
	//리턴값:변경된 행의 갯수
	int updateLectureArchive(LectureArchive lectureArchive);
	
	//자료실 조회수 카운트
	//매개변수:강좌별 자료실 고유번호
	//리턴값:변경된 행의 갯수
	int updateLectureArchiveCount(int lectureArchiveNo);
	
	//현재 LMS 기능중 자료실 삭제는 필요하지 않다고 생각하여 구현하지 않았음.(수정에서 파일삭제 및 내용 수정만 가능하게 함.)
	//추후 필요하면 추가 하겠음.
	//자료실 삭제
	//매개변수:삭제할 강좌 자료실 고유번호
	//리턴값:변경된 행의 갯수
	int deleteLectureArchive(int lectureArchiveNo);
	
}