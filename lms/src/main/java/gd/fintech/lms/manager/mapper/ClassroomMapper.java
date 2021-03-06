package gd.fintech.lms.manager.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import gd.fintech.lms.manager.vo.Classroom;

// 강의실에 대한 메퍼 인터페이스

@Mapper
public interface ClassroomMapper {
	// 강의실 목록
	// 매개변수: MAP beginRow, rowPerPage
	// 리턴값: 강의실 리스트
	List<Classroom> selectClassroomListByPage(Map<String, Object> map);
	
	// 강의실 입력
	// 매개변수: 강의실 정보
	// 리턴값: 행의 추가
	int insertClassromm(Classroom classroom);
	
	// 강의실 수정
	// 매개변수: 강의실 정보
	// 리턴값: 해당하는 행의 수정
	int updateClassroom(Classroom classroom);
	
	// 강의실 상세보기
	// 매개변수: 강의실 고유번호
	// 리턴값: 해당하는 강의실의 상세한 정보
	Classroom classroomDetail(int classroomNo);
	
	// 강의실 개수
	// 리턴값: 강의실의 행의 총 개수
	int selectClassroomCount();
	
}
