package gd.fintech.lms.teacher.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gd.fintech.lms.teacher.mapper.SyllabusMapper;
import gd.fintech.lms.teacher.vo.Syllabus;

@Service
@Transactional
public class SyllabusService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// 강의계획서 관련 Mapper
	@Autowired SyllabusMapper syllabusMapper;
	
	// 강의계획서를 출력하는 메소드
	// 매개변수: syllabusNo(
	// 리턴값: 강의계획서
	public Syllabus getSyllabusDetail(int syllabusNo) {
		Syllabus syllabusDetail = syllabusMapper.selectSyllabusDetail(syllabusNo);
		return syllabusDetail;
	}
	
	// 강사가 강의계획서를 작성하는 메소드
	public void createSyllabus(Syllabus syllabus) {
		logger.debug(syllabus.toString());
		syllabusMapper.insertSyllabus(syllabus);
	}
	
	// 강사가 강의계획서를 수정하는 메소드
	public void modifySyllabus(Syllabus syllabus) {
		logger.debug(syllabus.toString());
		syllabusMapper.updateSyllabus(syllabus);
	}
	
	// 강사가 강의계획서에 서명하는 메소드
	public void signSyllabusByTeacher(int syllabusNo) {
		syllabusMapper.updateSyllabusTeacherSign(syllabusNo);
	}
	
	// 운영자가 강의계획서에 서명하는 메소드
	public void signSyllabusByManager(int syllabusNo) {
		syllabusMapper.updateSyllabusManagerSign(syllabusNo);
	}
}
