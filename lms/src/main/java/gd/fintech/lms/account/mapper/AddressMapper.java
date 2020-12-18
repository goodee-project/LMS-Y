package gd.fintech.lms.account.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.account.vo.Address;

// 주소 정보에 관한 매퍼 인터페이스s

@Mapper
public interface AddressMapper {
	
	// 우편번호로 주소 목록을 조회(검색) 메소드
	// 매개변수: 우편번호, 페이징을 위한 시작페이지, 한 페이지 행의 수
	// 리턴값: 우편번호에 따른 주소 목록 리스트
	List<Address> getAddressByZipCode(Map<String,Object>map);
	
	// 주소 전체 목록을 조회하는 메소드
	// 매개변수: 페이징을 위한 시작페이지, 한 페이지 행의 수
	// 리턴값: 전체 주소 리스트(페이징)
	List<Address> getSelectAddressByPage(Map<String,Object>map);
	
	// 주소 전체 목록 개수(페이징을 위한 카운트)
	// 리턴값: 페이징 카운트
	int selectAddressCount();
}
