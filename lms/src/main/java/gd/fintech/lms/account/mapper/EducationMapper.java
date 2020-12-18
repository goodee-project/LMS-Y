package gd.fintech.lms.account.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.account.vo.Education;

// 학력 정보에 관한 매퍼 인터페이스

@Mapper
public interface EducationMapper {
	// 학력 리스트를 모두 조회하기 위한 메소드
	// 매개변수: 페이징을 위한 Map(시작페이지, 페이지별 행수)
	// 리턴값: 모든 계정의 학력을 조회한 리스트
	List<Education> selectEducationAll(Map<String, Integer> map);
	
	// 계정ID 값으로 학력 내용을 조회하는 메소드
	// 매개변수: 계정 ID(운영자 또는 강사)
	// 리턴값: 계정 ID별 조회된 학력 리스트
	List<Education> selectEducationByAccountId(String accountId);
	
	// 학력내용을 입력하기 위한 메소드
	// 매개변수: 입력된 값(account_Id, education_school, education_major, education_startdate, education_enddate)
	// 리턴값: 학력 정보를 입력한 행
	int insertEducation(Education education);
	
	// 학력내용을 수정하기 위한 메소드
	// 매개변수: 수정된 학력 정보(학력번호 조건)
	// 리턴값: 학력 정보를 수정한 행
	int updateEducationByEducationNo(Education education);
	
	// 해당 계정의 모든 학력내용을 삭제하기 위한 메소드
	// 매개변수: 계정 정보 ID
	// 리턴값: 계정의 학력 정보를 삭제한 행
	int deleteEducationByAccountId(String accountId);
	
	// 계정의 학력 리스트 중 하나만 삭제하기 위한 메소드
	// 매개변수: 학력 번호
	// 리턴값: 한 내용의 학력정보를 삭제한 행
	int deleteEducationByEducationNo(int educationNo);
}
