package gd.fintech.lms.teacher.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gd.fintech.lms.manager.mapper.ManagerMapper;
import gd.fintech.lms.teacher.mapper.SyllabusMapper;
import gd.fintech.lms.teacher.vo.Syllabus;

@Service
@Transactional
public class SyllabusService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// 강의계획서 관련 Mapper
	@Autowired SyllabusMapper syllabusMapper;
	@Autowired ManagerMapper managerMapper;
	
	// 강의계획서를 출력하는 메소드
	// 매개변수: syllabusNo(강의계획서 고유번호)
	// 리턴값
	// #1. 고유번호에 해당하는 강의계획서
	// #2. 강의계획서 작성자 이름
	public Map<String, Object> getSyllabusDetail(int syllabusNo) {
		Syllabus syllabusDetail = syllabusMapper.selectSyllabusDetail(syllabusNo);
		// syllabusDetail에서 syllabusWriter(강의계획서 작성자)를 accountId(아이디)로 출력
		String accountId = syllabusDetail.getSyllabusWriter();
		// accountId(아이디)를 이용하여 syllabusWriterName(작성자 이름) 출력
		String syllabusWriterName = syllabusMapper.selectTeacherName(accountId);
		
		// 리턴값 저장
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("syllabusDetail", syllabusDetail);
		returnMap.put("syllabusWriterName", syllabusWriterName);
		
		return returnMap;
	}
	
	// 강사가 강의계획서를 작성하는 메소드
	// 매개변수: syllabus(강의계획서)
	// 리턴값: 없음
	// 강의계획서 작성
	public void createSyllabus(Syllabus syllabus) {
		logger.debug(syllabus.toString());
		syllabusMapper.insertSyllabus(syllabus);
	}
	
	// 강사가 강의계획서를 수정하는 메소드
	// 매개변수: syllabus(강의계획서)
	// 리턴값: 없음
	// 강의계획서 수정
	public void modifySyllabus(Syllabus syllabus) {
		logger.debug(syllabus.toString());
		syllabusMapper.updateSyllabus(syllabus);
	}
	
	// 강사 이름을 출력하는 메소드
	// 매개변수: accountId(아이디)
	// 리턴값: syllabusTeacherSign(아이디에 해당하는 강사 이름)
	// 아이디에 해당하는 강사 이름으로 서명하기 위해 출력
	public String getTeacherName(String accountId) {
		String syllabusTeacherSign = syllabusMapper.selectTeacherName(accountId);
		return syllabusTeacherSign;
	}
	
	// 강사가 강의계획서에 서명하는 메소드
	// 매개변수:
	// #1. syllabusNo(강의계획서 고유번호)
	// #2. syllabusTeacherSign(강사 서명)
	// 리턴값: 없음
	public void signSyllabusByTeacher(int syllabusNo, String syllabusTeacherSign) {
		syllabusMapper.updateSyllabusTeacherSign(syllabusNo, syllabusTeacherSign);
	}
	
	// 운영자 이름을 출력하는 메소드
	// 매개변수: accountId(아이디)
	// 리턴값: syllabusManagerSign(아이디에 해당하는 운영자 이름)
	// 아이디에 해당하는 운영자 이름으로 서명하기 위해 출력
	public String getManagerName(String accountId) {
		String syllabusManagerSign = managerMapper.selectManagerName(accountId);
		return syllabusManagerSign;
	}
	
	// 운영자가 강의계획서에 서명하는 메소드
	// 매개변수:
	// #1. syllabusNo(강의계획서 고유번호)
	// #2. syllabusManagerSign(운영자 서명)
	// 리턴값: 없음
	public void signSyllabusByManager(int syllabusNo, String syllabusManagerSign) {
		syllabusMapper.updateSyllabusManagerSign(syllabusNo, syllabusManagerSign);
	}
}
