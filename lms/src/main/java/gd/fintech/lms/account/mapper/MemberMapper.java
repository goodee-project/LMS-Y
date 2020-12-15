package gd.fintech.lms.account.mapper;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.account.vo.Account;

@Mapper
public interface MemberMapper {
	Account selectMemberById(Account account);	// 계정 조회하는 메서드
}
