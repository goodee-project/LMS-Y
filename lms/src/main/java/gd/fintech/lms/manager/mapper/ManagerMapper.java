package gd.fintech.lms.manager.mapper;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.account.vo.Account;
import gd.fintech.lms.manager.vo.Manager;
import gd.fintech.lms.manager.vo.AccountImage;

// 운영자 정보에 대한 메퍼 인터페이스

@Mapper
public interface ManagerMapper {
	
	// 운영자 개인의 정보 (정보,학력)
	// 매개변수: 운영자 id
	// 리턴값: 운영자 id에 해당하는 운영자의 정보
	Manager selectManagerOne(String accountId);
	Manager selectManagerInfoOne(String accountId);
	
	// 운영자 정보 수정
	// 매개변수: 운영자 정보
	// 리턴값: 행의 수정
	int updateManager(Manager manager);
	
	// 회원가입 승인대기에 있는 운영자 개인정보를 가져와 운영자 개인정보 입력
	// 매개변수: 운영자 아이디
	// 리턴값: 행의 입력
	int insertManagerFromQueue(String accountId);
	
	// 계정 아이디에 해당하는 운영자의 이름
	// 매개변수:운영자 Id
	// 리턴값: 계정 아이디에 해당하는 운영자의 이름
	String selectManagerName(String accountId);
	
	// 운영자의 계정 이메일 중복 체크
	// 매개변수: 운영자id , 운영자 이메일
	String selectManagerEmail(String accountId, String managerEmail);
	
	// 운영자의 비밀번호를 수정
	// 매개변수: 계정 정보
	// 리턴값: 행의 수정
	int updateManagerPassword(Account account);
	
	// 운영자의 비밀번호를 수정하기전 확인
	// 매개변수: 운영자 id와 운영자의 password
	// 리턴값: 계정아이디에 해당하는 운영자의 id,password
	String selectManagerPassword (String accountId, String accountPw );
	
	// 운영자의 경력을 삭제
	// 매개변수: 경력의 고유번호
	// 리턴값:  행의 삭제
	int deleteCareerByManager(int careerNo);
	
	
	// 현재 운영자 프로필 출력
	// 매개변수: 운영자id
	// 리턴값: 운영자 이미지
	String selectManagerImageanddelete(String accountId);
	
	// 삭제시 사용할 이미지 수정
	int updateManagerImageByDelete(String accountId);
	
	// 운영자 이미지 카운트
	String selectImageFileUUIDCk(String accountid);
	
	// 운영자 이미지 조회
	// 매개변수: 운영자id
	// 리턴값: 운영자 id 조회
	AccountImage selectMyImage(String accountId);
	
	// 운영자 이미지 추가
	// 매개변수: 이미지Vo
	// 리턴값: 행의 추가
	int insertManagerImage(AccountImage accountImage);
	
	// 운영자 이미지 삭제
	// 매개변수: 운영자Id
	// 리턴값: 선택한 행의 삭제
	int deleteMyImage(String accountId);
	
	/// 운영자 이미지 수정
	// 매개변수: 운영자Id
	// 리턴값: 행의 수정
	int updateManagerImage(String accountId,String managerImage);
	int updateImageFile(AccountImage AccountImage);
}
