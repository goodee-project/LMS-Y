package gd.fintech.lms.student.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gd.fintech.lms.student.service.AnswerSheetService;
import gd.fintech.lms.student.vo.MultipleChoice;

// 학생의 시험과 관련된 컨트롤러

@Controller
public class AnswerSheetController {
	// 시험 관리 서비스
	@Autowired AnswerSheetService answerSheetService;
	
	// 시험을 치르는 폼으로 이동 (시험을 치뤘다면 결과 확인)
	// 매개변수:
	// lectureNo: 시험을 치를 강의 고유번호
	// Model, HttpSession(시험을 치르는 사용자 구별용 객체)
	// 리턴값: student/createAnswerSheet.jsp 뷰 포워딩
	@SuppressWarnings("rawtypes")
	@GetMapping("student/answerSheet")
	public String answerSheet(
			@RequestParam("lectureNo") int lectureNo,
			Model model, HttpSession session) {
		// 성적이 도출되는 상태라면 (시험지를 제출했다면) 결과창으로 이동
		Map<String, Object> map = answerSheetService.getAnswerSheetList(lectureNo, session);
		if (((List)map.get("mapList")).size() > 0) {
			model.addAttribute("mapList", map.get("mapList"));
			model.addAttribute("userTotal", map.get("userTotal"));
			model.addAttribute("multipleChoiceTotal", map.get("multipleChoiceTotal"));
			return "student/scoreDetail";
		}
		
		// 위의 조건이 만족하지 않으면(시험지를 미제출시) 시험 창으로 이동
		List<MultipleChoice> list = answerSheetService.getMultipleChoiceList(lectureNo);
		model.addAttribute("list", list);
		return "student/createAnswerSheet";
	}

	// 시험지 제출
	// 매개변수:
	// lectureNo: 시험을 치를 강의 고유번호
	// Model
	// 리턴값: student/createAnswerSheet.jsp 뷰 포워딩
	@PostMapping("student/answerSheet")
	public String answerSheet(
			@RequestParam("lectureNo") int lectureNo,
			HttpServletRequest request, HttpSession session) {
		answerSheetService.createAnswerSheet(lectureNo, request.getParameterMap(), session);
		
		// 시험 결과로 바로 이동
		return "redirect:/student/answerSheet?lectureNo="+lectureNo;
	}
}
