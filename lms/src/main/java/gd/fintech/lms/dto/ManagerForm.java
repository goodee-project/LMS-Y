package gd.fintech.lms.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

// 운영자 DTO

@Data
public class ManagerForm {
	// 운영자 아이디
	private String accoutnId;
	// 운영자 이메일
	private String managerEmail;
	// 운영자 이름
	private String managerName;
	// 운영자 연락처
	private String managerPhone;
	// 운영자 성별 (남, 여)
	private String managerGender;
	// 운영자 생년월일
	private String managerBirth;
	// 운영자 직책
	private String managerPosition;
	// 운영자 메인주소
	private String	managerAddressMain;			
	// 운영자 상세주소
	private String managerAddressSub;	
	// 운영자 프로필 사진
	private MultipartFile managerImage;
	// 운영자 계정 활성화 일시
	private String managerAccessdate;
	// 운영자 계정 업데이트 일시
	private String	managerUpdatedate;	
	//multipart
	private List<MultipartFile> imageFileList;
}
