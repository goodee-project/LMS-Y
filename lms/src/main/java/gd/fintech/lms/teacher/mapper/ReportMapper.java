package gd.fintech.lms.teacher.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.student.vo.ReportSubmit;
import gd.fintech.lms.teacher.vo.Report;

// 강좌에 연결된 과제를 관리하는 테이블의 매퍼

@Mapper
public interface ReportMapper {
	// 특정 강좌의 과제 리스트 출력 (페이징 기법 사용)
	// 매개변수: Map.put()을 사용해 강좌 고유번호 lectureNo, 페이징 변수 beginRow, rowPerPage를 넣을 것
	// 리턴값: 해당 페이지의 과제 리스트
	List<Report> selectReportList(Map<String, Object> map);
	
	// 단일 과제 정보 및 학생이 제출한 과제 리스트 출력
	// 매개변수: 가져올 과제 고유번호
	// 리턴값: 단일 과제 정보 및 학생이 제출한 과제 리스트
	Report selectReportOne(int reportNo);
	
	// 특정 강좌의 과제 갯수 출력
	// 매개변수: 과제가 등록된 강좌의 고유번호
	// 리턴값: 해당 강좌의 과제 갯수
	int selectReportCount(int lectureNo);
	
	// 과제 생성
	// 매개변수: 과제 객체, setter를 사용해 추가할 정보 lectureNo, reportTitle, reportContent, reportStartDate, reportEndDate를 넣을 것
	// 리턴값: 변경된 행 갯수
	int insertReport(Report report);
	
	// 과제 수정
	// 매개변수: 과제 객체, setter를 사용해 변경할 행 고유번호 reportNo, 변경할 정보 reportTitle, reportContent, reportStartDate, reportEndDate를 넣을 것
	// 리턴값: 변경된 행 갯수
	int updateReport(Report report);
	
	// 학생이 제출한 과제 평가
	// 매개변수, 과제제출 객체, setter를 사용해 변경할 행 고유번호 reportSubmitNo, 변경할 정보 reportSubmitPoint, reportSubmitFeedback을 넣을 것
	// 리턴값: 변경된 행 갯수
	int updateReportSubmitResult(ReportSubmit reportSubmit);
}
