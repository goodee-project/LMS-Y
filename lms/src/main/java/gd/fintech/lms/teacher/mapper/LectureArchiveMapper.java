package gd.fintech.lms.teacher.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.teacher.vo.LectureArchive;

//자료실 Mapper

@Mapper
public interface LectureArchiveMapper {
	//자료실 목록
	List<LectureArchive> selectLectureArchiveListByPage(Map<String,Object>map);
	
	//자료실 상세보기
	LectureArchive selectLectureArchive(int lectureArchiveNo);
	
	//자료실 페이지 카운트
	int selectLectureArchiveCount();
	
	//자료실 입력
	int insertLectureArchive(LectureArchive lectureArchive);
	
	//자료실 수정
	int updateLectureArchive(LectureArchive lectureArchive);
	
	//자료실 삭제
	int deleteLectureArchive(int lectureArchiveNo);
	
	
}
