package gd.fintech.lms.manager.vo;

import lombok.Data;

// 과목 정보 vo
@Data
public class Subject {
	private int subjectNo; // 과목 고유번호
	private String subjectName; // 과목 이름
	private String subjectCreateDate; // 과목 생성일자
	private String subjectUpdateDate; // 과목 수정일자
	private int subjectTotalDay; // 과목 총일수
	private String subjectInfo; // 과목 정보
}
