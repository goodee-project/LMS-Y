package gd.fintech.lms.account.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gd.fintech.lms.account.mapper.AccountMapper;
import gd.fintech.lms.account.mapper.AddressMapper;
import gd.fintech.lms.account.vo.Account;
import gd.fintech.lms.dto.SignUpForm;
import gd.fintech.lms.manager.mapper.StudentQueueMapper;
import gd.fintech.lms.manager.vo.StudentQueue;

// 회원가입 기능을 위한 서비스 클래스

@Service
@Transactional
public class SignUpService {
	// AccountMapper 객체 주입
	@Autowired private AccountMapper accountMapper;
	// AddressMapper 객체 주입
	@Autowired private AddressMapper addressMapper;
	// StudentQueueMapper 객체 주입
	@Autowired private StudentQueueMapper studentQueueMapper;
	
	// 회원가입시 Account 테이블에 회원 정보를 입력하는 메소드(vo로 전환)
	// 매걔변수: 회원가입폼에서 입력된 회원정보(id,pw,level)
	// 리턴값: 회원정보 입력
	public int createSignUpAccount(SignUpForm signUpForm) {
		Account account = new Account();
		account.setAccountId(signUpForm.getAccountId());
		account.setAccountPw(signUpForm.getAccountPw());
		account.setAccountLevel(signUpForm.getAccountLevel());
		return accountMapper.insertAccount(account);
	}
	
	// 회원가입시 Student_Queue 테이블에 회원 정보를 입력하는 메소드(vo로 전환)
	// 매개변수: 회원가입폼에서 입력된 회원정보(id,email,name,phone,gender,birth,mainAddress,sub)
	// 리턴값: 학생 승인대기에 입력하는 매퍼
	public int createSignUpStudentQueue(SignUpForm signUpForm) {
		StudentQueue studentQueue = new StudentQueue();
		studentQueue.setAccountId(signUpForm.getAccountId());
		studentQueue.setStudentEmail(signUpForm.getAccountEmail());
		studentQueue.setStudentName(signUpForm.getAccountName());
		studentQueue.setStudentPhone(signUpForm.getAccountPhone());
		studentQueue.setStudentGender(signUpForm.getAccountGender());
		studentQueue.setStudentBirth(signUpForm.getAccountBirth());
		studentQueue.setStudentAddressMain(signUpForm.getAccountAddressMain());
		studentQueue.setStudentAddressSub(signUpForm.getAccountAddressSub());
		return studentQueueMapper.insertStudentQueue(studentQueue);
	}
	
	// 계정ID로 중복 체크를 위한 메소드
	// 매개변수: 계정ID
	// 리턴값: 조회되는 계정 ID
	public String getAccountId(String accountId) {
		return accountMapper.selectAccountId(accountId);
	}
	
	// 우편주소로 주소 리스트를 조회 메소드
	// 매개변수: 우편주소
	// 리턴값: 우편주소에 따른 주소 목록
	public List<String> getAddressListByZipCode(String zipCode) {
		List<String> list = addressMapper.selectAddressByZipCode(zipCode);
		return list;
	}
}
