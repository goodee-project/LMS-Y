package gd.fintech.lms.admin.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.admin.vo.ManagerQueue;

// 운영자의 회원가입 승인대기를 위한 Mapper(관리자가 운영자의 회원가입을 승인할 수 있음)

@Mapper
public interface ManagerQueueMapper {
	// 회원가입 시 운영자의 개인정보를 managerQueue 테이블에 입력
	// 매개변수: 회원가입 승인대기 중인 운영자의 개인정보 
	// 리턴값: 변경된 행 갯수
	int insertManagerQueue(ManagerQueue managerQueue);
	
	// 관리자가 운영자의 회원가입을 승인 시 운영자의 개인정보를 managerQueue 테이블에서 삭제
	// 매개변수: 운영자 아이디
	// 리턴값: 변경된 행 갯수
	int deleteManagerQueue(String accountId);
	
	// 승인대기 중인 운영자의개인정보를 페이징하여 리스트로 출력
	// 매개변수: 
	// 리턴값: 회원가입 승인대기 중인 운영자의 개인정보의 리스트
	List<ManagerQueue> selectManagerQueueList(Map<String, Integer> map);
	
	// 승인대기 중인 운영자의 개인정보 상세정보를 출력
	// 매개변수: 운영자 아이디
	// 리턴값: 운영자 아이디에 해당하는 회원가입 승인대기 중인 운영자의 개인정보의 상세보기
	ManagerQueue selectManagerQueueDetail(String accountId);
}
