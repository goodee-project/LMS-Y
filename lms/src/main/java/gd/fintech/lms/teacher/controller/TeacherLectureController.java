package gd.fintech.lms.teacher.controller;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gd.fintech.lms.manager.vo.Lecture;
import gd.fintech.lms.teacher.service.TeacherLectureService;

//강사의강좌 정보 목록 및 상세보기 컨트롤러

@Controller
public class TeacherLectureController {
	// Logger
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	// TeacherLectureService 객체 주입
	@Autowired
	private TeacherLectureService teacherLectureService;

	// 강사 강좌 목록 페이지 메서드
	// 매개변수:강사 아이디
	// 리턴값:강사 아이디 참조하여 정보를 띄우는 뷰페이지
	@GetMapping("/teacher/teacherLecture")
	public String teacherLecture(Model model,
			HttpServletRequest request,
			@RequestParam(value = "currentPage") int currentPage) {
		
		//세션정보 가져옴
		HttpSession session = ((HttpServletRequest)request).getSession();
		//세션에 있는 아이디 가져옴
		String accountId = (String)session.getAttribute("accountId");

		List<Lecture> teacherLectureList = teacherLectureService.getTeacherLectureListByPage(accountId, currentPage);	
		
		// model을 이용해 뷰에 정보 보냄.
		model.addAttribute("accountId",accountId);
		model.addAttribute("teacherLectureList", teacherLectureList);
		model.addAttribute("currentPage", currentPage);
		return "/teacher/teacherLecture";
	}

	// 강사 강좌 상세 페이지 메서드
	// 매개변수:강좌 고유번호
	// 리턴값:강좌 고유번호 참조하여 정보를 띄우는 뷰페이지
	@GetMapping("/teacher/teacherLectureOne")
	public String teacherLectureOne(Model model,HttpServletRequest request, 
			@RequestParam(value = "lectureNo",required = false) int lectureNo) {
		//세션정보 가져옴
		HttpSession session = ((HttpServletRequest)request).getSession();
		//세션에 있는 아이디 가져옴
		String accountId = (String)session.getAttribute("accountId");
		// 강좌 정보 고유번호를 통해 DB정보를 가져옴
		Lecture lecture = teacherLectureService.getTeacherLectureOne(lectureNo);
		// Logger로 디버깅
		logger.debug("lecture -->" + lecture);
		// model을 이용해 뷰에 정보를 보냄
		model.addAttribute("lecture", lecture);
		model.addAttribute("accountId",accountId);
		return "/teacher/teacherLectureOne";
	}

}
