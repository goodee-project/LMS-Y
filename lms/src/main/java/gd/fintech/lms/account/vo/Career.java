package gd.fintech.lms.account.vo;

import lombok.Data;

// 경력(운영자,강사) 정보가 담긴 vo 
@Data
public class Career {
	private int careerNo;			// 경력번호(순번)
	private String accountId;		// 경력 아이디 
	private String careerContent;	// 경력 내용
	private String careerStartDate;	// 경력 시작일
	private String careerEndDate;	// 경력 끝일
}
