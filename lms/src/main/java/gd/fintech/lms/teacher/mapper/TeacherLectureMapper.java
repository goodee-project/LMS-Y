package gd.fintech.lms.teacher.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.manager.vo.Lecture;

//강사 자신의 강좌 Mapper

@Mapper
public interface TeacherLectureMapper {
	
	//강사 강좌 목록 및 페이징
	//매개변수:강사 아이디
	//리턴값:변경된 행 갯수
	List<Lecture> selectTeacherLectureListByPage(Map<String,Object>map);
	
	//강사 강좌 목록 페이징 카운트
	//매개변수: 강사 아이디
	int selectTeacherLectureCount(String accountId);
	
	//각 강좌별 정보 상세보기 출력
	//매개변수: 강좌 고유번호
	//리턴값:변경된 행 갯수
	Lecture selectTeacherLectureOne(int lectureNo);
	
}
