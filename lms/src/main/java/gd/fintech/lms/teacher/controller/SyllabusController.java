package gd.fintech.lms.teacher.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gd.fintech.lms.dto.SyllabusForm;
import gd.fintech.lms.manager.vo.Lecture;
import gd.fintech.lms.teacher.service.SyllabusService;
import gd.fintech.lms.teacher.vo.Syllabus;

// 강의계획서 관련 Controller

@Controller
public class SyllabusController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// 강의계획서 관련 Service
	@Autowired SyllabusService syllabusService;
	
	// 강의계획서 정보 페이지를 출력하는 메소드
	// 매개변수:
	// #1. model
	// #2. lectureNo(강의계획서 고유번호)
	// 리턴값: syllabusDetail(고유번호에 해당하는 강의계획서 페이지)
	// 강의계획서 정보를 출력
	@GetMapping(value = {"/teacher/syllabusDetail", "/manager/syllabusDetail", "/student/syllabusDetail"})
	public String syllabusDetail(Model model, HttpSession session,
			@RequestParam(value = "lectureNo") int lectureNo) {
		Map<String, Object> map = syllabusService.getSyllabusDetail(lectureNo);
		
		model.addAttribute("syllabusDetail", map.get("syllabusDetail"));
		model.addAttribute("lectureDetail", map.get("lectureDetail"));
		
		return "/teacher/syllabusDetail";
	}
	
	// 강사가 강의계획서를 작성할 수 있는 페이지를 출력하는 메소드
	// 매개변수:
	// #1. model
	// #2. session
	// #3. lectureNo(강의계획서 고유번호)
	// 리턴값: createSyllabus(강의계힉서 작성 페이지)
	// 작성할 강의계획서를 입력할 수 있는 페이지 출력
	// 강좌를 담당하는 강사만 해당 강의계획서 작성 페이지 접근 가능
	@GetMapping("/teacher/createSyllabus")
	public String createSyllabus(Model model, HttpSession session,
			@RequestParam(value = "lectureNo") int lectureNo) {
		Map<String, Object> map = syllabusService.getSyllabusDetail(lectureNo);
		
		// session에서 accountId(아이디)를 출력
		String accountId = (String)session.getAttribute("accountId");
		// syllabus(강의계획서 정보) 출력
		Syllabus syllabusDetail = (Syllabus)map.get("syllabusDetail");
		// lectureDetail(강좌 정보) 출력
		Lecture lectureDetail = (Lecture)map.get("lectureDetail");
		// 강좌 정보에서 강좌를 담당하는 강사 아이디를 teacherId로 출력
		String teacherId = lectureDetail.getAccountId();
		
		// teacherId(강좌를 담당하는 강사 아이디)와 accountId(현재 로그인 한 강사의 아이디)가 같으면 강의계획서 작성 페이지 출력
		if(teacherId.equals(accountId) && syllabusDetail == null) {
			model.addAttribute("lectureDetail", map.get("lectureDetail"));
			return "/teacher/createSyllabus";
		}
		
		return "redirect:/teacher/teacherLectureOne?lectureNo=" + lectureNo;
	}
	
	// 강의계획서를 입력된 값으로 작성하는 메소드
	// 매개변수: syllabus(강의계획서 정보)
	// 리턴값: syllabusDetail 페이지로 이동
	// 입력된 정보로 강의계획서를 작성
	// 작성된 강의계획서 페이지로 이동(고유번호에 해당하는 강의계획서로 이동)
	@PostMapping("/teacher/createSyllabus")
	public String createSyllabus(HttpSession session, int lectureNo, SyllabusForm syllabusForm) {
		logger.debug("서비스 디버그 강의계획서 정보 " + syllabusForm);
		syllabusService.createSyllabus(session, lectureNo, syllabusForm);
		
		return "redirect:/teacher/syllabusDetail?lectureNo=" + syllabusForm.getLectureNo();
	}
	
	// 강사가 강의계획서를 수정할 수 있는 페이지를 출력하는 메소드
	// 매개변수:
	// #1. model
	// #2. session
	// #3. lectureNo(강의계획서 고유번호)
	// 리턴값: modifySyllabus(고유번호에 해당하는 강의계획서 수정 페이지)
	// 수정할 강의계획서를 입력할 수 있는 페이지 출력
	// 기존의 강의계획서를 출력
	// 강좌를 담당하는 강사만 해당 강의계획서 수정 페이지 접근 가능
	@GetMapping("/teacher/modifySyllabus")
	public String modifySyllabus(Model model, HttpSession session,
			@RequestParam(value = "lectureNo") int lectureNo) {
		Map<String, Object> map = syllabusService.getSyllabusDetail(lectureNo);

		// session에서 accountId(아이디)를 출력
		String accountId = (String)session.getAttribute("accountId");
		// syllabus(강의계획서 정보) 출력
		Syllabus syllabusDetail = (Syllabus)map.get("syllabusDetail");
		// managerLectureDetail(강좌 정보) 출력
		Lecture lectureDetail = (Lecture)map.get("lectureDetail");
		// 강좌 정보에서 강좌를 담당하는 강사 아이디를 teacherId로 출력
		String teacherId = lectureDetail.getAccountId();
		
		logger.debug(lectureDetail.toString());		
		
		// teacherId(강좌를 담당하는 강사 아이디)와 accountId(현재 로그인 한 강사의 아이디)가 같으면 수정 페이지 출력
		if(teacherId.equals(accountId) && syllabusDetail != null) {
			model.addAttribute("syllabusDetail", map.get("syllabusDetail"));
			model.addAttribute("lectureDetail", map.get("lectureDetail"));
			return "/teacher/modifySyllabus";
		}
		
		return "redirect:/teacher/syllabusDetail?lectureNo=" + lectureNo;
	}
	
	// 강의계획서를 입력된 값으로 수정하는 메소드
	// 매개변수: syllabus(강의계획서 정보)
	// 리턴값: syllabusDetail 페이지로 이동
	// 입력된 정보로 강의계획서를 수정
	// 수정된 강의계획서 페이지로 이동(고유번호에 해당하는 강의계획서로 이동)
	@PostMapping("/teacher/modifySyllabus")
	public String modifySyllabus(HttpSession session, int lectureNo, SyllabusForm syllabusForm) {
		syllabusService.modifySyllabus(session, lectureNo, syllabusForm);
		
		return "redirect:/teacher/syllabusDetail?lectureNo=" + syllabusForm.getLectureNo();
	}
	
	// 강의계획서에 첨부된 파일을 다운로드하는 메소드
	// 매개변수:
	// #1. request
	// #2. response
	// #3. syllabusFileUUID(강의계획서 첨부파일 UUID)
	// 리턴값: 없음
	// 강의계획서에 첨부된 파일을 다운로드받고 다운로드 횟수를 1회 증가
	@GetMapping(value = {"/teacher/downloadSyllabusFile", "/manager/downloadSyllabusFile", "/student/downloadSyllabusFile"})
	public void downloadSyllabusFile(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("syllabusFileUUID") String syllabusFileUUID) {
		syllabusService.downloadQuestionCommentFile(request, response, syllabusFileUUID);
	}
	
	// 강사가 강의계획서에 서명하는 메소드
	// 매개변수:
	// #1. session
	// #2. lectureNo(강의계획서 고유번호)
	// 리턴값: syllabusDetail 페이지로 이동
	// 강사가 강의계획서에 서명
	// 서명한 강의계획서 페이지로 이동(고유번호에 해당하는 강의계획서 페이지로 이동)
	@GetMapping("/teacher/signSyllabusByTeacher")
	public String signSyllabusByTeacher(HttpSession session,
			@RequestParam(value = "lectureNo") int lectureNo) {
		syllabusService.signSyllabusByTeacher(session, lectureNo);
		
		return "redirect:/teacher/syllabusDetail?lectureNo=" + lectureNo;
	}
	
	// 운영자가 강의계획서에 서명하는 메소드
	// 매개변수:
	// #1. session
	// #2. lectureNo(강의계획서 고유번호)
	// 리턴값: syllabusDetail 페이지로 이동
	// 운영자가 강의계획서에 서명
	// 서명한 강의계획서 페이지로 이동(고유번호에 해당하는 강의계획서 페이지로 이동)
	@GetMapping("/manager/signSyllabusByManager")
	public String signSyllabusByManager(HttpSession session,
			@RequestParam(value = "lectureNo") int lectureNo) {
		syllabusService.signSyllabusByManager(session, lectureNo);
		
		return "redirect:/manager/syllabusDetail?lectureNo=" + lectureNo;
	}
}
