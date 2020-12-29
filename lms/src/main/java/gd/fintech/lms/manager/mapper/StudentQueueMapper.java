package gd.fintech.lms.manager.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.manager.vo.StudentQueue;

// 학생 승인대기 mapper

@Mapper
public interface StudentQueueMapper {
	// 학생 승인대기 리스트
	// 매개변수 : 시작row와 가져올 row의 갯수, 검색한 학생의 이름을 map으로 가져옴
	// 리턴값 : 학생승인대기 정보(계정ID, 이름, 이메일, 전화번호)의 리스트 
	List<StudentQueue> selectStudentQueueListByPage(Map<String, Object> map); 
	
	// 학생 승인대기 인원보기
	// 매개변수 : 학생계정ID
	// 리턴값 : 학생승인대기의 모든 정보
	StudentQueue selectStudentQueueDetail(String accountId);
	
	// 학생 승인 대기 인원 수 
	// 매개변수 : 검색한 학생의 이름
	// 리턴값 : 학생 승인 대기인원의 인원 수
	int selectStudentQueueCount(String studentName);
	
	// 학생 승인대기 정보 입력
	// 매개변수 : 학생승인대기의 모든 정보
	// 리턴값 : 행 추가
	int insertStudentQueue(StudentQueue studentQueue);
	
	// 학생 승인대기 정보 삭제
	// 매개변수 : 학생계정ID
	// 리턴값 : 행삭제
	int deleteStudentQueue(String accountId);
}
