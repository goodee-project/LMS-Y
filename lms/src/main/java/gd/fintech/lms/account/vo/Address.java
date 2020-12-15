package gd.fintech.lms.account.vo;

import lombok.Data;

// 주소의 정보를 담는 VO

@Data
public class Address {
	// 주소 번호(AUTO_INCREMENT, PK)
	private int id;				
	
	// 우편번호
	private String zipCode;		
	
	// 시도
	private String province;	
	
	// 시군구
	private String city;		
	
	// 읍면
	private String town;		
	
	// 도로명
	private String street;		
}
