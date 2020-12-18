package gd.fintech.lms.account.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.account.vo.Career;

// 경력 정보에 관한 매퍼 인터페이스

@Mapper
public interface CareerMapper {
	// 모든 계정의 경력 목록을 조회하는 메소드
	// 매개변수: 페이징을 위한 시작페이지, 한페이지에서 보여주는 행의 수
	// 리턴값: 모든 계정에 경력 정보 조회 결과값
	List<Career> selectCareerAll(Map<String, Integer> map);
	
	// 계정 ID별 경력 목록을 조회하는 메소드
	// 매개변수: 계정의 ID 값(운영자, 강사)
	// 리턴값: ID에 따른 경력 정보 조회 결과값
	List<Career> selectCareerByAccountId(String accountId);
	
	// 경력 내용을 입력하기 위한 메소드
	// 매개변수: 계정Id, 경력내용, 경력시작일, 경력마지막일
	// 리턴값: 경력을 입력한 행의 수
	int insertCareer(Career career);
	
	// 경력 내용을 수정하기 위한 메소드
	// 매개변수: 수정폼에서 수정한 데이터(경력내용, 시작일자, 마친일자)
	// 리턴값: 경력이 수정된 행
	int updateCareerByCareerNo(Career career);
	
	// 해당 계정 ID의 모든 경력을 삭제하는 메소드
	// 매개변수: 계정의 ID 값
	// 리턴값: ID에 따른 경력을 삭제한 행
	int deleteCareerByAccountId(String accountId);
	
	// 경력 내용 하나만 삭제하는 메소드
	// 매개변수: 해당 경력에 해당하는 경력번호
	// 리턴값: 한 행의 경력을 지운 행
	int deleteCareerByCareerNo(int careerNo);
}
