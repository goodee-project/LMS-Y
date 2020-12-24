package gd.fintech.lms.teacher.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
			@RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
			HttpSession session, Model model) {
		List<Map<String, Object>> list = reportService.getReportList(currentPage, session);
		
		model.addAttribute("list", list);
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
