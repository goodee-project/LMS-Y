package gd.fintech.lms.student.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

// 학생 통계 자료를 산출하기 위한 매퍼 인터페이스

@Mapper
public interface StudentChartMapper {
	// 출결 현황에 대한 통계를 산출하는 메소드
	// 매개변수: 학생의 아이디, 강의 번호
	// 리턴값: 학생의 강좌 출결별(출석,결석,외출,조퇴,지각) 데이터
	Map<String, Object> selectAttendanceDataByAccountId(Map<String, Object> map);
	
	// 카테고리를 위한 강좌 리스트 조회 메소드
	// 매개변수: 학생 아이디
	// 리턴값: 학생이 수강중인 또는 수료한 강좌 리스트(강좌명, 강좌번호)
	List<Map<String, Object>> selectLectureCategoryByAccountId(String accountId);
	
	// 인덱스에서 보여지는 기본 강좌번호 조회 메소드
	// 매개변수: 학생 계정
	// 리턴값: 인덱스에 보여지는 기본 강좌번호
	Integer selectDefaultLectureNoByAccountId(String accountId);

	// 학생 과제 성적 통계를 산출하는 메소드
	// 매개변수: 학생의 아이디, 강좌 번호
	// 리턴값: 학생의 과제별 점수 리스트
	List<Map<String, Object>> selectReportScoreByAccountId(Map<String, Object> map);
	
	// 과제 개수 구하는 메소드
	// 매개변수: 학생 아이디, 강의번호
	// 리턴값: 과제의 개수
	int selectCountReportByAccountId(Map<String, Object> map);
	
	// 부여받은 과제 점수 합계 구하는 메소드
	// 매개변수: 학생 아이디, 강의 번호
	// 리턴값: 학생이 부여받은 과제 점수 합계
	int selectSumReportByAccountId(Map<String, Object> map);
}
