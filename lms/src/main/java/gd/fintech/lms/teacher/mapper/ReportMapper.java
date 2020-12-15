package gd.fintech.lms.teacher.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.teacher.vo.Report;

// 강좌에 연결된 과제를 관리하는 테이블의 매퍼

@Mapper
public interface ReportMapper {
	// 과제 리스트 출력 (페이징 기법 사용)
	// 매개변수: Map.put()을 사용해 페이징 변수 beginRow, rowPerPage를 넣을 것
	// 리턴값: 해당 페이지의 과제 리스트
	List<Report> selectReportList(Map<String, Object> map);
	
	// 과제 생성
	// 매개변수: 과제 객체, setter를 사용해 추가할 정보 lectureNo, reportTitle, reportContent, reportStartDate, reportEndDate를 넣을 것
	// 리턴값: 변경된 행 갯수
	int insertReport(Report report);
	
	// 과제 수정
	// 매개변수: 과제 객체, setter를 사용해 변경할 행 고유번호 reportNo, 변경할 정보 reportTitle, reportContent, reportStartDate, reportEndDate를 넣을 것
	// 리턴값: 변경된 행 갯수
	int updateReport(Report report);
}
