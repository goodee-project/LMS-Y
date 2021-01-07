package gd.fintech.lms.student.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gd.fintech.lms.student.service.ReportSubmitService;

@RestController
public class ReportSubmitRestController {
	@Autowired private ReportSubmitService reportSubmitService;
	
	// 과제 제출 파일 삭제
	// 매개변수 :
	// RequestParam : 
	// reportSubmitFileUUID(과제 제출파일 UUID)
	// 리턴값 : 과제 수정 페이지 
	@PostMapping("/student/removeReportSubmitFile")
	public String removeReportSubmitFile(@RequestParam(value="reportSubmitFileUUID") String reportSubmitFileUUID) {
		reportSubmitService.removeReportSubmitFile(reportSubmitFileUUID);
		return "redirect:/student/modifyReportSubmit";
	}
}
