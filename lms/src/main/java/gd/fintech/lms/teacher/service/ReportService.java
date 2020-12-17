package gd.fintech.lms.teacher.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gd.fintech.lms.teacher.mapper.ReportMapper;

@Service
@Transactional
public abstract class ReportService {
	@Autowired private ReportMapper reportMapper;
}
