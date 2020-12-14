package gd.fintech.lms.manager.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.manager.vo.StudentQueue;

// 학생 승인대기 mapper
@Mapper
public interface StudentQueueMapper {
	List<StudentQueue> selectStudentQueue(); // 학생 승인대기 리스트 
	StudentQueue selectStudentQueueDetail(String studentId); // 학생 승인대기 인원보기
	
	int insertStudentQueue(StudentQueue studentQueue); // 학생 승인대기 정보 입력
	int deleteStudentQueue(String studentId); // 학생 승인대기 정보 삭제
}
