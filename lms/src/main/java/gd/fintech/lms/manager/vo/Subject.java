package gd.fintech.lms.manager.vo;

import lombok.Data;

// 과목 VO: 과목의 정보 VO
@Data
public class Subject {
	private int subjectNo; // 과목 고유번호
	private String subjectName; // 과목 이름
	private String subjectCreatedate; // 과목 생성일자
	private String subjectUpdatedate; // 과목 수정일자
	private int subjectTotalday; // 과목 총일수
	private String subjectInfo; // 과목 정보
}
