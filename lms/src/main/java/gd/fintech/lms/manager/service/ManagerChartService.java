package gd.fintech.lms.manager.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gd.fintech.lms.manager.mapper.ManagerChartMapper;

// 운영자가 보는 차트 Service

@Service
@Transactional
public class ManagerChartService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// 운영자 차트 데이터 Mapper
	@Autowired private ManagerChartMapper managerChartMapper;
	
	// 
	public Map<String, Object> getTeacherAndStudentCountChart() {
		logger.debug(null);
		return managerChartMapper.selectTeacherAndStudentCount();
	}
	
}
