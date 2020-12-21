package gd.fintech.lms.student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import gd.fintech.lms.student.service.AnswerSheetService;

@Controller
public class AnswerSheetController {
	@Autowired AnswerSheetService answerSheetMapper;
}
