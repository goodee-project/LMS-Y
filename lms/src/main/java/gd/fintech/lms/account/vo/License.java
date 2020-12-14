package gd.fintech.lms.account.vo;

import lombok.Data;

// 자격증(운영자,강사,학생) 정보가 담긴 vo
@Data
public class License {
	private int licenseNo;			// 자격증번호(순번)
	private String accountId;		// 계정 번호
	private String licenseNumber;	// 자격증 일련번호
	private String licenseName;		// 자격증명
	private String licenseAgency;	// 발급기관
	private String licenseGetDate;	// 발급일
}
