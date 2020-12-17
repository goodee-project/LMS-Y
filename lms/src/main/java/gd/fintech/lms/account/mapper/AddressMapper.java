package gd.fintech.lms.account.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.account.vo.Address;

// 주소 정보에 관한 매퍼 인터페이스

@Mapper
public interface AddressMapper {
	// 우편번호로 주소 목록을 조회하는 메소드
	// 매개변수: 우편번호
	List<Address> getAddressByZipCode(String zipCode);
}
