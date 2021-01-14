package gd.fintech.lms.teacher.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gd.fintech.lms.teacher.service.SyllabusService;

// 강의계획서 첨부파일 비동기 Controller

@RestController
public class SyllabusRestController {
	// 강의계획서 정보 관련 Service
	@Autowired private SyllabusService syllabusService;
	
	// 강의계획서에 첨부된 파일을 삭제하는 메소드
	// 매개변수: syllabusFileUUID(강의계획서 첨부파일 UUID)
	// 리턴값: true
	// UUID에 해당하는 첨부파일 삭제
	@PostMapping("/teacher/removeSyllabusFile")
	public boolean removeSyllabusFile(@RequestParam("syllabusFileUUID") String syllabusFileUUID) {
		syllabusService.removeSyllabusFile(syllabusFileUUID);;
		return true;
	}
}
