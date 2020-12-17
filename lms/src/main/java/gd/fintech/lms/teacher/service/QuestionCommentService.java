package gd.fintech.lms.teacher.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gd.fintech.lms.teacher.mapper.QuestionCommentFileMapper;
import gd.fintech.lms.teacher.mapper.QuestionCommentMapper;

@Service
@Transactional
public abstract class QuestionCommentService {
	@Autowired private QuestionCommentMapper questionCommentMapper;
	@Autowired private QuestionCommentFileMapper questionCommentFileMapper;
}
