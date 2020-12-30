package gd.fintech.lms.student.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gd.fintech.lms.dto.ReportSubmitForm;
import gd.fintech.lms.student.service.ReportSubmitService;
import gd.fintech.lms.teacher.vo.Report;

@Controller
public class ReportSubmitController {
	// debug를 하기위한 logger 
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	// 한 과제에 관한 과제 제출 서비스
	@Autowired private ReportSubmitService reportSubmitService;
	
	// 과제 리스트 출력
	// 매개변수 :
	// Model
	// RequestParam : accountId(계정 ID)
	// 리턴값 : 계정과 연관 있는 과제리스트
	@GetMapping("/student/reportList")
	public String reportList(Model model,
			@RequestParam(value="currentPage", defaultValue = "1") int currentPage,
			HttpSession session) {
		Map<String, Object> map = reportSubmitService.getReportListByPage(currentPage, session);
		logger.debug(map.toString());
		
		model.addAttribute("reportList", map.get("reportList"));
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("lastPage", map.get("lastPage"));
		
		model.addAttribute("navPerPage", map.get("navPerPage"));
		model.addAttribute("navBeginPage", map.get("navBeginPage"));
		model.addAttribute("navLastPage", map.get("navLastPage"));
		return "/student/reportList";
	}
	
	// 과제 제출 정보 출력
	// 매개변수 :
	// Model
	// RequestParam : 
	// reportNo(과제 번호)
	// accountId(계정 ID)
	// 리턴값 : 과제의 제출 상세정보 출력
	@GetMapping("/student/reportSubmitDetail")
	public String reportSubmitDetail(Model model,
			@RequestParam(value="reportNo") int reportNo,
			HttpSession session) {
		Report report = reportSubmitService.getReportSubmitDetail(reportNo, session);
		model.addAttribute("report", report);
		return "/student/reportSubmitDetail";
	}
	
	// 과제 제출 입력 페이지
	// 리턴값 : 과제 제출 입력페이지
	@GetMapping("/student/createReportSubmit")
	public String createReportSubmit() {
		return "createReportSubmit";
	}
	
	// 과제 제출 입력 액션
	// 매개변수 : 과제 제출 폼
	// 리턴값 : 과제 제출 상세보기 페이지 
	@PostMapping("/student/createReportSubmit")
	public String createReportSubmit(ReportSubmitForm reportSubmitForm) {
		logger.debug(reportSubmitForm.toString());
		reportSubmitService.createReportSubmit(reportSubmitForm);
		return "redirect:/student/reportSubmitDetail?reportNo="
		+reportSubmitForm.getReportNo()
		+"&accountId="+reportSubmitForm.getAccountId();
	}
	
	// 과제 제출 수정 페이지
	// 매개변수 :
	// Model
	// RequestParam : 
	// reportNo(과제 번호)
	// accountId(계정 ID)
	// 리턴값 : 과제 제출 수정페이지
	@GetMapping("/student/modifyReportSubmit")
	public String modifyReportSubmit(Model model,
			@RequestParam(value="reportNo") int reportNo,
			@RequestParam(value="accountId") String accountId) {
		return "modifyReportSubmit";
	}
	
	// 과제 제출 수정 액션
	// 매개변수 : 과제 제출 폼 
	// 리턴값 : 과제 제출 상세보기 페이지 
	@PostMapping("/student/modifyReportSubmit")
	public String modifyReportSubmit(ReportSubmitForm reportSubmitForm) {
		logger.debug(reportSubmitForm.toString());
		reportSubmitService.modifyReportSubmit(reportSubmitForm);
		return "redirect:/student/reportSubmitDetail?reportNo="
		+reportSubmitForm.getReportNo()
		+"&accountId="+reportSubmitForm.getAccountId();
	}
	
	// 과제 제출 파일 삭제
	// 매개변수 :
	// RequestParam : 
	// reportSubmitFileUUID(과제 제출파일 UUID)
	// reportNo(과제 번호)
	// accountId(계정 ID)
	// 리턴값 : 과제 수정 페이지 
	@GetMapping("/student/removeReportSubmitFile")
	public String removeReportSubmitFile(@RequestParam(value="reportSubmitFileUUID") String reportSubmitFileUUID,
			@RequestParam(value="reportNo") int reportNo,
			@RequestParam(value="accountId") String accountId) {
		reportSubmitService.removeReportSubmitFile(reportSubmitFileUUID);
		return "redirect:/student/modifyReportSubmit?reportNo="
		+reportNo
		+"&accountId="+accountId;
	}
}
