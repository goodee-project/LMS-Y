package gd.fintech.lms.manager.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.manager.vo.Lecture;

@Mapper
public interface LectureManagerMapper {
	
	List<Lecture> selectLectureListByPage(Map<String, Object> map); //강좌 리스트 
	int selectLecTotalCount(); //강좌 개수
	int insertLecture(Lecture lecture); // 강좌 입력 
	int deleteLecture(int lectureId); // 강좌 삭제 
	int updateNotice(Lecture lecture); // 강좌 수정
	Lecture selectLectureOne(int lectureId); // 강좌 상세


}
