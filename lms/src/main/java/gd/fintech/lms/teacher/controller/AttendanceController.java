package gd.fintech.lms.teacher.controller;

import java.util.Calendar;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gd.fintech.lms.teacher.service.AttendanceService;
import gd.fintech.lms.teacher.vo.Attendance;

//강사가 관리하는 출석 컨트롤러

@Controller
public class AttendanceController {
	//Looger
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//AttendanceService 객체 주입
	@Autowired private AttendanceService attendanceService;
	
	//출석에 필요한 학생목록 메서드
	//매개변수:map.put에 강좌별 번호
	//리턴값:강좌,년,월에 따라 띄우는 뷰 페이지
	@GetMapping("/teacher/calendarAttendanceList")
	public String calendarAttendanceList(Model model,
			@RequestParam(value="lectureNo")int lectureNo) {
		//서비스 호출
		List<Attendance> attendanceList = attendanceService.getCalendarAttendanceList(lectureNo);
		
		//모델로 뷰 연결(년,월,마지막 일,1일의 요일,attendanceList) 넘김
		model.addAttribute("attendanceList",attendanceList);
		return "/teacher/calendarAttendanceList";
	}
	
	// 년/월/일에 출석 상세보기 메서드
	//매개변수:map.put에 강좌별 번호,년도,월,일을 넣음
	//리턴값:강좌,년,월,일에 따라 띄우는 상세보기 뷰 페이지
	@GetMapping("/teacher/calendarAttendanceListOne")
	public String calendarAttendanceListOne(Model model,
			@RequestParam(value = "lectureNo")int lectureNo,
			@RequestParam(name = "target") String target,		//pre, next
			@RequestParam(name = "currentYear",required = true)int currentYear,
			@RequestParam(name = "currentMonth",required = true)int currentMonth,
			@RequestParam(name = "currentDay",required = true)int currentDay) {
			
			Calendar targetDay = Calendar.getInstance();
			targetDay.set(Calendar.YEAR,currentYear);
			targetDay.set(Calendar.MONTH, currentMonth-1);
			targetDay.set(Calendar.DATE, currentDay);
			if(target.equals("pre")){
				targetDay.add(Calendar.DATE, -1);//-
			}else if(target.equals("next")) {
				targetDay.add(Calendar.DATE, +1);//+
			}
			
			List<Attendance> attendance = attendanceService.getCalendarAttendanceListOne(lectureNo,targetDay.get(Calendar.YEAR), targetDay.get(Calendar.MONTH)+1, targetDay.get(Calendar.DATE));
			logger.debug("attendance"+attendance);
			model.addAttribute("attendance", attendance);
			model.addAttribute("currentYear",targetDay.get(Calendar.YEAR));
			model.addAttribute("currentMonth",targetDay.get(Calendar.MONTH)+1);
			model.addAttribute("currentDay",targetDay.get(Calendar.DATE));
		return "/teacher/calendarAttendanceListOne";
		
	}
	
	//학생 출석 상태 입력 폼 메서드
	//매개변수:강좌별 고유번호,currentYear,currentMonth,currentDay
	//리턴값:출석상태 입력 폼 페이지
	@GetMapping("/teacher/createAttendance")
	public String createAttendance(Model model,
			@RequestParam(value="lectureNo")int lectureNo,
			@RequestParam(name = "currentYear",required = true)int currentYear,
			@RequestParam(name = "currentMonth",required = true)int currentMonth,
			@RequestParam(name = "currentDay",required = true)int currentDay) {
		List<Attendance> attendanceList = attendanceService.getCalendarAttendanceList(lectureNo);
		
		model.addAttribute("attendanceList",attendanceList);
		model.addAttribute("currentYear", currentYear);
		model.addAttribute("currentMonth", currentMonth);
		model.addAttribute("currentDay", currentDay);
		return "/teacher/createAttendance";
	}
	
	//학생 출석 상태 입력 액션 메서드
	@PostMapping("/teacher/createAttendance")
	public String createAttendance(Attendance attendance) {
		
		attendanceService.createAttendance(attendance);
		
		return "redirect:/teacher/calendarAttendanceListOne";
	}
		
}
	
