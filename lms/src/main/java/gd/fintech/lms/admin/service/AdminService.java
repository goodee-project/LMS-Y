package gd.fintech.lms.admin.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gd.fintech.lms.admin.mapper.AdminMapper;

// 관리자 정보 관련 Service

@Service
@Transactional
public class AdminService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// 관리자 정보 관련 Mapper
	@Autowired private AdminMapper adminMapper;
	
	// 관리자 이름을 출력하는 메소드
	// 매개변수: accountId(아이디)
	// 리턴값: 관리자 이름
	public String getAdminName(String accountId) {
		String adminName = adminMapper.selectAdminName(accountId);
		return adminName;
	}
}
