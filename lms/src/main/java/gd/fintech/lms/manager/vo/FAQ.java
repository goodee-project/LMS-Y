package gd.fintech.lms.manager.vo;

import lombok.Data;

// FAQ vo:  운영자가 자주 묻는 질문을 올리는 FAQ vo
@Data
public class FAQ {
  private int faqNo; //  FAQ 번호
  private String accountId;  // 계정 id 
  private String faqWriter; //FAQ 작성자
  private String faqTitle;  // FAQ 제목
  private String faqContent;  // FAQ  내용
  private String faqCreateDate;// FAQ 작성 날짜 
  private String faqUpdateDate;// FAQ 수정 날짜
  private int faqCount; // FAQ 조회수
  private String faqCategory; //FAQ 카테고리
  
  

}
