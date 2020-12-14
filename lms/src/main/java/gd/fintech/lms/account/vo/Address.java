package gd.fintech.lms.account.vo;

import lombok.Data;

@Data
public class Address {
	// 주소는 필요한 컬럼만 추가
	private int id;				// 주소 아이디(순번)
	private String zipCode;		// 우편번호
	private String province;	// 시도
	private String city;		// 시군구
	private String town;		// 읍면
	private String street;		// 도로명
}
