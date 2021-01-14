package gd.fintech.lms.teacher.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gd.fintech.lms.manager.vo.Lecture;
import gd.fintech.lms.teacher.mapper.TeacherChartMapper;

// 강사 차트 service

@Service
public class TeacherChartService {
	// 강사 차트 mapper
	@Autowired private TeacherChartMapper teacherChartMapper;
	
	// 강사별 강좌 가져오기 
	// 매개변수 : 강사 ID
	// 리턴값 : 강사의 강좌 리스트 
	public List<Lecture> getLectureListByAccountId(HttpSession session) {
		return teacherChartMapper.selectLectureListByAccountId((String)session.getAttribute("accountId"));
	}
	
	// 계정에 디폴트 강좌를 가져옴
	// 매개변수 : session
	// 리턴값 : 디폴트 강좌번호 
	public int getDefaultLecture(HttpSession session) {
		return teacherChartMapper.selectDefaultLecture((String)session.getAttribute("accountId"));
	}
	
	// 강좌의 과제 제출률 
	// 매개변수 : 강좌 번호
	// 리턴값 : 강좌 과제들의 제출률 출력
	public List<Map<String, Object>> getReportSubmitRateByLecture(int lectureNo) {
		return teacherChartMapper.selectReportSubmitRateByLecture(lectureNo);
	}
	
	// 강좌별 평가 정답률
	// 매개변수 : 강좌번호
	// 리턴값 : 강좌에 해당되는 문제별 정답률
	public List<Map<String, Object>> getTestAnswerRateByLecture(int lectureNo) {
		return teacherChartMapper.selectTestAnswerRateByLecture(lectureNo);
	}
}
