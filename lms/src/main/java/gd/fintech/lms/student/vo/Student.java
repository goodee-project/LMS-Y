package gd.fintech.lms.student.vo;

import java.util.List;

import gd.fintech.lms.account.vo.License;
import gd.fintech.lms.manager.vo.Lecture;
import lombok.Data;
//학생 정보 vo
@Data
public class Student {
	//학생id
	private String accountId;
	
	//학생 이메일
	private String studentEmail;
	
	//학생 이름
	private String studentName;
	
	//학생 번호
	private String studentPhone;
	
	//학생 성별
	private String studentGender;
	
	//학생 생일
	private String studentBirth;
	
	//학생 주소
	private String studentAddressMain;
	
	//학생 상세주소
	private String studentAddressSub;
	
	//학생 이미지
	private String studentImage;
	
	//학생 승인날짜 now
	private String studentAccessDate;
	
	//학생 개인정보 수정날짜
	private String studentUpdateDate;
	
	//운영자가 학생등록을 승인한 날짜
	private String mangerIdAccess;
	
	// 강좌 정보 리스트
	private List<Lecture>lectureInfo;
	
	// 해당 과제를 참조하고 있는 과제제출 리스트
	private List<ReportSubmit> reportSubmitList;
	
	//학생 자격증 리스트
	private List<License> licenseList;
}
