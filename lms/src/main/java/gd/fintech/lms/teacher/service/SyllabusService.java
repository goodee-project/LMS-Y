package gd.fintech.lms.teacher.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gd.fintech.lms.manager.mapper.ManagerMapper;
import gd.fintech.lms.manager.vo.Lecture;
import gd.fintech.lms.teacher.mapper.SyllabusMapper;
import gd.fintech.lms.teacher.mapper.TeacherLectureMapper;
import gd.fintech.lms.teacher.vo.Syllabus;

@Service
@Transactional
public class SyllabusService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// 강의계획서 관련 Mapper
	@Autowired SyllabusMapper syllabusMapper;
	// 운영자 관련 Mapper
	@Autowired ManagerMapper managerMapper;
	// 강좌 관련 Mapper
	@Autowired TeacherLectureMapper lectureMapper;
	
	// 강의계획서를 출력하는 메소드
	// 매개변수: syllabusNo(강의계획서 고유번호)
	// 리턴값
	// #1. 고유번호에 해당하는 강의계획서
	// #2. 강의계획서 작성자 이름
	public Map<String, Object> getSyllabusDetail(int lectureNo) {
		Syllabus syllabusDetail = syllabusMapper.selectSyllabusDetail(lectureNo);
		Lecture lectureDetail = lectureMapper.selectTeacherLectureOne(lectureNo);
		
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("syllabusDetail", syllabusDetail);
		returnMap.put("lectureDetail", lectureDetail);
		
		return returnMap;
	}
	
	// 강사가 강의계획서를 작성하는 메소드
	// 매개변수: syllabus(강의계획서 정보)
	// 리턴값: 없음
	// 강의계획서 작성
	// 강좌를 담당하는 강사만 강의계획서 작성 가능
	public void createSyllabus(HttpSession session, Syllabus syllabus, int lectureNo) {
		logger.debug(syllabus.toString());
		
		// 현재 로그인한 강사의 아이디와 이름을 출력
		// session에서 accountId(아이디)를 출력
		String accountId = (String)session.getAttribute("accountId");
		// syllabusWriter(강의계획서 작성자)을 출력
		String syllabusWriter = syllabusMapper.selectTeacherName(accountId);
		
		// 강좌를 담당하는 강사의 아이디를 확인하기 위한 코드
		// 강좌 고유번호에 해당하는 강좌 정보를 출력
		Lecture lectureDetail = lectureMapper.selectTeacherLectureOne(lectureNo);
		// 강좌를 담당하는 강사 아이디를 teacherId로 출력
		String teacherId = lectureDetail.getAccountId();
		
		// teacherId(강좌를 담당하는 강사 아이디)와 accountId(현재 로그인 한 강사의 아이디)가 같으면 강의계획서 작성
		if(teacherId.equals(accountId)) {
			// syllabus(강의계획서 정보)에 accountId(아이디) 추가
			syllabus.setAccountId(accountId);
			// syllabus(강의계획서 정보)에 syllabusWriter(강의계획서 작성자) 추가
			syllabus.setSyllabusWriter(syllabusWriter);
			syllabusMapper.insertSyllabus(syllabus);
			logger.debug("강의계획서 작성 성공");
		} else {
			logger.debug("강의계획서 작성 실패");
		}
	}
	
	// 강사가 강의계획서를 수정하는 메소드
	// 매개변수: syllabus(강의계획서 정보)
	// 리턴값: 없음
	// 강의계획서 수정
	public void modifySyllabus(HttpSession session, Syllabus syllabus, int lectureNo) {
		logger.debug(syllabus.toString());
		
		// 현재 로그인한 강사의 아이디 출력
		// session에서 accountId(아이디)를 출력
		String accountId = (String)session.getAttribute("accountId");
		
		// 강좌를 담당하는 강사의 아이디를 확인하기 위한 코드
		// 강좌 고유번호에 해당하는 강좌 정보를 출력
		Lecture lectureDetail = lectureMapper.selectTeacherLectureOne(lectureNo);
		// 강좌를 담당하는 강사 아이디를 teacherId로 출력
		String teacherId = lectureDetail.getAccountId();
		
		// teacherId(강좌를 담당하는 강사 아이디)와 accountId(현재 로그인 한 강사의 아이디)가 같으면 강의계획서 수정
		if(teacherId.equals(accountId)) {
			syllabusMapper.updateSyllabus(syllabus);
			logger.debug("강의계획서 수정 성공");
		} else {
			logger.debug("강의계획서 수정 실패");
		}
	}
	
	// 강사가 강의계획서에 서명하는 메소드
	// 매개변수:
	// #1. syllabusNo(강의계획서 고유번호)
	// #2. syllabusTeacherSign(강사 서명)
	// 리턴값: 없음
	// 서명 여부를 확인하여 서명
	public void signSyllabusByTeacher(HttpSession session, int lectureNo) {
		// session에서 accountId(아이디)을 출력
		String accountId = (String)session.getAttribute("accountId");
		// accountId(아이디)로 서명할 이름 syllabusTeacherSign(운영자 서명)으로 출력
		String syllabusTeacherSign = syllabusMapper.selectTeacherName(accountId);
		
		// 강좌를 담당하는 강사의 아이디를 확인하기 위한 코드
		// 강좌 고유번호에 해당하는 강좌 정보를 출력
		Lecture lectureDetail = lectureMapper.selectTeacherLectureOne(lectureNo);
		// 강좌를 담당하는 강사 아이디를 teacherId로 출력
		String teacherId = lectureDetail.getAccountId();
		
		// 서명 여부를 확인하기 위한 코드
		// syllabusNo(강의계획서 고유번호)에 해당하는 syllabusDetail(강의계획서 정보) 출력 
		Syllabus syllabusDetail = syllabusMapper.selectSyllabusDetail(lectureNo);
		// syllabusDetail의 syllabusTeacherSign(강사 서명)를 TeacherSign(강사 서명)으로 출력
		String teacherSign = syllabusDetail.getSyllabusTeacherSign();
		// syllabusDetail의 syllabusTeacherSignDate(강사 서명일자)를 teacherSignDate(강사 서명일자)로 출력
		String teacherSignDate = syllabusDetail.getSyllabusTeacherSignDate();
		
		// 만약 강좌를 담당하는 강사 아이디와 현대 로그인 한 강사의 아이디가 같고, 강사 서명과 강사 서명일자가 없다면 서명
		if((teacherId.equals(accountId)) && (teacherSign == null && teacherSignDate == null)) {
			syllabusMapper.updateTeacherSign(lectureNo, syllabusTeacherSign);
			logger.debug("서명 성공");
		} else {
			logger.debug("서명 실패");
		}
	}

	// 운영자가 강의계획서에 서명하는 메소드
	// 매개변수:
	// #1. syllabusNo(강의계획서 고유번호)
	// #2. syllabusManagerSign(운영자 서명)
	// 리턴값: 없음
	// 서명 여부를 확인하여 서명
	public void signSyllabusByManager(HttpSession session, int lectureNo) {
		// session에서 accountId(아이디)을 출력
		String accountId = (String)session.getAttribute("accountId");
		// accountId(아이디)로 서명할 이름 syllabusManagerSign(운영자 서명)으로 출력
		String syllabusManagerSign = managerMapper.selectManagerName(accountId);
		
		// 서명 여부를 확인하기 위한 코드
		// syllabusNo(강의계획서 고유번호)에 해당하는 syllabusDetail(강의계획서 정보) 출력
		Syllabus syllabusDetail = syllabusMapper.selectSyllabusDetail(lectureNo);
		// syllabusDetail의 syllabusTeacherSign(강사 서명)를 TeacherSign(강사 서명)으로 출력
		String teacherSign = syllabusDetail.getSyllabusTeacherSign();
		// syllabusDetail의 syllabusTeacherSignDate(강사 서명일자)를 teacherSignDate(강사 서명일자)로 출력
		String teacherSignDate = syllabusDetail.getSyllabusTeacherSignDate();
		// syllabusDetail(강의계획서 정보)에서 syllabusManagerSign(운영자 서명)를 managerSign(운영자 서명)으로 출력
		String managerSign = syllabusDetail.getSyllabusManagerSign();
		// syllabusDetail(강의계획서 정보)에서 syllabusManagerSignDate(운영자 서명일자)를 managerSignDate(운영자 서명일자)로 출력
		String managerSignDate = syllabusDetail.getSyllabusManagerSignDate();
		
		// 만약 강사 서명과 강사 서명일자, 운영자 서명과 운영자 서명일자가 없다면 서명
		if((teacherSign != null && teacherSignDate != null) && (managerSign == null && managerSignDate == null)) {
			syllabusMapper.updateManagerSign(lectureNo, syllabusManagerSign);
			logger.debug("서명 성공");
		} else {
			logger.debug("서명 실패");
		}
	}
}
