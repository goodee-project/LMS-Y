package gd.fintech.lms.manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import gd.fintech.lms.manager.service.ClassroomService;
import gd.fintech.lms.manager.vo.Classroom;

//Classroom 관련 컨트롤러 

@Controller
public class ClassroomController {
	
	@Autowired private ClassroomService classroomService;
	
	
	// 강의실 정보를 출력하는 컨트롤러
	// 매개변수:
	// 리턴값:
	@GetMapping("/manager/classroom/classroomList")
	public String ClassroomList(Model model) {
		List<Classroom> classroomList = classroomService.getClassroomList();
		model.addAttribute("classroomList", classroomList);
		return "classroomList";
			
	}
	
	
	// 강의실 개설 폼
	// 매개변수:
	// 리턴값:
	@GetMapping("/manager/classroom/createClassroom")
	public String createClassroom() {
		return "createClassroom";
	}
	
	
	// 강의실 개설
	// 매개변수:
	// 리턴값:
	@PostMapping("/manager/classroom/createClassroom")
	public String createClassroom(Classroom classroom) {
		classroomService.createClassroom(classroom);
		return "redirect:/classroomList";
	
   
		
		
		
	
		
		
	}

}