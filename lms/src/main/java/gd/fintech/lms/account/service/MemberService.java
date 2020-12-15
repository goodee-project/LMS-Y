package gd.fintech.lms.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gd.fintech.lms.account.vo.Account;
import gd.fintech.lms.account.mapper.MemberMapper;

@Service
@Transactional
public class MemberService {
	@Autowired private MemberMapper memberMapper;	// MemberMapper 객체 주입
	// 계정 조회 결과를 가져오는 메소드
	public Account getMemberById(Account account) {	// 파라미터(로그인 계정 id,pw)
		Account memberCk = memberMapper.selectMemberById(account);	// 계정 조회값을 Account 객체에 담기
		// memberCk의 값이 존재하면(계정 정보가 있으면) 결과 넘기기
		if(memberCk != null) {
			return memberCk;
		}
		return null;	// 결과 없으면 null 반환
	}
}
