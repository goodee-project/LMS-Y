package gd.fintech.lms.student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import gd.fintech.lms.student.service.ClassRegistrationCancelService;

@Controller
public class ClassRegistrationCancelController {
	@Autowired ClassRegistrationCancelService classRegistrationCancelService;
	
	//수강취소 사유 입력
}
