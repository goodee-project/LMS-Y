package gd.fintech.lms.admin.mapper;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.admin.vo.Admin;

// 관리자 정보 Mapper
@Mapper
public interface AdminMapper {
	// 관리자 개인정보를 출력
	// 매개변수: accountId(아이디)
	// 리턴값: 아이디에 해당하는 관리자 개인정보
	Admin selectAdmin(String accountId);
	
	// 관리자 이름을 출력
	// 매개변수: accountId(아이디)
	// 리턴값: 아이디에 해당하는 관리자 이름
	String selectAdminName(String accountId);
}
