package gd.fintech.lms.account.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.account.vo.License;

// 자격증 정보에 관한 매퍼 인터페이스

@Mapper
public interface LicenseMapper {
	// 자격증 리스트를 모두 조회하기 위한 메소드
	// 매개변수: 페이징을 위한 Map(시작페이지, 페이지별 행수)
	// 리턴값: 모든 계정의 자격증 목록을 조회한 리스트
	List<License> selectLicenseAll(Map<String, Integer> map);
	
	// 계정ID 값으로 자격증 내용을 조회하는 메소드
	// 매개변수: 계정 ID(운영자,강사,학생)
	// 리턴값: 계정 ID별 조회된 자격증 리스트
	List<License> selectLicenseByAccountId(String accountId);
	
	// 자격증을 입력하기 위한 메소드
	// 매개변수: 입력된 값(account_id, license_number, license_name, license_agency, license_getdate)
	// 리턴값: 자격증 정보를 입력한 행
	int insertLicense(License license);
	
	// 자격증 내용을 수정하기 위한 메소드
	// 매개변수: 수정된 자격증 정보
	// 리턴값: 자격증 정보를 수정한 행
	int updateLicenseByLicenseNo(License license);
	
	// 해당 계정의 모든 자격증 내용을 삭제하기 위한 메소드
	// 매개변수: 계정 정보 ID
	// 리턴값: 계정의 자격증 정보를 삭제한 행
	int deleteLicenseByAccountId(String accountId);
	
	// 계정의 자격증 리스트 중 하나만 삭제하기 위한 메소드
	// 매개변수: 자격증 번호
	// 리턴값: 한 내용의 자격증 정보를 삭제한 행
	int deleteLicenseByLicenseNo(int licenseNo);
}
