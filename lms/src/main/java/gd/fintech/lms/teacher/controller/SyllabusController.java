package gd.fintech.lms.teacher.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gd.fintech.lms.teacher.service.SyllabusService;
import gd.fintech.lms.teacher.vo.Syllabus;

// 강의계획서 관련 Controller

@Controller
public class SyllabusController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// 강의계획서 관련 Service
	@Autowired SyllabusService syllabusService;
	
	// 강의계획서 페이지를 출력하는 메소드
	// 매개변수:
	// #1. model
	// #2. syllabusNo(강의계획서)
	// 리턴값:
	// #1. syllabusDetail(고유번호에 해당하는 강의계획서 페이지)
	// #2. syllabusWriterName(강의계획서 작성자 이름)
	// 강의계획서 출력 시에 작성자 아이디를 그대로 출력하는 것이 아닌 해당 아이디의 사용자 이름을 출력
	@GetMapping(value = {"/teacher/syllabusDetail", "/manager/syllabusDetail", "/student/syllabusDetail"})
	public String syllabusDetail(Model model, @RequestParam(value = "syllabusNo") int syllabusNo) {
		Map<String, Object> map = syllabusService.getSyllabusDetail(syllabusNo);
		model.addAttribute("syllabusDetail", map.get("syllabusDetail"));
		model.addAttribute("syllabusWriterName", map.get("syllabusWriterName"));
		
		return "/teacher/syllabusDetail";
	}
	
	// 강사가 강의계획서를 작성할 수 있는 페이지를 출력하는 메소드
	// 매개변수: 없음
	// 리턴값: createSyllabus(강의계힉서 작성 페이지)
	// 작성할 강의계획서를 입력할 수 있는 페이지 출력
	@GetMapping("/teacher/createSyllabus")
	public String createSyllabus(Model model, HttpServletRequest request) {
		HttpSession session = ((HttpServletRequest)request).getSession();
		String accountId = (String)session.getAttribute("accountId");
		String syllabusWriter = syllabusService.getTeacherName(accountId);
		model.addAttribute("syllabusWriter", syllabusWriter);
		
		return "/teacher/createSyllabus";
	}
	
	// 강의계획서를 입력된 값으로 작성하는 메소드
	// 매개변수: syllabus(강의계획서)
	// 리턴값: syllabusDetail 페이지로 이동
	// 입력된 정보로 강의계획서를 작성
	@PostMapping("/teacher/createSyllabus")
	public String createSyllabus(Syllabus syllabus) {
		syllabusService.createSyllabus(syllabus);
		
		return "redirect:/teacher/teacherLecture?currentPage=1";
	}
	
	// 강사가 강의계획서를 수정할 수 있는 페이지를 출력하는 메소드
	// 매개변수:
	// #1. model
	// #2. syllabusNo(강의계획서 고유번호)
	// 리턴값: modifySyllabus(고유번호에 해당하는 강의계획서 수정 페이지)
	// 수정할 강의계획서를 입력할 수 있는 페이지 출력
	// 기존의 강의계획서를 출력
	@GetMapping("/teacher/modifySyllabus")
	public String modifySyllabus(Model model, @RequestParam(value = "syllabusNo") int syllabusNo) {
		Map<String, Object> map = syllabusService.getSyllabusDetail(syllabusNo);
		model.addAttribute("modifySyllabus", map.get("syllabusDetail"));
		
		return "/teacher/modifySyllabus";
	}
	
	// 강의계획서를 입력된 값으로 수정하는 메소드
	// 매개변수: syllabus(강의계획서)
	// 리턴값: syllabusDetail 페이지로 이동
	// 입력된 정보로 강의계획서를 수정
	// 수정된 강의계획서 페이지로 이동(고유번호에 해당하는 강의계획서로 이동)
	@PostMapping("/teacher/modifySyllabus")
	public String modifySyllabus(Syllabus syllabus) {
		syllabusService.modifySyllabus(syllabus);
		
		return "redirect:/teacher/syllabusDetail?syllabusNo=" + syllabus.getSyllabusNo();
	}
	
	// 강사가 강의계획서에 서명하는 메소드
	// 매개변수: syllabusNo(강의계획서 고유번호)
	// 리턴값: syllabusDetail 페이지로 이동
	// 강사가 강의계획서에 서명
	// 서명한 강의계획서 페이지로 이동(고유번호에 해당하는 강의계획서 페이지로 이동)
	@GetMapping("/teacher/signSyllabusByTeacher")
	public String signSyllabusByTeacher(@RequestParam(value = "syllabusNo") int syllabusNo,
			HttpServletRequest request) {
		HttpSession session = ((HttpServletRequest)request).getSession();
		String accountId = (String)session.getAttribute("accountId");
		String syllabusTeacherSign = syllabusService.getTeacherName(accountId);
		logger.debug(syllabusTeacherSign);
		syllabusService.signSyllabusByTeacher(syllabusNo, syllabusTeacherSign);
		
		return "redirect:/teacher/syllabusDetail?syllabusNo=" + syllabusNo;
	}
	
	// 운영자가 강의계획서에 서명하는 메소드
	// 매개변수: syllabusNo(강의계획서 고유번호)
	// 리턴값: syllabusDetail 페이지로 이동
	// 운영자가 강의계획서에 서명
	// 서명한 강의계획서 페이지로 이동(고유번호에 해당하는 강의계획서 페이지로 이동)
	@GetMapping("/manager/signSyllabusByManager")
	public String signSyllabusByManager(@RequestParam(value = "syllabusNo") int syllabusNo,
			HttpServletRequest request) {
		HttpSession session = ((HttpServletRequest)request).getSession();
		String accountId = (String)session.getAttribute("accountId");
		String syllabusManagerSign = syllabusService.getManagerName(accountId);
		syllabusService.signSyllabusByManager(syllabusNo, syllabusManagerSign);
		
		return "redirect:/manager/syllabusDetail?syllabusNo=" + syllabusNo;
	}
}
	