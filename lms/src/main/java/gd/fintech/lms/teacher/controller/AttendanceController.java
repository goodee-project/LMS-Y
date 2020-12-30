package gd.fintech.lms.teacher.controller;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gd.fintech.lms.student.service.ClassRegistrationService;
import gd.fintech.lms.student.service.StudentService;
import gd.fintech.lms.student.vo.ClassRegistration;
import gd.fintech.lms.student.vo.Student;
import gd.fintech.lms.teacher.service.AttendanceService;
import gd.fintech.lms.teacher.vo.Attendance;

//강사가 관리하는 출석 컨트롤러

@Controller
public class AttendanceController {
	//Looger
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//AttendanceService 객체 주입
	@Autowired private AttendanceService attendanceService;
	
	// 년/월/일에 출석 상세보기 메서드
	//매개변수:map.put에 강좌별 번호,년도,월,일을 넣음
	//리턴값:강좌,년,월,일에 따라 띄우는 상세보기 뷰 페이지
	@GetMapping("/teacher/attendanceList")
	public String calendarAttendanceList(Model model,
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

			
			List<Attendance> attendance = attendanceService.getCalendarAttendanceList(lectureNo,targetDay.get(Calendar.YEAR), targetDay.get(Calendar.MONTH)+1, targetDay.get(Calendar.DATE));
			logger.debug("attendance 상세보기 뷰----->"+attendance);
			model.addAttribute("attendance", attendance);
			model.addAttribute("currentYear",targetDay.get(Calendar.YEAR));
			model.addAttribute("currentMonth",targetDay.get(Calendar.MONTH)+1);
			model.addAttribute("currentDay",targetDay.get(Calendar.DATE));
		return "/teacher/attendanceList";
		
	}
	
	//학생 출석 상태 입력 폼 메서드
	//매개변수:강좌별 고유번호,currentYear,currentMonth,currentDay
	//리턴값:출석상태 입력 폼 페이지
	@GetMapping("/teacher/createAttendance")
	public String createAttendance(Model model,
			@RequestParam(value="lectureNo")int lectureNo,
			@RequestParam(value = "accountId")String accountId,
			@RequestParam(value = "attendanceDay") String attendanceDay,
			@RequestParam(value = "studentName")String studentName,
			@RequestParam(value="studentGender")String studentGender,
			@RequestParam(name = "currentYear",required = true)int currentYear,
			@RequestParam(name = "currentMonth",required = true)int currentMonth,
			@RequestParam(name = "currentDay",required = true)int currentDay) {

		List<Attendance> attendanceStateList = attendanceService.getAttendanceStudentOne(lectureNo, accountId, studentName, studentGender, attendanceDay);
		logger.debug("attendanceStateList 출석 입력 폼-->"+attendanceStateList);
		Attendance attendanceList = attendanceService.getAttendanceOne(lectureNo, accountId, attendanceDay);
		logger.debug("attendanceList 출석 수정 폼-->"+attendanceList);
		
		model.addAttribute("attendanceList",attendanceList);
		model.addAttribute("attendanceStateList",attendanceStateList);;
		model.addAttribute("currentYear", currentYear);
		model.addAttribute("currentMonth", currentMonth);
		model.addAttribute("currentDay", currentDay);
		return "/teacher/createAttendance";
	}
	
	//학생 출석 상태 입력 액션 메서드
	//리턴값:출석달력 페이지로 이동
	@PostMapping("/teacher/createAttendance")
	public String createAttendance(Attendance attendance) {
		// 오늘날짜
		Calendar cal = Calendar.getInstance();
		int currentYear = cal.get(Calendar.YEAR);
		int currentMonth = cal.get(Calendar.MONTH)+1;
		int currentDay = cal.get(Calendar.DATE);
		attendanceService.createAttendance(attendance);
		return "redirect:/teacher/attendanceList?lectureNo="+attendance.getLectureNo()+"&&target=weekDay"+"&&currentYear="+currentYear+"&&currentMonth="+currentMonth+"&&currentDay="+currentDay;
	}
	
	
	// 출석부 학생 출석상태 수정 폼
	@GetMapping("/teacher/modifyAttendanceOne")
	public String modifyAttendanceStateOne(Model model, 
			@RequestParam(value="lectureNo")int lectureNo,
			@RequestParam(value = "accountId")String accountId,
			@RequestParam(value = "studentName")String studentName,
			@RequestParam(value="studentGender")String studentGender,
			@RequestParam(value = "attendanceDay") String attendanceDay,
			@RequestParam(name = "currentYear",required = true)int currentYear,
			@RequestParam(name = "currentMonth",required = true)int currentMonth,
			@RequestParam(name = "currentDay",required = true)int currentDay) {

		
		
		List<Attendance> attendanceStateList = attendanceService.getAttendanceStudentOne(lectureNo, accountId, attendanceDay, studentName,studentGender);
		logger.debug("attendanceStateList 출석 수정 폼-->"+attendanceStateList);
		Attendance attendanceList = attendanceService.getAttendanceOne(lectureNo, accountId, attendanceDay);
		logger.debug("attendanceList 출석 수정 폼-->"+attendanceList);
		
		model.addAttribute("attendanceList",attendanceList);
		model.addAttribute("attendanceStateList",attendanceStateList);
		model.addAttribute("currentYear", currentYear);
		model.addAttribute("currentMonth", currentMonth);
		model.addAttribute("currentDay", currentDay);
		return "teacher/modifyAttendanceOne";
	}

	// 출석부 학생 출석상태 수정 액션
	@PostMapping("/teacher/modifyAttendanceOne")
	public String modifyAttendanceStateOne(Attendance attendance) {
		// 오늘날짜
		Calendar cal = Calendar.getInstance();
		int currentYear = cal.get(Calendar.YEAR);
		int currentMonth = cal.get(Calendar.MONTH)+1;
		int currentDay = cal.get(Calendar.DATE);
		attendanceService.moidifyAttendance(attendance);
		return "redirect:/teacher/attendanceList?lectureNo="+attendance.getLectureNo()+"&&target=weekDay"+"&&currentYear="+currentYear+"&&currentMonth="+currentMonth+"&&currentDay="+currentDay;
	}

}
