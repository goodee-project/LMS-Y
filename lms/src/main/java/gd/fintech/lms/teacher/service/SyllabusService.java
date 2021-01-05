package gd.fintech.lms.teacher.service;

import javax.servlet.http.HttpSession;

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
	public Syllabus getSyllabusDetail(int syllabusNo) {
		Syllabus syllabusDetail = syllabusMapper.selectSyllabusDetail(syllabusNo);
		
		return syllabusDetail;
	}
	
	// 강사가 강의계획서를 작성하는 메소드
	// 매개변수: syllabus(강의계획서)
	// 리턴값: 없음
	// 강의계획서 작성
	public void createSyllabus(HttpSession session, Syllabus syllabus) {
		logger.debug(syllabus.toString());
		// session에서 accountId(아이디)를 출력
		String accountId = (String)session.getAttribute("accountId");
		String syllabusWriter = syllabusMapper.selectTeacherName(accountId);
		
		// syllabus에 accountId(아이디) 추가
		syllabus.setAccountId(accountId);
		// syllabus에 syllabusWriter(작성자) 추가
		syllabus.setSyllabusWriter(syllabusWriter);
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
	
	// 강사가 강의계획서에 서명하는 메소드
	// 매개변수:
	// #1. syllabusNo(강의계획서 고유번호)
	// #2. syllabusTeacherSign(강사 서명)
	// 리턴값: 없음
	// 서명 여부를 확인하여 서명
	public void signSyllabusByTeacher(HttpSession session, int syllabusNo) {
		// session에서 accountId(아이디)을 출력
		String accountId = (String)session.getAttribute("accountId");
		// accountId(아이디)로 서명할 이름 syllabusTeacherSign(운영자 서명)으로 출력
		String syllabusTeacherSign = syllabusMapper.selectTeacherName(accountId);
		
		// 서명 여부를 확인하기 위함
		// syllabusNo(강의계획서 고유번호)에 해당하는 syllabusDetail(강의계획서 정보) 출력 
		Syllabus syllabusDetail = syllabusMapper.selectSyllabusDetail(syllabusNo);
		// syllabusDetail의 syllabusTeacherSign(강사 서명)를 TeacherSign(강사 서명)으로 출력
		String teacherSign = syllabusDetail.getSyllabusTeacherSign();
		// syllabusDetail의 syllabusTeacherSignDate(강사 서명일자)를 teacherSignDate(강사 서명일자)으로 출력
		String teacherSignDate = syllabusDetail.getSyllabusTeacherSignDate();
		
		// 강사 서명과 강사 서명 일자가 없다면 서명
		if(teacherSign == null && teacherSignDate == null) {
			syllabusMapper.updateTeacherSign(syllabusNo, syllabusTeacherSign);
		}
	}

	// 운영자가 강의계획서에 서명하는 메소드
	// 매개변수:
	// #1. syllabusNo(강의계획서 고유번호)
	// #2. syllabusManagerSign(운영자 서명)
	// 리턴값: 없음
	// 서명 여부를 확인하여 서명
	public void signSyllabusByManager(HttpSession session, int syllabusNo) {
		// session에서 accountId(아이디)을 출력
		String accountId = (String)session.getAttribute("accountId");
		// accountId(아이디)로 서명할 이름 syllabusManagerSign(운영자 서명)으로 출력
		String syllabusManagerSign = managerMapper.selectManagerName(accountId);
		
		// 서명 여부를 확인하기 위함
		// syllabusNo(강의계획서 고유번호)에 해당하는 syllabusDetail(강의계획서 정보) 출력 
		Syllabus syllabusDetail = syllabusMapper.selectSyllabusDetail(syllabusNo);
		// syllabusDetail(강의계획서 정보)에서 syllabusManagerSign(운영자 서명)를 managerSign(운영자 서명)으로 출력
		String managerSign = syllabusDetail.getSyllabusManagerSign();
		// syllabusDetail(강의계획서 정보)에서 syllabusManagerSignDate(운영자 서명일자)를 managerSignDate(운영자 서명일자)으로 출력
		String managerSignDate = syllabusDetail.getSyllabusManagerSignDate();
		
		// 운영자 서명과 운영자 서명 일자가 없다면 서명
		if(managerSign == null && managerSignDate == null) {
			syllabusMapper.updateManagerSign(syllabusNo, syllabusManagerSign);
		}
	}
}
