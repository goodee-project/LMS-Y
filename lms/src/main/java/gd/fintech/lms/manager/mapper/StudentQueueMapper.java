package gd.fintech.lms.manager.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.dto.SignUpForm;
import gd.fintech.lms.manager.vo.StudentQueue;

// 학생 승인대기 mapper

@Mapper
public interface StudentQueueMapper {
	// 학생 승인대기 리스트
	// 리턴값 : 학생승인대기 정보(계정ID, 이름, 이메일, 전화번호)의 리스트 
	List<StudentQueue> selectStudentQueueList(); 
	
	// 학생 승인대기 인원보기
	// 매개변수 : 학생계정ID
	// 리턴값 : 학생승인대기의 모든 정보
	StudentQueue selectStudentQueueDetail(String accountId);
	
	// 학생 승인대기 정보 입력
	// 매개변수 : 학생승인대기의 모든 정보
	// 리턴값 : 행 추가
	int insertStudentQueue(SignUpForm signUpForm);
	
	// 학생 승인대기 정보 삭제
	// 매개변수 : 학생계정ID
	// 리턴값 : 행삭제
	int deleteStudentQueue(String accountId);
}
