  package gd.fintech.lms.student.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gd.fintech.lms.student.mapper.ClassRegistrationMapper;

@Service
@Transactional
public class ClassRegistrationService {
	@Autowired ClassRegistrationMapper classRegistrationMapper;
	
	//학생이 수강신청한 목록(페이징)
	
	//수강 신청한 과목 상세보기 
}
