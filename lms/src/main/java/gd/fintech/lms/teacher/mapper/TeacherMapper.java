package gd.fintech.lms.teacher.mapper;

import java.awt.Image;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.teacher.vo.AccountImage;
import gd.fintech.lms.teacher.vo.Teacher;

//강사 Mapper

@Mapper
public interface TeacherMapper {
	
	//강사 상세보기
	//매개변수:회원가입 당시 입력한 데이터
	//리턴값:강사ID 여부를 조회하여 반환
	Teacher selectTeacherOne(String accountId);
	
	//강사 정보 수정
	//매개변수:강사의 자신의 전체 정보를 가져옴
	//리턴값:변경된 행 갯수
	int updateTeacherInfo(Teacher teacher);
	
	//강사 승인대기 정보 입력
	//매개변수:강사승인대기의 모든정보
	//리턴값:변경된 행 갯수
	int insertTeacherFromQueue(Map<String, Object> map);
	
	//강사 이미지 추가
	//매개변수:이미지vo
	//리턴값:변경된 행 갯수
	int insertTeacherImage(AccountImage accountImage);
	
	//강사 이미지 조회
	//매개변수:강사ID
	//리턴값:강사ID 여부를 조회하여 반환
	AccountImage selectMyImage(String accountId);
	
	//강사 이미지 삭제
	//매개변수:강사ID
	//리턴값:변경된 행 갯수
	int deleteMyImage(String accountId);
	
	
	//테스트
	//이미지 수정테스트
	int updateTeacherImage(String accountId,String teacherImage);
	//프로젝트에서 이미지 삭제
	String selectTeacherImageanddelete(String accountId);
	
	
}
