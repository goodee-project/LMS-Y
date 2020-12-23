package gd.fintech.lms.teacher.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gd.fintech.lms.teacher.service.LectureArchiveService;
import gd.fintech.lms.teacher.vo.LectureArchive;

//강좌별 자료실 컨트롤러

@Controller
public class LectureArchiveController {
	//Logger
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//LectureArchiveService 객체 주입
	@Autowired private LectureArchiveService lectureArchiveService;
	
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
			@RequestParam(value="lectureArchiveNo")int lectureArchiveNo) {
		//강좌별 자료실 상세보기
		LectureArchive lectureArchive = lectureArchiveService.getLectureArchiveOne(lectureArchiveNo);
		
		//모델로 뷰에 값 전달
		model.addAttribute("lectureArchive",lectureArchive);
		return "/teacher/lectureArchiveOne";
	}
}
