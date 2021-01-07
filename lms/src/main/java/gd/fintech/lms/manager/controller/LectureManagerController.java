package gd.fintech.lms.manager.controller;



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

import gd.fintech.lms.manager.service.LectureManagerService;
import gd.fintech.lms.manager.vo.Classroom;
import gd.fintech.lms.manager.vo.Lecture;
import gd.fintech.lms.manager.vo.Subject;
import gd.fintech.lms.manager.vo.Textbook;




//운영자의 강좌에 대한 컨트롤러

@Controller
public class LectureManagerController {
	//debugLogger
	private final Logger logger = LoggerFactory.getLogger(LectureManagerController.class);
	
	// 강좌  
	@Autowired private LectureManagerService lectureManagerService;
	
	// 강좌 리스트 
	// 매개변수: Model @RequestParam: currentPage (현재페이지)
	// 리턴값: FAQ 리스트 페이지 출력
	@GetMapping("/manager/managerLecture")
	public String FAQList(Model model,
		@RequestParam(value="currentPage",defaultValue = "1")int currentPage) {
	 Map<String, Object> map = lectureManagerService.getManagerLectureListByPage(currentPage);
	 logger.debug(map.toString());
	 model.addAttribute("currentPage",currentPage);
	 model.addAttribute("ManagerLectureList",map.get("ManagerLectureList"));
	 model.addAttribute("lastPage",map.get("lastPage"));
	 model.addAttribute("navBeginPage",map.get("navBeginPage"));
	 model.addAttribute("navLastPage",map.get("navLastPage"));
     model.addAttribute("navPerPage",map.get("navPerPage"));
     
     	return "manager/managerLecture";
	
	}	
	// 강좌 개설 폼
	// 매개변수:
	// 리턴값:
	@GetMapping("/manager/createLectureManager")
	public String createLecture(Model model) {
	 List<Textbook> textbookList = lectureManagerService.getLectureTextBookList();
	 List<Subject>	subjectList = lectureManagerService.getLectureSubjectList();
	 List<Classroom> classroomList = lectureManagerService.getLectureClassroomList();
	 logger.debug("classroomList"+ classroomList );
	 model.addAttribute("classroomList", classroomList);
	 model.addAttribute("textbookList", textbookList);
	 model.addAttribute("subjectList", subjectList);
	 
		return "manager/createLectureManager";
	}
	
	
	// 강좌 개설 액션
	// 매개변수:
	// 리턴값:
	@PostMapping("/manager/createLectureManager")
	public String createLecture(Lecture lecture) {
		lectureManagerService.createLecture(lecture);
		logger.debug("lecture"+ lecture );
		return "redirect:/manager/index";
	}
	
	// 강좌 수정 폼
	// 매개변수:
	// 리턴값:
	@GetMapping("/manager/modifyLectureManager")
	public String modifyLecture(Model model, 
			@RequestParam(name="lectureNo")int lectureNo) {
		List<Textbook> textbookList = lectureManagerService.getLectureTextBookList();
		List<Subject>	subjectList = lectureManagerService.getLectureSubjectList();
		List<Classroom> classroomList = lectureManagerService.getLectureClassroomList();
		Lecture lecture = lectureManagerService.getManagerLectureDetail(lectureNo);
		model.addAttribute("lecture", lecture);
		model.addAttribute("classroomList", classroomList);
		model.addAttribute("textbookList", textbookList);
		model.addAttribute("subjectList", subjectList);
		return "manager/modifyLectureManager";
	}
	// 강좌 수정 액션
	// 매개변수:
	// 리턴값:
	@PostMapping("/manager/modifyLectureManager")
	public String modifyLecture(Lecture lecture) {
		lectureManagerService.modifyLecture(lecture);
		return "redirect:/manager/managerLectureDetail?lectureNo="+lecture.getLectureNo();
	}
	
	// 강좌 상세정보
	// 매개변수:
	// 리턴값:
	@GetMapping("/manager/managerLectureDetail")
	public String managerLectureDetail(Model model,
			@RequestParam("lectureNo")int lectureNo) {
		Lecture lecture = lectureManagerService.getManagerLectureDetail(lectureNo);
		model.addAttribute("lecture", lecture);
		logger.debug("lecture"+ lecture );
		return "manager/managerLectureDetail";
		}
	
	
	
}
