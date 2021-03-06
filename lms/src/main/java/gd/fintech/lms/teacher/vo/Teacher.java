package gd.fintech.lms.teacher.vo;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import gd.fintech.lms.account.vo.Career;
import gd.fintech.lms.account.vo.Education;
import gd.fintech.lms.account.vo.License;
import lombok.Data;


//강사 VO

@Data
public class Teacher {
	//강사 아이디
	private String accountId;
	
	//강사 이메일
	private String teacherEmail;
	
	//강사 이름
	private String teacherName;
	
	//강사 전화번호
	private String teacherPhone;
	
	//강사 성별
	private String teacherGender;
	
	//강사 생년월일
	private String teacherBirth;
	
	//강사 메인주소
	private String teacherAddressMain;
	
	//강사 서브주소
	private String teacherAddressSub;
	
	//강사 프로필 사진
	private String teacherImage;
	
	//강사 계정 활성화 일시
	private String teacherAccessDate;
	
	//강사 업데이트날짜 일시
	private String teacherUpdateDate;
	
	//계정을 승인한 운영자의 아이디
	private String managerIdAccess;
	
	//강사 한줄소개 
	private String teacherInfo; 
	
	//Multipart를 위한 프로퍼티 추가
	private List<AccountImage> imageFileList;
	
	//경력vo
	private List<Career> careerList;
	
	//자격증vo
	private List<License> licenseList;
	
	//학력vo
	private List<Education> educationList;
}
