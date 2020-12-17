package gd.fintech.lms.teacher.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gd.fintech.lms.teacher.mapper.MultipleChoiceExampleMapper;
import gd.fintech.lms.teacher.mapper.MultipleChoiceMapper;
import gd.fintech.lms.teacher.mapper.TestMapper;

@Service
@Transactional
public abstract class TestService {
	@Autowired private TestMapper testMapper;
	@Autowired private MultipleChoiceMapper multipleChoiceMapper;
	@Autowired private MultipleChoiceExampleMapper multipleChoiceExampleMapper;
}
