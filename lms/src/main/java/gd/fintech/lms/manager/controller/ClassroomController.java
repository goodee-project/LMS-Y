package gd.fintech.lms.manager.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gd.fintech.lms.manager.service.ClassroomService;
import gd.fintech.lms.manager.vo.Classroom;

//Classroom 관련 컨트롤러 

@Controller
public class ClassroomController {
	//debugLogger
	private final Logger logger = LoggerFactory.getLogger(ClassroomController.class);
		
	@Autowired private ClassroomService classroomService;
	
	
	// 강의실 정보를 출력하는 컨트롤러
	// 매개변수:Model 
	// 리턴값: 강의실 리스트 페이지 출력
	@GetMapping("/manager/classroomList")
	public String ClassroomList(Model model) {
		List<Classroom> classroomList = classroomService.getClassroomList();
		model.addAttribute("classroomList", classroomList);
		return "manager/classroomList";
			
	}
	
	
	// 강의실 개설 폼
	// 리턴값: 강의실 입력 액션 
	@GetMapping("/manager/createClassroom")
	public String createClassroom() {
		return "manager/createClassroom";
	}
	
	
	// 강의실 개설 액션
	// 매개변수: 강의실 정보 
	// 리턴값: 입력한 정보를 포함한 강의실 리스트 페이지 출력 
	@PostMapping("/manager/createClassroom")
	public String createClassroom(Classroom classroom) {
		classroomService.createClassroom(classroom);
		logger.debug("Classroom"+ classroom );
		return "redirect:/manager/classroomList";
	}
	
	
	// 강의실 정보 수정 폼 
	// 리턴값: 강의실 정보 입력 액션 
	@GetMapping("/manager/modifyClassroom")
	public String modifyClassroom(Model model, 
			@RequestParam(name="classroomNo")int classroomNo) {
		Classroom classroom = classroomService.getClassroomDetail(classroomNo);
		model.addAttribute("classroom", classroom);
		logger.debug("Classroom"+ classroom );
		return "manager/modifyClassroom";
	}
	
	// 강의실 정보 수정 액션 
	// 리턴값: 입력한 강의실 행의 수정된 페이지
	@PostMapping("/manager/modifyClassroom")
	public String modifyClassroom(Classroom classroom) {
		classroomService.modifyClassroom(classroom);
		logger.debug("Classroom"+ classroom );
		return "redirect:/manager/classroomDetail?classroomNo="+classroom.getClassroomNo();
	}
	
	// 강의실 상세정보
	// 리턴값: 입력한 classroomNo에 해당하는 강의실 상세정보 
	@GetMapping("/manager/classroomDetail")
	public String classroomDetail(Model model,
			@RequestParam("classroomNo")int classroomNo) {
		Classroom classroom = classroomService.getClassroomDetail(classroomNo);
		model.addAttribute("classroom", classroom);
		logger.debug("Classroom"+ classroom );
		return "manager/classroomDetail";
		
	}
	
	// 강의실 삭제 
	// 리턴값: classroomNo에 해당하는 강의실 삭제
	@GetMapping("/manager/removeClassroom")
	public String removeClassroom(
			@RequestParam(value="classroomNo")int classroomNo) {
		classroomService.removeClassroom(classroomNo);
		return  "redirect:/manager/classroomList";
	}
	
	
}