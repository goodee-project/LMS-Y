package gd.fintech.lms.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gd.fintech.lms.account.mapper.AccountMapper;
import gd.fintech.lms.dto.SignUpForm;
import gd.fintech.lms.manager.mapper.StudentQueueMapper;

// 회원가입 기능을 위한 서비스 클래스

@Service
@Transactional
public class SignUpService {
	// AccountMapper 객체 주입
	@Autowired private AccountMapper accountMapper;
	// StudentQueueMapper 객체 주입
	@Autowired private StudentQueueMapper studentQueueMapper;
	
	// 회원가입시 Account 테이블에 회원 정보를 입력하는 메소드
	// 매걔변수: 회원가입폼에서 입력된 회원정보(id,pw,level)
	// 리턴값: 회원정보 입력
	public int createSignUpAccount(SignUpForm signUpForm) {
		return accountMapper.insertAccount(signUpForm);
	}
	
	// 회원가입시 Student_Queue 테이블에 회원 정보를 입력하는 메소드
	// 매개변수: 회원가입폼에서 입력된 회원정보(id,email,name,phone,gender,birth,mainAddress,sub)
	public int createSignUpStudentQueue(SignUpForm signUpForm) {	
		return studentQueueMapper.insertStudentQueue(signUpForm);;
	}
}
