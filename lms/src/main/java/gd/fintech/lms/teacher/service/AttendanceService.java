package gd.fintech.lms.teacher.service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gd.fintech.lms.teacher.mapper.AttendanceMapper;
import gd.fintech.lms.teacher.vo.Attendance;

//강사가 관리하는 출석 서비스

@Service
@Transactional
public class AttendanceService {
	// Looger
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
		
	//AttendanceMapper 객체 추입
	@Autowired private  AttendanceMapper attendanceMapper;

	// 년/월별 출석 상세보기 메서드
	//매개변수:map.put에 강좌별 번호,년도,월,일을 넣음
	//리턴값:map값을 리턴
	public List<Attendance> getCalendarAttendanceList(int lectureNo,int currentYear,int currentMonth,int currentDay){
		Calendar targetDay = Calendar.getInstance();
		targetDay.set(Calendar.YEAR,currentYear);
		targetDay.set(Calendar.MONTH, currentMonth-1);
		targetDay.set(Calendar.DATE, currentDay);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("lectureNo", lectureNo);
		map.put("currentYear", targetDay.get(Calendar.YEAR));
		map.put("currentMonth", targetDay.get(Calendar.MONTH)+1);
		map.put("currentDay", targetDay.get(Calendar.DATE));
		
		List<Attendance> attendance = attendanceMapper.selectAttendanceStudentList(map);
		logger.trace("attendance 서비스 상세보기 메서드---->"+attendance);
		return attendance;
	}
	//출석에 필요한 학생 상세보기
	public List<Attendance> getAttendanceStudentOne(int lectureNo,String accountId,String studentName,String studentGender, String attendanceDay){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("lectureNo",lectureNo);
		map.put("accountId",accountId);
		map.put("studentName",studentName);
		map.put("studentGender",studentGender);
		return attendanceMapper.selectAttendanceStudentOne(map);

	}
	//출석 상세보기
	public Attendance getAttendanceOne(int lectureNo, String accountId, String attendanceDay) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("lectureNo", lectureNo);
		map.put("accountId", accountId);
		map.put("attendanceDay", attendanceDay);
		
		return attendanceMapper.selectAttendanceOne(map);
	}
	
	//학생 출석 상태 입력
	public int createAttendance(Attendance attendance) {

		 return attendanceMapper.insertAttendance(attendance);
	}
	
	//학생 출석 상태 수정
	public int moidifyAttendance(Attendance attendance) {
		 
		return attendanceMapper.updateAttendance(attendance);
	}
}

