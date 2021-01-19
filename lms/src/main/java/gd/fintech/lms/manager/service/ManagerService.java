package gd.fintech.lms.manager.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import gd.fintech.lms.FilePath;
import gd.fintech.lms.account.mapper.AddressMapper;
import gd.fintech.lms.account.mapper.CareerMapper;
import gd.fintech.lms.account.mapper.EducationMapper;
import gd.fintech.lms.account.mapper.LicenseMapper;
import gd.fintech.lms.account.vo.Account;
import gd.fintech.lms.account.vo.Career;
import gd.fintech.lms.account.vo.Education;
import gd.fintech.lms.account.vo.License;
import gd.fintech.lms.dto.ManagerForm;
import gd.fintech.lms.manager.mapper.ManagerMapper;
import gd.fintech.lms.manager.vo.AccountImage;
import gd.fintech.lms.manager.vo.Manager;

// 운영자가 하는 업무를 위한 서비스

@Service
public class ManagerService {
	// Logger
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	// 운영자정보 mapper
	@Autowired
	private ManagerMapper managerMapper;

	// 주소정보 mapper
	@Autowired
	private AddressMapper addressMapper;

	// 경력 정보 mapper
	@Autowired
	private CareerMapper careerMapper;

	// 학력 정보 mapper
	@Autowired
	private EducationMapper educationMapper;

	// 자격증 정보 mapper
	@Autowired
	private LicenseMapper licenseMapper;

	// 운영자 정보 상세보기
	// 매개변수: 운영자 id
	// 리턴값: 운영자 정보를 출력
	public Map<String, Object> getManagerDetail(String accountId) {

		// 운영자 정보
		Manager manager = managerMapper.selectManagerOne(accountId);

		StringBuilder uri = null;
		if (managerMapper.selectMyImage(accountId) != null) {
			try {
				// 소스 파일 불러오기
				File file = new File(FilePath.getFilePath() + manager.getManagerImage());

				// 파일 컨텐츠타입 설정
				String contentType = managerMapper.selectMyImage(accountId).getImageFileType();

				// 바이트 배열로 파일 불러오기
				byte[] data = Files.readAllBytes(file.toPath());

				// 베이스64 문자열로 변환하기 (자바 8버전)
				String base64str = Base64.getEncoder().encodeToString(data);

				// "data URI" 생성
				uri = new StringBuilder();
				uri.append("data:");
				uri.append(contentType);
				uri.append(";base64,");
				uri.append(base64str);
			} catch (IOException e) { // 파일 로드에 실패했을 경우 Transactional 애노테이션에게 알려줌
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		Map<String, Object> map = new HashMap<>();
		// uri를 Controller로 리턴시키기
		map.put("imageURI", uri);
		map.put("manager", manager);

		return map;
	}

	// 운영자 정보 수정하기
	// 매개변수:운영자의 정보, 세션, 계정Id
	// 리턴값: 수정된 값
	public boolean modifyManager(ManagerForm managerForm, HttpSession session, String accountId) {
		Manager manager = new Manager();
		manager.setAccountId(managerForm.getAccountId());
		manager.setManagerEmail(managerForm.getManagerEmail());
		manager.setManagerName(managerForm.getManagerName());
		manager.setManagerPhone(managerForm.getManagerPhone());
		manager.setManagerGender(managerForm.getManagerGender());
		manager.setManagerBirth(managerForm.getManagerBirth());
		manager.setManagerPosition(managerForm.getManagerPosition());
		manager.setManagerAddressMain(managerForm.getManagerAddressMain());
		manager.setManagerAddressSub(managerForm.getManagerAddressSub());
		logger.debug(manager.toString());
		managerMapper.updateManager(manager);

		// 파일이 존재할 경우 for문을 돌면서 Multipartfile을 vo로 변환 후 첨부파일 추가
		if (managerForm.getImageFileList() != null) {
			// 기존에 있던 사진 정보 불러오기
			managerMapper.selectMyImage(accountId);
			managerMapper.selectManagerImageanddelete(accountId);

			for (MultipartFile mf : managerForm.getImageFileList()) {
				String fileNameUUID = UUID.randomUUID().toString().replace("-", "");

				try {
					// 물리적 파일을 생성(하드 디스크)
					String fileName = FilePath.getFilePath() + fileNameUUID;
					mf.transferTo(new File(fileName));
					logger.debug("파일 생성됨:" + fileName);
				} catch (Exception e) {
					// 해당 파일 생성 실패시
					// 예외 메세지를 출력
					e.printStackTrace();

					// Transactional 기능을 수행하는 Service 컴포넌트에게 예외 발생을 알려 작업 내역을 롤백하도록 유도함
					throw new RuntimeException(e);
				}
				session.setAttribute("managerImage", fileNameUUID);
				AccountImage accountImage = new AccountImage();
				accountImage.setImageFileOriginal(manager.getManagerImage());
				accountImage.setAccountId(manager.getAccountId());
				accountImage.setImageFileUUID(fileNameUUID);
				accountImage.setImageFileSize(mf.getSize());
				accountImage.setImageFileOriginal(mf.getOriginalFilename());
				accountImage.setImageFileType(mf.getContentType());
				// 운영자 이미지 조회 null 일시
				if (managerMapper.selectMyImage(accountId) != null) {
					managerMapper.updateImageFile(accountImage);
					managerMapper.updateManagerImage(accountId, fileNameUUID);
				}
				// 파일이 없을떄
				if (managerMapper.selectMyImage(accountId) == null) {
					managerMapper.insertManagerImage(accountImage);
					managerMapper.updateManagerImage(accountId, fileNameUUID);
				}
			}
		}
		return true;
	}

	// 운영자 이미지 제거
	// 매개변수: 운영자의 계정 id
	public void removeFIle(String accountId) {
		// 파일 제거
		String fileName = FilePath.getFilePath() + accountId;
		// 파일 경로 이름지정
		File file = new File(fileName);
		// 파일이 존재할시
		if (file.exists()) {
			file.delete();
		}
		managerMapper.deleteMyImage(accountId);
		managerMapper.updateManagerImageByDelete(accountId);
	}

	// 운영자 이미지
	// 매개변수: 운영자 id
	// 리턴값: 운영자 자신 이미지
	public AccountImage getManagerImageFIle(String accountId) {
		return managerMapper.selectMyImage(accountId);
	}

	// 운영자 이미지 조회
	public AccountImage selectMyImage(String accountId) {
		return managerMapper.selectMyImage(accountId);
	}

	// 운영자 이미지 출력
	public String getImageFileUUIDCk(String accountId) {
		return managerMapper.selectImageFileUUIDCk(accountId);
	}

	// 운영자의 자격증,경력,학력 상세보기
	public Map<String, Object> getManagerInforOne(String accountId) {
		Manager manager = managerMapper.selectManagerInfoOne(accountId);
		Map<String, Object> map = new HashMap<>();
		map.put("manager", manager);
		return map;
	}

	// 운영자의 개인 정보를 수정하는 서비스 메소드
	// 매개변수: 운영자 정보
	// 리턴값: 운영자의 수정된 정보
	public int modifyManager(Manager manager) {
		return managerMapper.updateManager(manager);
	}

	// 우편주소로 주소 리스트를 조회 메서드
	// 매개변수: 우편주소
	// 리턴값: 우편주소에 따른 주소목록
	public List<String> getAddressListByZipcode(String zipCode) {
		List<String> list = addressMapper.selectAddressByZipCode(zipCode);
		return list;
	}

	// 운영자의 현재 아이디 계정 이메일 정보 조회 메서드
	// 매개변수: 운영자id , 운영자 이메일
	// 리턴값: 조회되는 계정, 이메일
	public String getManagerEmail(String accountId, String managerEmail) {
		return managerMapper.selectManagerEmail(accountId, managerEmail);
	}

	// 운영자의 비밀번호 변경
	// 매개변수: 계정의 정보
	// 리턴값: 해당하는 행의 변경
	public void modifyManagerPasswd(Account account) {
		managerMapper.updateManagerPassword(account);
	}

	// 운영자의 변경전의 Id 및 password
	// 매개변수: 운영자의 id 및 password
	// 리턴값: 변경전의 id 와 password의 확인
	public String getManagerPassword(String accountId, String accountPw) {
		return managerMapper.selectManagerPassword(accountId, accountPw);
	}

	// 운영자 경력추가
	// 매개변수:경력vo
	// 리턴값:변경된 행 갯수
	public void createManagerCareer(Career career) {
		careerMapper.insertCareer(career);
	}

	// 운영자 경력삭제
	// 운영자:경력 고유번호 , 운영자 id
	// 리턴값:변경된 행 갯수
	public void removeManagerCareer(int careerNo, String accountId) {
		managerMapper.deleteCareerByManager(careerNo);
	}

	// 운영자 자격증추가
	// 매개변수:자격증vo
	// 리턴값:변경된 행 갯수
	public void createManagerLicense(License license) {
		licenseMapper.insertLicense(license);
	}

	// 운영자 자격증삭제
	// 매개변수:자격증 고유번호 , 운영자Id
	// 리턴값:변경된 행 갯수
	public void removeManagerLicense(int licenseNo, String accountId) {
		licenseMapper.deleteLicenseByLicenseNo(licenseNo);
	}

	// 운영자 학력추가
	// 매개변수:학력vo
	// 리턴값:변경된 행 갯수
	public void createManagerEducation(Education education) {
		educationMapper.insertEducation(education);
	}

	// 운영자 학력삭제
	// 매개변수:학력 순번
	// 리턴값:변경된 행 갯수
	public void removeManagerEducation(int educationNo, String accountId) {
		educationMapper.deleteEducationByEducationNo(educationNo);
	}

}