package gd.fintech.lms.admin.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.admin.vo.ManagerQueue;

// 관리자가 운영자의 회원가입 정보 및 승인대기 관리를 위한 Mapper
@Mapper
public interface ManagerQueueMapper {
	// 회원가입 시 운영자의 가입정보를 managerQueue 테이블에 입력
	int insertManagerQueue(ManagerQueue managerQueue);
	
	// 회원가입 승인 시 운영자의 가입정보를 managerQueue 테이블에서 삭제
	int deleteManagerQueue(ManagerQueue managerId);
	
	// 운영자의 승인대기 가입정보를 페이징하여 리스트로 출력
	List<ManagerQueue> selectManagerQueueList(Map<String, Integer> map);
	
	// 운영자의 가입정보 상세보기
	ManagerQueue selectManagerQueueDetail(String managerId);
}
