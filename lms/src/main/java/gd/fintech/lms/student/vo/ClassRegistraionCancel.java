package gd.fintech.lms.student.vo;

import lombok.Data;
//학생이 수강을 취소하는 vo
@Data
public class ClassRegistraionCancel {
		//수강 신청 번호
		private int classRegistrationNo;
	
		//학생 수강 취소일
		private String cancelCreateDate;
		
		//취소 이유(내용)
		private String cancelContent;	
}
