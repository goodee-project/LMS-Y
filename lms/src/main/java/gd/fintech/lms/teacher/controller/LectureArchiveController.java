package gd.fintech.lms.teacher.controller;

import java.util.List;
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

import gd.fintech.lms.dto.LectureArchiveForm;
import gd.fintech.lms.manager.mapper.LectureManagerMapper;
import gd.fintech.lms.manager.service.LectureManagerService;
import gd.fintech.lms.manager.vo.Lecture;
import gd.fintech.lms.teacher.service.LectureArchiveService;
import gd.fintech.lms.teacher.service.LectureNoticeService;
import gd.fintech.lms.teacher.service.TeacherLectureService;
import gd.fintech.lms.teacher.service.TeacherService;
import gd.fintech.lms.teacher.vo.LectureArchive;
import gd.fintech.lms.teacher.vo.LectureNotice;
import gd.fintech.lms.teacher.vo.Teacher;

//강좌별 자료실 컨트롤러

@Controller
public class LectureArchiveController {
	//Logger
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//LectureArchiveService,TeacherService, LectureNoticeService 객체 주입
	@Autowired private LectureArchiveService lectureArchiveService;
	@Autowired private TeacherService teacherService;
	@Autowired private LectureNoticeService lectureNoticeService;
	
	//강좌별 자료실 목록 및 페이지 메서드
	//매개변수:강좌별 번호
	//리턴값:강좌별 번호를 참조하여 자료실 목록 정보를 띄우는 뷰페이지
	@SuppressWarnings("unchecked")
	@GetMapping("/teacher/lectureArchive")
	public String lectureArchive(Model model,
			@RequestParam(value="lectureNo")int lectureNo,
			@RequestParam(value="currentPage")int currentPage) {
		
		Map<String,Object> map = lectureArchiveService.getLectureArchiveListByPage(lectureNo, currentPage);
		
		List<LectureArchive> lectureArchiveList = (List<LectureArchive>)map.get("lectureArchiveList");
		logger.trace(lectureArchiveList+"<--- lectureArchiveList");
		int lastPage = (int)map.get("lastPage");
		
		//모델로 뷰에 값 전달
		model.addAttribute("lectureArchiveList",lectureArchiveList);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("lastPage", lastPage);
		model.addAttribute("lectureNo", lectureNo);
		return "/teacher/lectureArchive";
	}
	
	//강좌별 자료실 상세보기 메서드
	//매개변수:강좌별 자료실 고유번호
	//리턴값:강좌별 자료실 고유번호를 참조하여 자료실 정보를 띄우는 뷰페이지
	@GetMapping("/teacher/lectureArchiveOne")
	public String lectureArchiveOne(Model model,
			HttpServletResponse response, 
			HttpServletRequest request,
			@RequestParam(value="lectureArchiveNo")int lectureArchiveNo) {
		//강좌별 자료실 상세보기
		LectureArchive lectureArchive = lectureArchiveService.getLectureArchiveOne(lectureArchiveNo);
		
		//세션정보
		HttpSession session = ((HttpServletRequest)request).getSession();
		// 세션에 있는 아이디를 가져옴
		String accountId = (String) session.getAttribute("accountId");
		//로깅을 이용한 디버그
		logger.debug(accountId+"<--- accountId");
		//조회수 증가
		long update_time = 0;
		//세션에 저장된 조회 시간 검색
		if(session.getAttribute("update_time"+lectureArchiveNo)!=null) {
			update_time = (long)session.getAttribute("update_time"+lectureArchiveNo);
		}
		//시스템 현재시간
		long current_time = System.currentTimeMillis();
		if(current_time - update_time>24*60*601000) {
			//조회수 증가 코드
			lectureArchiveService.increaseLectureArchiveCount(lectureArchiveNo);
			session.setAttribute("update_time"+lectureArchiveNo,current_time);
		}
		//모델로 뷰에 값 전달
		model.addAttribute("lectureArchive",lectureArchive);
		return "/teacher/lectureArchiveOne";
	}
	
	//강좌별 자료실 입력 폼 메서드
	//매개변수: 강좌 자료실 고유번호
	//리턴값:강좌별 자료실 입력 뷰페이지
	@GetMapping("/teacher/createLectureArchive")
	public String createLectureArchive(Model model,HttpSession session,@RequestParam(value="lectureNo")int lectureNo) {
		// 세션에 있는 아이디를 가져옴
		String  accountId = (String)session.getAttribute("accountId");
		// 세션에 있는 아이디를 참조하여 teacherService의 getTeacherOne의 정보를 가져옴
		Teacher teacher = teacherService.getTeacherOne(accountId);
		logger.debug("teacher",teacher);
		// model을 통해 View에 다음과 같은 정보들을 보내준다
		model.addAttribute("teacher",teacher);
		model.addAttribute("lectureNo",lectureNo);
		return "/teacher/createLectureArchive";
	}
	
	//강좌별 자료실 입력 액션 메서드
	//매개변수:lectureArchiveForm,HttpSession
	//리턴값:
	@PostMapping("/teacher/createLectureArchive")
	public String createLectureArchive(LectureArchiveForm lectureArchiveForm,LectureNotice lectureNotice,HttpSession session) {
		lectureArchiveService.createLectureArchive(lectureArchiveForm, session);
		return "redirect:/teacher/lectureArchive?lectureNo="+lectureNotice.getLectureNo()+"&&currentPage=1";
		
	}
	
	//강좌별 자료실 첨부파일 다운로드
	//매개변수: RequestParam: questionCommentFileUUID(파일 UUID) HttpServletRequest, HttpServletResponse
	//리턴값: 파일 다운로드
	@GetMapping("/teacher/downloadLectureArchiveFile")
	public void downloadLectureArchiveFile(
			@RequestParam("lectureArchiveFileUUID")String lectureArchiveFileUUID,
			HttpServletRequest request,HttpServletResponse response) {
		lectureArchiveService.downloadLectureArchiveFile(lectureArchiveFileUUID, request, response);
	}
}