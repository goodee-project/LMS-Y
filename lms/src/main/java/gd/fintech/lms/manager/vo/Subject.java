package gd.fintech.lms.manager.vo;

import java.util.List;

import lombok.Data;

// 과목 정보 vo

@Data
public class Subject {
	// 과목 고유번호
	private int subjectNo;
	
	// 과목 이름
	private String subjectName;
	
	// 과목 생성일자
	private String subjectCreateDate;
	
	// 과목 수정일자
	private String subjectUpdateDate;
	
	// 과목 총 이수일수
	private int subjectTotalDay;
	
	// 과목 정보
	private String subjectInfo;
	
	// 과목 정보 리스트
	private List<Subject>subjectInfoList;
}
