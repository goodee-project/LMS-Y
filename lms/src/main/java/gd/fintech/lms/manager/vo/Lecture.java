package gd.fintech.lms.manager.vo;

import java.util.List;

import gd.fintech.lms.teacher.vo.Report;
import lombok.Data;

//  강좌 vo :  운영자가 개설할 강좌 관련 vo

@Data
public class Lecture {
	// 강좌 번호
	private int lectureNo; 
	
	// 강좌 id
	private String accountId;
	
	// 과목 번호
	private int subjectNo; 
	
	// 강사 이름 
	private String teacherName; 
	
	// 강좌 이름 
	private String lectureName; 
	
	// 교재 고유번호(국제 표준 도서 번호)
	private String textbookISBN;
	
	// 시작 날짜
	private String lectureStartDate;
	
    // 종료 날짜
	private String lectureEndDate; 
	
	// 강좌 정원수
	private int lectureTotal;
	
	// 강의실 번호
	private int classroomNo; 
	
	// 강좌 생성날짜
	private String lectureCreateDate; 
	
	// 강좌 수정날짜
	private String lectureUpdateDate; 
	
	// 강좌 정보 리스트
	private List<Lecture>lectureInfo;
	
	// 강좌의 과목 정보 리스트
	private List<Subject>subjectInfoList;
	
	// 강좌의 과제제출 정보
	private List<Report>reportInfoList;
	
	// 강좌의 강의실 정보 리스트
	private List<Classroom>classroomInfoList;
	
	// 강좌의 교재 정보 리스트 
	private List<Textbook>textbookInfoList;
	
	//과목 vo 추가
	private Subject	subject;
	
}
