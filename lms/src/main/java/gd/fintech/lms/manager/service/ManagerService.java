package gd.fintech.lms.manager.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gd.fintech.lms.account.mapper.AddressMapper;
import gd.fintech.lms.account.vo.Account;
import gd.fintech.lms.manager.mapper.ManagerMapper;
import gd.fintech.lms.manager.vo.Manager;


// 운영자가 하는 업무를 위한 서비스

@Service
public class ManagerService {
	// 운영자정보 mapper 
	@Autowired private ManagerMapper managerMapper;
	
	// 주소정보 mapper
	@Autowired private AddressMapper addressMapper;
	
	
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
	
	// 우편주소로 주소 리스트를 조회 메서드
	// 매개변수: 우편주소 
	// 리턴값: 우편주소에 따른 주소목록
	public List<String> getAddressListByZipcode(String zipCode){
		List<String> list = addressMapper.selectAddressByZipCode(zipCode);
		return list;
	}
	
	// 운영자의 비밀번호 변경
	// 매개변수: 계정의 정보
	// 리턴값: 해당하는 행의 변경
	public void modifyManagerPasswd(Account account) {
		managerMapper.updateManagerPassword(account);
	}
		
	// 운영자의 변경전의 Id 및 password
	// 매개변수: 운영자의 id 및 password
	// 리턴값: 변경전의 id 와 password의 확인
	public String getManagerPassword(String accountId,String accountPw) {
		return managerMapper.selectManagerPassword(accountId, accountPw);
	}	
}
