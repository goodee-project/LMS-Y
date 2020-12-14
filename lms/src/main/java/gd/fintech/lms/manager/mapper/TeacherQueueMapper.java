package gd.fintech.lms.manager.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.manager.vo.TeacherQueue;

// 강사 승인대기 mapper
@Mapper
public interface TeacherQueueMapper {
	List<TeacherQueue> selectTeacherQueue(); // 강사 승인대기 리스트 
	TeacherQueue selectTeacherQueueDetail(String teacherId); // 강사 승인대기 상세보기
	
	int insertTeacherQueue(TeacherQueue teacherQueue); // 강사 승인대기 정보 입력
	int deleteTeacherQueue(String teacherId); // 강사 승인대기 정보 삭제
}
