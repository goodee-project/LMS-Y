package gd.fintech.lms.account.vo;

import lombok.Data;

// 학력(운영자,강사) 정보가 담긴 vo
@Data
public class Education {
	private String educationNo;		// 학력번호(순번)
	private String accountId;		// 계정 아이디
	private String educationSchool;	// 출신학교
	private String educationMajor;	// 전공
	private String educationStartDate;	// 입학일
	private String educationEndDate;	// 졸업일
}
