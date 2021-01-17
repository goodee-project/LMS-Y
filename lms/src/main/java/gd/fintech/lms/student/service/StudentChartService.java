package gd.fintech.lms.student.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gd.fintech.lms.student.mapper.StudentChartMapper;

// 학생의 통계 차트를 위한 서비스 클래스

@Service
@Transactional
public class StudentChartService {
	// StudentChartMapper 객체 주입
	@Autowired private StudentChartMapper studentChartMapper;
	
	// 학생의 출결 통계를 위한 출결 조회 메소드
	// 매개변수: 학생 아이디, 강좌번호
	// 리턴값: 학생에 대한 출결 현황 데이터
	public Map<String, Object> getAttendanceDataByAccountId(Map<String, Object> map) {
		return studentChartMapper.selectAttendanceDataByAccountId(map);
	}
	
	// 카테고리를 위한 강좌 리스트 조회 메소드
	// 매개변수: 학생 아이디
	// 리턴값: 학생이 수강중인 또는 수료한 강좌 리스트
	public List<Map<String, Object>> getLectureCategoryByAccountId(String accountId) {
		return studentChartMapper.selectLectureCategoryByAccountId(accountId);
	}
	
	// 인덱스에서 출력될 기본 강좌 번호를 조회하는 메소드
	// 매개변수: 학생 아이디
	// 리턴값: 기본 강좌 번호
	public Integer getDefaultLectureNo(String accountId) {
		return studentChartMapper.selectDefaultLectureNoByAccountId(accountId);
	}
	
	// 학생 과제 성적 통계를 산출하는 메소드
	// 매개변수: 학생 아이디, 강좌 번호
	// 리턴값: 과제명과 과제 점수 리턴
	public List<Map<String, Object>> getReportScoreByAccountId(Map<String, Object> map) {
		return studentChartMapper.selectReportScoreByAccountId(map);
	}
	
	// 과제의 개수 구하는 메소드(총점을 구하기 위함)
	// 매개변수: 학생 아이디, 강의 번호
	// 리턴값: 학생의 과제 개수에 따른 총점
	public int getReportTotalScore(Map<String, Object> map) {
		int count = studentChartMapper.selectCountReportByAccountId(map);	// 과제 개수
		int totalScore = 0;	// 총점 변수
		for(int i=0; i<count; i++) {
			totalScore = count*100;
		}
		return totalScore;
	}
	
	// 학생이 부여받은 과제 합계 메소드
	// 매개변수: 학생 아이디, 강의 번호
	// 리턴값: 학생이 부여받은 과제 합계
	public int getReportSumScore(Map<String, Object> map) {
		return studentChartMapper.selectSumReportByAccountId(map);
	}
}
