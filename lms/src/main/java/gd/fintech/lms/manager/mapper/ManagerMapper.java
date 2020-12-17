package gd.fintech.lms.manager.mapper;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.manager.vo.Manager;
import gd.fintech.lms.student.vo.Student;

// 운영자 정보에 대한 메퍼 인터페이스

@Mapper
public interface ManagerMapper {
	
	// 운영자 개인의 정보 
	// 매개변수: 운영자 id
	// 리턴값: 운영자 id에 해당하는 운영자의 정보
	Manager selectManagerOne(String accountId);
	
	
	// 운영자 정보 수정 
	// 매개변수: 운영자 정보
	// 리턴값: 행의 수정
	int updateManager(Manager manager);
	
	
	// 운영자 정보 입력
	// 매개변수: 운영자 정보
	// 리턴값: 행의 입력
	int insertManager(Manager manager);
	
	
	
	
	
}
