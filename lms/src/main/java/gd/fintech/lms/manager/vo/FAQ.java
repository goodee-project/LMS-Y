package gd.fintech.lms.manager.vo;

import lombok.Data;

// FAQ vo:  운영자가 자주 묻는 질문을 올리는 FAQ vo

@Data
public class FAQ {
	// FAQ 번호
	private int faqNo;

	// 계정 id
	private String accountId;

	// FAQ 작성자
	private String faqWriter;

	// FAQ 제목
	private String faqTitle;

	// FAQ 내용
	private String faqContent;

	// FAQ 작성 날짜
	private String faqCreateDate;

	// FAQ 수정 날짜
	private String faqUpdateDate;

	// FAQ 조회수
	private int faqCount;

	// FAQ 카테고리
	private String faqCategory;

}
