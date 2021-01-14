package gd.fintech.lms.student.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.account.vo.Account;
import gd.fintech.lms.student.vo.AccountImage;
import gd.fintech.lms.student.vo.Student;
//학생의 정보를 보여주는 게시판
@Mapper
public interface StudentMapper {
	//학생 회원가입 정보(기본정보)
	//매개변수:학생Vo
	//리턴값:해당하는 행 수정한 값
	int insertStudentFromQueue(Map<String, Object> map);
	
	//학생 상세보기(정보보기)
	//매개변수: 학생계정의id
	//리턴값: 학생의 모든 정보(id,email,name,phone,gender,birth,address,image,accessdate)
	Student selectStudentOne(String accountId);
	
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
	
	//학생 이미지 조회
	//매개변수:학생id
	//리턴값:학생계정id로 조회
	AccountImage selectStudentImage(String accountId);
	
	//학생 프로필 이미지 추가
	//매개변수:이미지Vo
	//리턴값:해당 행 추가
	int insertStudentImage(AccountImage accountImage);
	
	//학생 프로필 이미지 삭제
	//매개변수:학생id
	//리턴값:제거된 하나의 행
	int removeStudentImage(String accountId);
	
	//학생 이미지만 수정
	//매개변수:학생id
	//리턴값:수정된 하나의 행
	int updateStudentImage(String accountId,String studentImage);
	int updateImageFile(AccountImage accountImage);
	
	//학생 이미지 출력 수정시 이전이미지 삭제
	//매개변수:학생id
	//리턴값:이미지VO
	String updateStudentImagePrevious(String accountId);
	
	//학생 현재 비밀번호 확인
	//매개변수:학생id,pw
	String selectStudentPw(String accountId,String accountPw);
	
	//학생 비밀번호 변경
	//매개변수:
	int updateStudentPw(Account account);
}
