package gd.fintech.lms.teacher.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import gd.fintech.lms.teacher.service.TestService;
import gd.fintech.lms.teacher.vo.MultipleChoice;
import gd.fintech.lms.teacher.vo.MultipleChoiceExample;

@RestController
public class TestRestController {
	@Autowired private TestService testService;
	
	@PostMapping("/teacher/modifyMultipleChoice")
	public boolean modifyMultipleChoice(MultipleChoice multipleChoice) {
		testService.modifyMultipleChoice(multipleChoice);
		return true;
	}
	
	@PostMapping("/teacher/modifyMultipleChoiceExample")
	public boolean modifyMultipleChoiceExample(MultipleChoiceExample multipleChoiceExample) {
		testService.modifyMultipleChoiceExample(multipleChoiceExample);
		return true;
	}
}
