package gd.fintech.lms.account.restmapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AddressRestMapper {
	// 우편번호로 주소 모든 내용 조회 메소드
	// 매개변수: 우편번호
	// 리턴값: 우편번호에 따른 주소 목록 리스트
	List<String> selectRestAddressByZipCode(String zipcode);
}
