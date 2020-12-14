package gd.fintech.lms.manager.vo;

import lombok.Data;

// FAQ  관련 vo
@Data
public class Faq {
  private int faqNo; //  FAQ 번호
  private String accountId;  //계정 id 
  private String faqWriter; //FAQ 작성자
  private String faqTitle;  // FAQ 제목
  private String faqContent;  // FAQ  내용
  private String faqCreatedate;// FAQ 작성시간 
  private String faqUpdatedate;// FAQ 수정 시간
  private int faqCount; // FAQ 조회수
  
  

}
