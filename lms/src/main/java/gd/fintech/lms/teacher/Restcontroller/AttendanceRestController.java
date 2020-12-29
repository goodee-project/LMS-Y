package gd.fintech.lms.teacher.Restcontroller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gd.fintech.lms.teacher.service.AttendanceService;

@RestController
public class AttendanceRestController {
	// Looger
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	// AttendanceService 객체 주입
	@Autowired
	private AttendanceService attendanceService;

	// 출석 입력
	// 매개변수:강좌고유번호,년,월,일,학생아이디,출석상태
	// 리턴값:
	/*@GetMapping("/teacher/createAttendance")
	public void createAttendance(@RequestParam(name = "lectureNo") int lectureNo,
			@RequestParam(name = "currentYear") int currentYear, 
			@RequestParam(name = "currentMonth") int currentMonth,
			@RequestParam(name = "currentDay") int currentDay, 
			@RequestParam(name = "accountId") String accountId,
			@RequestParam(value = "attendanceState") String attendanceState,
			@RequestParam(value = "attendanceRemark", defaultValue = "") String attendanceRemark) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("lectureNo", lectureNo);
		map.put("accountId", accountId);
		map.put("attendanceState", attendanceState);
		if (attendanceRemark.equals("")) {
			map.put("attendanceRemark", null);
		} else {
			map.put("attendanceRemark", attendanceRemark);
		}
		
		attendanceService.createAttendance(map);
	}
	
	//출석 수정
	//매개변수:강좌고유번호,년,월,일,학생아이디,출석상태
	//리턴값:
	@GetMapping("/teacher/modifyAttendance")
	public void modifyAttendance(@RequestParam(name = "lectureNo") int lectureNo, 											// 강좌 고유번호
			@RequestParam(name = "currentYear") int currentYear, 										
			@RequestParam(name = "currentMonth") int currentMonth, 										
			@RequestParam(name = "currentDay") int currentDay, 											
			@RequestParam(name = "accountId") String accountId, 										
			@RequestParam(value = "attendanceState") String attendanceState, 							
			@RequestParam(value = "attendanceRemark", defaultValue = "") String attendanceRemark) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("lectureNo", lectureNo);					
		map.put("currentYear", currentYear);				
		map.put("currentMonth", currentMonth);				
		map.put("currentDay", currentDay);					
		map.put("accountId", accountId);					
		map.put("attendanceState", attendanceState);		
		if (attendanceRemark.equals("")) {
			map.put("attendanceRemark", null);				
		} else {
			map.put("attendanceRemark", attendanceRemark);	
		}
		
		attendanceService.moidifyAttendance(map);
	}*/
}
