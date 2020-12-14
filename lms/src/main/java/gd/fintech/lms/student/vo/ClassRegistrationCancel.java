package gd.fintech.lms.student.vo;

import lombok.Data;
//수강취소
@Data
public class ClassRegistrationCancel {
	private int classRegistrationNo;// 학생 수강신청 목록
	private String cancelCreateDate;// 학생 수강 취소일
	private String cancleContent;// 취소 이유(내용)
}
