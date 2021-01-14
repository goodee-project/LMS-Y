package gd.fintech.lms.manager.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gd.fintech.lms.manager.service.SubjectService;

// 과목 정보를 입력 시에 과목명을 검색하는 비동기 Controller

@RestController
public class SubjectRestController {
	// 과목 정보 관련 Service
	@Autowired SubjectService subjectService;
	
	// 과목명의 중복 여부를 확인하는 메소드
	// 매개변수: subjectName(과목명)
	// 리턴값: 중복(noPass) 또는 중복되지 않음(pass)
	@PostMapping("/manager/subjectNameCheck")
	public String subjectNameCheck(@RequestParam(value = "subjectName", required = true) String subjectName) {
		String subjectNameCk = subjectService.getSubjectName(subjectName);
		// 만약 조회된 ISBN이 없다면 pass를 return
		if(subjectNameCk == null) {
			return "Pass";
		}
		// 조회된 ISBN이 있다면 noPass를 return
		return "noPass";
	}
}
