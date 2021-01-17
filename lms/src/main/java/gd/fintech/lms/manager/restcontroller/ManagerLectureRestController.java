package gd.fintech.lms.manager.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gd.fintech.lms.manager.service.LectureManagerService;

// 운영자의 강좌 관련 비동기 Controller

@RestController
public class ManagerLectureRestController {
	
	@Autowired LectureManagerService lectureManagerService;

	
	// 강좌에서 강사 이름을 받아오는 메소드
	// 매개변수: 강사 id 
	// 리턴값: 강사 id 에 해당하는 강사 이름
	@GetMapping("/manager/teacherName")
	public List<String> LectureTeacherName(@RequestParam(value = "accountId", required = true) String accountId) {
		return lectureManagerService.getTeacherName(accountId);
	}
	
	// 운영자의 비밀번호를 수정할때 확인하는 메소드
	// 매개변수: 운영자의 계정id, 계정pw
	// 리턴값: 승인 또는 비승인
	@PostMapping("/manager/lectureNameCheck")
	public String lectureTeacherNameCk(@RequestParam(value="accountId",required= true)String accountId,
			@RequestParam(value="teacherName",required= true)String teacherName){
		String teacherNameck = lectureManagerService.getTeacherNameCk(accountId, teacherName);
		return teacherNameck;
	}
}
