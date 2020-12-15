package gd.fintech.lms.admin.mapper;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.admin.vo.Admin;

// 관리자 정보 Mapper
@Mapper
public interface AdminMapper {
	// 관리자가 자신의 개인정보를 조회
	Admin selectAdmin(String adminId);
		
	// 관리자가 자신의 개인정보를 수정
	int updateAdmin(Admin admin);
}
