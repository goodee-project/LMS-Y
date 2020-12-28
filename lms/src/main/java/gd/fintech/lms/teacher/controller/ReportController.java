package gd.fintech.lms.teacher.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gd.fintech.lms.teacher.service.ReportService;
import gd.fintech.lms.teacher.vo.Report;

// 강사가 관리 가능한 강좌의 과제를 관리하는 기능을 담당하는 컨트롤러

@Controller
public class ReportController {
	// 강좌의 과제를 관리하기 위한 서비스
	@Autowired private ReportService reportService;
	
	// 강사가 관리 가능한 과제 목록 출력
	// 매개변수:
	// RequestParam: currentPage(현재 페이지)
	// HttpSession, Model
	// 리턴값: teacher/reportList.jsp 뷰 포워딩
	@GetMapping("/teacher/reportList")
	public String reportList(
			@RequestParam("lectureNo") int lectureNo,
			@RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
			HttpSession session, Model model) {
		Map<String, Object> map = reportService.getReportList(lectureNo, currentPage, session);
		
		// 과제 정보를 담은 리스트
		model.addAttribute("list", map.get("infoList"));
		
		// 페이지 관련 값
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("pageNaviBegin", map.get("pageNaviBegin"));
		model.addAttribute("pageNaviEnd", map.get("pageNaviEnd"));
		model.addAttribute("lastPage", map.get("lastPage"));
		
		return "teacher/reportList";
	}
	
	// 과제 번호를 이용해 과제 상세보기 출력
	// 매개변수:
	// RequestParam: reportNo(상세보기 출력될 과제번호)
	// Model
	// 리턴값: teacher/reportDetail.jsp 뷰 포워딩
	@GetMapping("/teacher/reportDetail")
	public String reportDetail(
			@RequestParam("reportNo") int reportNo,
			Model model) {
		Report report = reportService.getReportDetail(reportNo);
		
		model.addAttribute("report", report);
		return "teacher/reportDetail";
	}
	
	// 과제 생성 폼 호출
	// 매개변수:
	// RequestParam: 
	// 리턴값: teacher/createReport.jsp 뷰 포워딩
	@GetMapping("/teacher/createReport")
	public String createReport(
			@RequestParam("lectureNo") int lectureNo) {
		return "teacher/createReport";
	}

	// 과제 생성
	// 매개변수: Report(커맨드 객체), HttpSession(작성자 인증용 세션 객체)
	// 리턴값: /teacher/reportList 리다이렉트
	@PostMapping("/teacher/createReport")
	public String createReport(Report report, HttpSession session) {
		reportService.createReport(report, session);
		
		return "redirect:/teacher/reportList?lectureNo="+report.getLectureNo();
	}

	// 과제 수정 폼 호출
	// 매개변수:
	// RequestParam: reportNo(수정할 과제의 고유번호), Model
	// 리턴값: teacher/modifyReport.jsp 뷰 포워딩
	@GetMapping("/teacher/modifyReport")
	public String modifyReport(
			@RequestParam("reportNo") int reportNo,
			Model model) {
		Report report = reportService.getReportDetail(reportNo);
		
		model.addAttribute("report", report);
		return "teacher/modifyReport";
	}

	// 과제 수정
	// 매개변수: Report(커맨드 객체), HttpSession(작성자 인증용 세션 객체)
	// 리턴값: /teacher/reportDetail 리다이렉트
	@PostMapping("/teacher/modifyReport")
	public String modifyReport(Report report, HttpSession session) {
		reportService.modifyReport(report, session);
		
		return "redirect:/teacher/reportDetail?reportNo="+report.getReportNo();
	}
	
	// 제출된 과제의 첨부파일 다운로드
	// 매개변수:
	// RequestParam: reportSubmitFileUUID(파일 UUID)
	// HttpServletRequest, HttpServletResponse
	// 리턴값: 파일 다운로드
	@GetMapping("/teacher/downloadReportSubmitFile")
	public void downloadReportSubmitFile(
			@RequestParam("reportSubmitFileUUID") String reportSubmitFileUUID,
			HttpServletRequest request, HttpServletResponse response) {
		reportService.downloadReportSubmitFile(reportSubmitFileUUID, request, response);
	}
}
