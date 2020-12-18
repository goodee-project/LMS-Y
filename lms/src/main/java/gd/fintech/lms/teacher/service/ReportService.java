package gd.fintech.lms.teacher.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gd.fintech.lms.student.vo.ReportSubmit;
import gd.fintech.lms.teacher.mapper.ReportMapper;
import gd.fintech.lms.teacher.vo.Report;

// 과제 출제 및 과제 평가를 관리하는 서비스

@Service
@Transactional
public abstract class ReportService {
	// 과제 출제 및 과제 평가 관리를 위한 매퍼
	@Autowired private ReportMapper reportMapper;
	
	// 해당 강좌에 등록된 과제 리스트를 출력 (currentPage 페이지의 내용 10개만 보여줌)
	// 매개변수:
	// #1: 강사 ID
	// #2: 표시할 페이지 번호
	// 리턴값: 등록된 과제 리스트
	public abstract List<Report> getReportList(String accountId, int currentPage);
	
	// 해당 과제에 대한 상세 정보 출력 (제출된 과제 포함)
	// 매개변수: 과제 고유번호
	// 리턴값: 제출된 과제를 포함한 과제 상세정보
	public abstract Report getReportDetail(int reportNo);
	
	// 과제 생성
	// 매개변수: 과제 객체, setter를 사용해 추가할 정보 lectureNo, reportTitle, reportContent, reportStartDate, reportEndDate를 넣을 것
	public abstract void createReport(Report report);
	
	// 과제 수정
	// 매개변수: 과제 객체, setter를 사용해 변경할 행 고유번호 reportNo, 변경할 정보 reportTitle, reportContent, reportStartDate, reportEndDate를 넣을 것
	public abstract void modifyReport(Report report);
	
	// 학생이 제출한 과제 평가
	// 매개변수, 과제제출 객체, setter를 사용해 변경할 행 고유번호 reportSubmitNo, 변경할 정보 reportSubmitScore, reportSubmitFeedback을 넣을 것
	public abstract void evaluateReportSubmit(ReportSubmit reportSubmit);
}
