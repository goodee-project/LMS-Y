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
import gd.fintech.lms.student.vo.ClassRegistration;
import gd.fintech.lms.teacher.vo.Teacher;


//운영자의 강좌에 대한 컨트롤러

@Controller
public class LectureManagerController {
	//debugLogger
	private final Logger logger = LoggerFactory.getLogger(LectureManagerController.class);
	
	// 강좌  
	@Autowired private LectureManagerService lectureManagerService;
	
	// 강좌 리스트 
	// 매개변수: Model @RequestParam: currentPage (현재페이지)
	// 리턴값: 강좌 리스트 페이지 출력
	@GetMapping("/manager/managerLecture")
	public String managerLectureList(Model model,
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
	
	// 강의 리스트
	@GetMapping("/manager/lectureStudentList")
	public String lectureStudentList(Model model, int lectureNo) {
		List<ClassRegistration> studentList = lectureManagerService.getlectureStudentList(lectureNo);
		model.addAttribute("studentList", studentList);
		return "manager/managerLectureDetail";
	}
	
	
	
	// 강좌에서 수강 상태를 거절로 바꾸는 액션 
	@GetMapping("/manager/modifylectureStudentReject")
 	public String createManagerEducation(
 			@RequestParam (value="lectureNo")int lectureNo,
 			@RequestParam(value="accountId")String accountId) {
		lectureManagerService.modifylectureStudentReject(accountId, lectureNo);
 		return "redirect:/manager/managerLectureDetail?lectureNo="+lectureNo;
 	}
 
	// 강좌에서 수강상태를 수강중으로 바꾸는 액션 
	@GetMapping("/manager/modifylectureStudentCk")
	public String modifylectureStudentCk(
			@RequestParam(value="lectureNo")int lectureNo,
			@RequestParam(value="accountId")String accountId) {
		lectureManagerService.modifylectureStudentCk(accountId, lectureNo);
		return "redirect:/manager/managerLectureDetail?lectureNo="+lectureNo;
	}

	// 강좌 개설 폼
	// 매개변수:model
	// 리턴값: 강좌의 개설
	@GetMapping("/manager/createLectureManager")
	public String createLecture(Model model) {
	 List<Textbook> textbookList = lectureManagerService.getLectureTextBookList();
	 List<Subject>	subjectList = lectureManagerService.getLectureSubjectList();
	 List<Classroom> classroomList = lectureManagerService.getLectureClassroomList();
	 List<Teacher> teacherList = lectureManagerService.getLectureTeacherList();
	 logger.debug("classroomList"+ classroomList );
	 model.addAttribute("classroomList", classroomList);
	 model.addAttribute("textbookList", textbookList);
	 model.addAttribute("subjectList", subjectList);
	 model.addAttribute("teacherList", teacherList);
	return "manager/createLectureManager";
	}
	
	
	// 강좌 개설 액션
	// 매개변수: 강죄정보
	// 리턴값: 강좌의 리스트
	@PostMapping("/manager/createLectureManager")
	public String createLecture(Lecture lecture) {
		lectureManagerService.createLecture(lecture);
		logger.debug("lecture"+ lecture );
		return "redirect:/manager/managerLecture";
	}
	
	// 강좌 수정 폼
	// 매개변수: Model, @RequestParam: lectureNo (강좌 고유번호)
	// 리턴값: 강좌 개설 액션
	@GetMapping("/manager/modifyLectureManager")
	public String modifyLecture(Model model, 
			@RequestParam(name="lectureNo")int lectureNo) {
		List<Textbook> textbookList = lectureManagerService.getLectureTextBookList();
		List<Subject>	subjectList = lectureManagerService.getLectureSubjectList();
		List<Classroom> classroomList = lectureManagerService.getLectureClassroomList();
		List<Teacher> teacherList = lectureManagerService.getLectureTeacherList();
		Lecture lecture = lectureManagerService.getManagerLectureDetail(lectureNo);
		model.addAttribute("lecture", lecture);
		model.addAttribute("classroomList", classroomList);
		model.addAttribute("textbookList", textbookList);
		model.addAttribute("subjectList", subjectList);
		model.addAttribute("teacherList", teacherList);
		return "manager/modifyLectureManager";
	}
	// 강좌 수정 액션
	// 매개변수: 강좌 정보
	// 리턴값: 수정된 강좌 상세보기 페이지 출력
	@PostMapping("/manager/modifyLectureManager")
	public String modifyLecture(Lecture lecture) {
		lectureManagerService.modifyLecture(lecture);
		return "redirect:/manager/managerLectureDetail?lectureNo="+lecture.getLectureNo();
	}
	
	// 강좌 상세정보
	// 매개변수: model, @RequestParam: lectureNo(강좌 고유번호)
	// 리턴값: lectureNo에 해당하는 강좌 상세정보
	@GetMapping("/manager/managerLectureDetail")
	public String managerLectureDetail(Model model,
			@RequestParam("lectureNo")int lectureNo) {
		Lecture lecture = lectureManagerService.getManagerLectureDetail(lectureNo);
		List<ClassRegistration> classRegistration = lectureManagerService.getlectureStudentList(lectureNo);
		model.addAttribute("classRegistration", classRegistration);
		model.addAttribute("lecture", lecture);
		return "manager/managerLectureDetail";
		}
	
}