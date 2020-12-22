package gd.fintech.lms.teacher.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gd.fintech.lms.teacher.service.LectureNoticeService;
import gd.fintech.lms.teacher.vo.LectureNotice;

@Controller
public class LectureNoticeController {
	// Logger
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	// LectureNoticeService 객체 주입
	@Autowired
	private LectureNoticeService lectureNoticeService;

	//강좌별 공지사항 목록 페이지 메서드
	//매개변수:강좌별 번호
	//리턴값:강좌별 번호를 참조하여 공지사항 정보를 띄우는 뷰페이지
	@SuppressWarnings("unchecked")
	@GetMapping("/teacher/lectureNotice")
	public String lectureNotice(Model model,
			@RequestParam(value="lectureNo",defaultValue = "1") int lectureNo,
			@RequestParam(value="currentPage",defaultValue = "10")int currentPage) {
		
		Map<String,Object> map = lectureNoticeService.getLectureNoticeListByPage(lectureNo, currentPage);
		
		List<LectureNotice> lectureNoticeList = (List<LectureNotice>)map.get("lectureNoticeList");
		logger.trace(lectureNoticeList+"<--- lectureNoticeList");
		int lastPage = (int)map.get("lastPage");
		
		//모델로 뷰에 값 전달
		model.addAttribute("lectureNoticeList",lectureNoticeList);
		model.addAttribute("currentPage",currentPage);
		model.addAttribute("lastPage",lastPage);
		model.addAttribute("lectureNo",lectureNo);
		return "lectureNotice";

	}
	
	//강좌별 공지사항 상세보기 페이지 메서드
	//매개변수:깅좌별 공지사항 고유번호
	//리턴값:강좌별 공지사항 고유번호를 참조하여 공지사항 정보를 띄우는 뷰페이지
	@GetMapping("/teacher/lectureNoticeOne")
	public String LectureNoticeOne(Model model,
			@RequestParam(value="lectureNoticeNo")int lectureNoticeNo) {
		LectureNotice lectureNotice = lectureNoticeService.getLectureNoticeOne(lectureNoticeNo);
		//모델로 뷰에 값 전달
		model.addAttribute("lectureNotice",lectureNotice);
		return "lectureNoticeOne";
	}
	
	//강좌별 공지사항 입력 폼
	//매개변수:강좌 고유번호
	//리턴값:강좌별 공지사항 고유번호를 참조하여 공지사항을 입력하는 뷰페이지
	@GetMapping("/teacher/createLectureNotice")
	public String createLectureNotice(Model model,
			@RequestParam(value="lectureNo")int lectureNo) {
		
		//LectureNotice vo에서 강좌 고유번호를 가져옴.
		LectureNotice lectureNotice = lectureNoticeService.getLectureNoticeOne(lectureNo);
		lectureNotice.getLectureNo();
		//모델로 강좌 고유번호 뷰에 전달 
		model.addAttribute("lectureNo",lectureNo);
		return "createLectureNotice";
	}
	//강좌별 공지사항 입력 액션
	//매개변수:강좌 고유번호
	//리턴값:강좌별 공지사항 고유번호를 참조하여 공지사항 상세보기 뷰페이지
	@PostMapping("/teacher/createLectureNotice")
	public String createLectureNotice(LectureNotice lectureNotice) {
		lectureNoticeService.createLectureNotice(lectureNotice);
		return "redirect:/teacher/lectureNotice?lectureNo="+lectureNotice.getLectureNo()+"&&currentPage=1";
	}
	//강좌별 공지사항 수정 폼
	//매개변수:강좌 공지사항 고유번호
	//리턴값:강좌별 공지사항 고유번호를 참조하여 공지사항을 수정하는 뷰페이지
	@GetMapping("/teacher/modifyLectureNotice")
	public String modifyLectureNotice(Model model,
			@RequestParam(value="lectureNoticeNo")int lectureNoticeNo) {
		LectureNotice lectureNotice = lectureNoticeService.getLectureNoticeOne(lectureNoticeNo);
	
		model.addAttribute("lectureNotice",lectureNotice);
		return "modifyLectureNotice";
	}
	//강좌별 공지사항 수정 액션
	//매개변수:강좌 공지사항 고유번호
	//리턴값:강좌별 공지사항 고유번호를 참조하여 공지사항 상세보기 뷰페이지
	@PostMapping("/teacher/modifyLectureNotice")
	public String modifyLectureNotice(LectureNotice lectureNotice) {
		lectureNoticeService.modifyLectureNotice(lectureNotice);
		logger.trace(lectureNotice+"<--- lectureNotice");
		return "redirect:/teacher/lectureNoticeOne?lectureNoticeNo="+lectureNotice.getLectureNoticeNo();
	}
	//강좌별 공지사항 삭제
}
