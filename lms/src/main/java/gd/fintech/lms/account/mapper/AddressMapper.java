package gd.fintech.lms.account.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.account.vo.Address;

// 주소 정보에 관한 매퍼 인터페이스s

@Mapper
public interface AddressMapper {
	
	// 우편번호로 주소 목록을 조회(검색) 메소드
	// 매개변수: 우편번호
	List<Address> getAddressByZipCode(Map<String,Object>map);
	
	// 주소 전체를 띄워주는 목록 메소드
	//매개변수:주소목록
	List<Address> getSelectAddressByPage(Map<String,Object>map);
	
	//주소 목록 개수
	//페이징 카운트
	int selectAddressCount();
}
