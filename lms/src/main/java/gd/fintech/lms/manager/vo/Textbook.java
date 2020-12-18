package gd.fintech.lms.manager.vo;

import java.util.List;

import lombok.Data;

// 교재 정보 vo

@Data
public class Textbook {
	// 교재 고유번호(국제 표준 도서 번호)
	private String textbookISBN;
	
	// 교재 이름
	private String textbookTitle;
	
	// 교재 저자
	private String textbookWriter;
	
	// 교재 출판사
	private String textbookPublisher;
	
	// 교재 출판일
	private String textbookPublishDate;
	
	// 교재 정보
	private String textbookInfo;
	
	// 교재 가격
	private int textbookPrice;
	
	// 교재 정보 입력일자
	private String textbookCreateDate;
	
	// 교재 정보 수정일자
	private String textbookUpdateDate;
	
	// 교재 정보 리스트
	private List<Textbook>textbookInfoList;
	
}
