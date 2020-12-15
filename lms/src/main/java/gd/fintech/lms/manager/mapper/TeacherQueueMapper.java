package gd.fintech.lms.manager.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.manager.vo.TeacherQueue;

// 강사 승인대기 mapper

@Mapper
public interface TeacherQueueMapper {
	// 강사 승인대기 리스트
	// 리턴값 : 강사승인대기 정보(이름, 전화번호)의 리스트 
	List<TeacherQueue> selectTeacherQueueList();
	
	// 강사 승인대기 상세보기
	// 매개변수 : 강사계정ID
	// 리턴값 : 강사승인대기의 모든 정보
	TeacherQueue selectTeacherQueueDetail(String teacherId); 
	
	// 강사 승인대기 정보 입력
	// 매개변수 : 강사승인대기의 모든 정보
	// 리턴값 : 행 추가
	int insertTeacherQueue(TeacherQueue teacherQueue);
	
	// 강사 승인대기 정보 삭제
	// 매개변수 : 강사계정ID
	// 리턴값 : 행삭제
	int deleteTeacherQueue(String teacherId); 
}
