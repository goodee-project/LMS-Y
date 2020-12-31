package gd.fintech.lms.admin.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.admin.vo.ManagerQueue;

// 운영자의 회원가입 승인대기 Mapper(관리자가 운영자의 회원가입을 승인할 수 있음)

@Mapper
public interface ManagerQueueMapper {
	// 회원가입 승인 대기 중인 운영자 리스트를 페이징하여 출력
	// 매개변수: map에 저장된 페이징 변수 beginRow(해당 페이지), rowPerPage(페이지에 표시할 항목수)와 검색 변수 searchType(검색조건), searchKeyword(검색어)
	// 리턴값: 회원가입 승인대기 중인 운영자 정보 리스트
	// 검색을 했다면 검색 결과에 따른 회원가입 승인대기 중인 운영자 정보 리스트 출력
	List<ManagerQueue> selectManagerQueueList(Map<String, Object> map);
	
	// 회원가입 승인 대기 중인 운영자 리스트의 페이징을 위해 총 항목수를 출력
	// 매개변수:
	// #1. searchType(검색조건)
	// #2. searchKeyword(검색어)
	// 리턴값: 회원가입 승인대기 중인 운영자 정보의 총 항목수
	// 검색을 했다면 검색 결과에 따른 승인대기 중인 운영자 정보의 총 항목수 출력
	int selectManagerQueueCount(String searchType, String searchKeyword);
	
	// 회원가입 승인대기 중인 운영자의 개인정보를 출력
	// 매개변수: accountId(아이디)
	// 리턴값: 아이디에 해당하는 회원가입 승인대기 중인 운영자의 개인정보
	ManagerQueue selectManagerQueueDetail(String accountId);
	
	// 운영자가 회원가입 시 자신의 정보를 입력
	// 매개변수: managerQueue(운영자 개인정보)
	// 리턴값: 변경된 행의 갯수
	int insertManagerQueue(ManagerQueue managerQueue);
		
	// 관리자가 운영자의 회원가입을 승인 또는 거부 시 운영자의 개인정보를 삭제
	// 매개변수: accountId(아이디)
	// 리턴값: 변경된 행의 갯수
	int deleteManagerQueue(String accountId);
}
