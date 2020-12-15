package gd.fintech.lms.account.mapper;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.account.vo.Address;

// 주소 정보에 관한 매퍼 인터페이스

@Mapper
public interface AddressMapper {
	// 모든 주소를 조회하는 메소드
	Address selectAddress();	
}
