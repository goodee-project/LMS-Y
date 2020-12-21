package gd.fintech.lms.teacher.controller;

import java.util.List;
import java.util.Map;

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

import gd.fintech.lms.teacher.service.LectureNoticeService;
import gd.fintech.lms.teacher.vo.LectureNotice;

@Controller
public class LectureNoticeController {
	// Logger
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	// LectureNoticeService 객체 주입
	@Autowired
	private LectureNoticeService lectureNoticeService;

	// 강좌별 공지사항 목록 페이지 메서드
	// 매개변수:강좌별 번호
	// 리턴값:강좌별 번호를 참조하여 공지사항 정보를 띄우는 뷰페이지
	@SuppressWarnings("unchecked")
	@GetMapping("/teacher/lectureNotice")
	public String lectureNotice(Model model,ServletRequest request, 
			@RequestParam(value="lectureNo") int lectureNo,
			@RequestParam(value="currentPage")int currentPage) {
		
		Map<String,Object> map =lectureNoticeService.getLectureNoticeListByPage(lectureNo, currentPage);
		
		List<LectureNotice> lectureNoticeList = (List<LectureNotice>)map.get("lectureNoticeList");
		logger.trace(lectureNoticeList+"<--- lectureNoticeList");
		int lastPage = (int)map.get("lastPage");
		
		model.addAttribute("lectureNoticeList",lectureNoticeList);
		model.addAttribute("currentPage",currentPage);
		model.addAttribute("lastPage",lastPage);
		return "lectureNotice";

	}
}
