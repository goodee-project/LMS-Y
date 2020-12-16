package gd.fintech.lms.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gd.fintech.lms.account.vo.Account;
import gd.fintech.lms.account.mapper.MemberMapper;

// 회원 등록 여부를 확인하기 위한 서비스 클래스

@Service
@Transactional
public class MemberService {
	// MemberMapper 객체 주입
	@Autowired private MemberMapper memberMapper;
	
	// 계정 등록 여부를 조회한 결과를 가져오는 메소드
	// 매개변수: 로그인뷰에서 입력하여 넘어온 계정 ID, PW
	// 리턴값: 계정 조회 값이 없으면 null 반환, 있으면 조회된 계정 정보(ID, Level)를 반환
	public Account getMemberById(Account account) {	
		// 계정 조회 값을 Account VO 객체에 담기
		Account memberCk = memberMapper.selectMemberById(account);	
		System.out.println(memberCk + ": 서비스에서 받은 계정 조회값");
		if(memberCk != null) {
			return memberCk;
		}
		return null;
	}
}
