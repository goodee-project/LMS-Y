package gd.fintech.lms.teacher.mapper;

import java.awt.Image;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.account.vo.Account;
import gd.fintech.lms.teacher.vo.AccountImage;
import gd.fintech.lms.teacher.vo.Teacher;

//강사 Mapper

@Mapper
public interface TeacherMapper {
	
	//강사 상세보기
	//매개변수:회원가입 당시 입력한 데이터
	//리턴값:강사ID 여부를 조회하여 반환
	Teacher selectTeacherOne(String accountId);
	
	//현재 강사 프로필사진 출력
	//매개변수:강사ID
	//리턴값:강사 이미지파일관련 전체
	String selectTeacherImageanddelete(String accountId);
	
	//강사 이미지 조회
	//매개변수:강사ID
	//리턴값:강사ID 여부를 조회하여 반환
	AccountImage selectMyImage(String accountId);
	
	//강사 승인대기 정보 입력
	//매개변수:강사승인대기의 모든정보
	//리턴값:변경된 행 갯수
	int insertTeacherFromQueue(Map<String, Object> map);
	
	//강사 이미지 추가
	//매개변수:이미지vo
	//리턴값:변경된 행 갯수
	int insertTeacherImage(AccountImage accountImage);
	
	//강사 이미지 삭제
	//매개변수:강사ID
	//리턴값:변경된 행 갯수
	int deleteMyImage(String accountId);
	
	
	//이미지 수정(이미지만 따로수정)
	//매개변수:강사ID
	//리턴값:변경된 행 갯수
	int updateTeacherImage(String accountId,String teacherImage);
	int updateImageFile(AccountImage AccountImage);
	//강사 전체 정보 수정
	//매개변수:강사의 자신의 전체 정보를 가져옴
	//리턴값:변경된 행 갯수
	int updateTeacherInfo(Teacher teacher);
	
	//강사 비밀번호 변경
	//매개변수:계정vo
	//리턴값:변경된 행 갯수
	int updateTeacherPw(Account account);
	
	//강사 현재 비밀번호 확인
	//매개변수:강사ID,강사 비밀번호
	String selectTeacherPw(String accountId,String accountPw);
	
	// 계정 이메일 중복체크를 위한 메소드
	//매개변수:강사ID,강사 이메일
	String selectTeacherEmail(String accountId,String teacherEmail);
	
	
}
