package gd.fintech.lms.student.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.account.vo.Account;
import gd.fintech.lms.account.vo.License;
import gd.fintech.lms.student.vo.AccountImage;
import gd.fintech.lms.student.vo.Student;
//학생의 정보를 보여주는 게시판
@Mapper
public interface StudentMapper {
	//학생 회원가입 정보(기본정보)
	//매개변수:학생Vo
	//리턴값:해당하는 행 수정한 값
	int insertStudentFromQueue(Map<String, Object> map);
	
	//학생 상세보기(정보보기+학력)
	//매개변수: 학생계정의id
	//리턴값: 학생의 모든 정보(id,email,name,phone,gender,birth,address,image,accessdate)
	Student selectStudentOne(String accountId);
	Student selectStudentLisence(String accountId);
	
	// 자격증을 입력하기 위한 메소드
	// 매개변수: 입력된 값(account_id, license_number, license_name, license_agency, license_getdate)
	// 리턴값: 자격증 정보를 입력한 행
	int insertLicense(License license);
		
	// 계정의 자격증 리스트 중 하나만 삭제하기 위한 메소드
	// 매개변수: 자격증 번호
	// 리턴값: 한 내용의 자격증 정보를 삭제한 행
	int deleteLicenseByLicenseNo(int licenseNo);
	
	//학생 정보 수정액션
	//매개변수: 학생Vo
	//리턴값:해당 행 수정한 값
	int updateStudent(Student student);
	
	//학생이 볼 과제
	//매개변수: 학생계정id
	//리턴값:해당행 출력
	List<Student> selectReportOne(String accountId);
	
	//학생의 id에 해당하는 학생이름
	//매개변수: 학생계정id
	//리턴값:계정id에 해당하는 학생이름
	String selectStudentName(String accountId);
	
	//학생 현재 비밀번호 확인
	//매개변수:학생id,pw
	String selectStudentPw(String accountId,String accountPw);
	
	//이메일 중복체크
	//매개변수:학생id,학생 이메일
	String selectStudentEmail(String accountId,String studentEmail);
	
	//학생 비밀번호 변경
	//매개변수:
	//리턴값:
	int updateStudentPw(Account account);
	
	//현재 학생 프로필 사진 출력
	//매개변수:
	//리턴값:학생 이미지파일 전체
	String selectStudentImageanddelete(String accountId);
	
	//학생 이미지 카운트
	//매개변수:
	//리턴값:
	String selectImageFileUUIDCk(String accountId);

	//학생 이미지 조회
	//매개변수:
	//리턴값:
	AccountImage selectMyImage(String accountId);
	
	//학생 미미지 추가
	//매개변수:이미지vo
	//리턴값:추가되는 행
	int insertStudentImage(AccountImage accountImage);
	
	//학생 이미지 삭제
	//매개변수:
	//리턴값:
	int deleteMyImage(String accountId);	
	
	//이미지 수정(이미지만)
	//매개변수
	//리턴값:
	int updateStudentImage(String accountId,String studentImage);
	
	//이미지파일 수정
	//매개변수:
	//리턴값:
	int updateImageFile(AccountImage AccountImage);
	
	//이미지 수정 버튼 추가 -> 삭제버튼 클릭
	//매개변수:
	//리턴값:
	int updateStudentImgbyDelete(String accountId);
}
