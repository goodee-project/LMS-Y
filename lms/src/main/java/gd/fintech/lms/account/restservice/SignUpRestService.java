package gd.fintech.lms.account.restservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gd.fintech.lms.account.restmapper.AddressRestMapper;

// 회원가입을 위한 서비스

@Service
@Transactional
public class SignUpRestService {
	// AddressRestMapper 객체 주입(주소)
	@Autowired AddressRestMapper addressRestMapper;
	
	// 우편주소로 주소 리스트를 조회 메소드
	// 매개변수: 우편주소
	// 리턴값: 우편주소에 따른 주소 목록
	public List<String> getAddressListByZipCode(String zipCode) {
		List<String> list = addressRestMapper.selectRestAddressByZipCode(zipCode);
		return list;
	}
}
