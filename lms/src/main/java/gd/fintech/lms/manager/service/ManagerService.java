package gd.fintech.lms.manager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gd.fintech.lms.manager.mapper.ManagerMapper;
import gd.fintech.lms.manager.vo.Manager;


// 운영자가 하는 업무를 위한 서비스

@Service
public class ManagerService {
	// 운영자정보 mapper 
	@Autowired private ManagerMapper managerMapper;
	

	// 운영자의 개인 정보를 볼수있는 서비스 메소드 
	// 매개변수: 운영자의 계정 ID
	// 리턴값: 운영자의 상세 정보 
	public Manager getManagerDetail(String accountId) {
		return managerMapper.selectManagerOne(accountId);
	}
	
	// 운영자의 개인 정보를 수정하는 서비스 메소드 
	// 매개변수: 운영자 정보
	// 리턴값: 운영자의 수정된 정보 
	public int modifyManager(Manager manager) {
		return managerMapper.updateManager(manager);
	}
}
