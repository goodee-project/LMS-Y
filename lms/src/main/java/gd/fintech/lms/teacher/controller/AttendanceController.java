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
	
	//년/월/일에 출석 상세보기 메서드
	//매개변수:map.put에 강좌별 번호,년도,월,일을 넣음
	//리턴값:강좌,년,월,일에 따라 띄우는 상세보기 뷰 페이지
	@GetMapping("/teacher/attendanceList")
	public String calendarAttendanceList(Model model,
			@RequestParam(value = "lectureNo")int lectureNo,
			@RequestParam(value = "target",required = false) String target,		//pre, next
			 @RequestParam(value = "currentYear",required = false,defaultValue = "-1")Integer currentYear,
				@RequestParam(value = "currentMonth",required = false,defaultValue = "-1")Integer currentMonth,
				@RequestParam(value = "currentDay",required = false,defaultValue = "-1")Integer currentDay) {
		
		
		 
			Calendar targetDay = Calendar.getInstance();
			//이코드가 값이 null일 경우 넣어주는
			if(currentYear != -1) {//사용자가 직접 년도를 입력했을 때
				//targetDay를 사용자가 직접 입력한 년도로 바꿈
				targetDay.set(Calendar.YEAR,currentYear);
				
				//0일이나 32일에 대한 대처 불가능
				//제작은 가능하나 불필요한 코드가 많이 씀
				//currentYear = Calendar.getInstance().get(Calendar.YEAR);//년 
			}
			if(currentMonth != -1) {
				//targetDay는 월 계산은 0~11까지니까 currentMonth는 1~12까지 받아오니까 -1로 해줘서 맞춤
				targetDay.set(Calendar.MONTH, currentMonth-1);
				//0일이나 32일에 대한 대처 불가능
				//제작은 가능하나 불필요한 코드가 많이 씀
				//currentMonth = Calendar.getInstance().get(Calendar.MONTH);//월
			}
			if(currentDay != -1) {
				
				//0이면 이전달 마지막일 , 32일이면 다음달 일
				//2월에는 29일 적었을때 다음날 3월1일 바뀜
				targetDay.set(Calendar.DATE, currentDay);
				//0일이나 32일에 대한 대처 불가능
				//제작은 가능하나 불필요한 코드가 많이 씀
				//currentDay = Calendar.getInstance().get(Calendar.DATE);//일
			}
			//Logger 디버깅
			logger.debug("targetDay  ----->"+targetDay.toString());
			if(target.equals("pre")){
				targetDay.set(Calendar.DATE, targetDay.get(Calendar.DATE)-1);//-
			}else if(target.equals("next")) {
				targetDay.set(Calendar.DATE, targetDay.get(Calendar.DATE)+1);//+
			}

			List<Attendance> attendance = attendanceService.getCalendarAttendanceList(lectureNo,targetDay.get(Calendar.YEAR),targetDay.get(Calendar.MONTH)+1,targetDay.get(Calendar.DATE));
			//Logger 디버깅
			logger.debug("attendance 상세보기 뷰----->"+attendance);
			//model을 통해 View에 다음과 같은 정보들을 보내준다
			model.addAttribute("lectureNo",lectureNo);
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
			@RequestParam(name = "currentYear",required = false)int currentYear,
			@RequestParam(name = "currentMonth",required = false)int currentMonth,
			@RequestParam(name = "currentDay",required = false)int currentDay) {
		
		List<Attendance> attendanceStateList = attendanceService.getAttendanceStudentOne(lectureNo, accountId, studentName, studentGender, attendanceDay);
		//Logger 디버깅
		logger.debug("attendanceStateList 출석 입력 폼-->"+attendanceStateList);
		Attendance attendanceList = attendanceService.getAttendanceOne(lectureNo, accountId, attendanceDay);
		//Logger 디버깅
		logger.debug("attendanceList 출석 수정 폼-->"+attendanceList);
		//model을 통해 View에 다음과 같은 정보들을 보내준다
		model.addAttribute("lectureNo",lectureNo);
		model.addAttribute("attendanceList",attendanceList);
		model.addAttribute("attendanceStateList",attendanceStateList);;
		model.addAttribute("currentYear", currentYear);
		model.addAttribute("currentMonth", currentMonth);
		model.addAttribute("currentDay", currentDay);
		return "/teacher/createAttendance";
	}
	
	//학생 출석 상태 입력 액션 메서드
	//매개변수:Attendance
	//리턴값:출석달력 페이지로 이동
	@PostMapping("/teacher/createAttendance")
	public String createAttendance(Attendance attendance) {
		// 오늘날짜
		Calendar cal = Calendar.getInstance();
		//현재 년도
		int currentYear = cal.get(Calendar.YEAR);
		//현재 월
		int currentMonth = cal.get(Calendar.MONTH)+1;
		//현재 일
		int currentDay = cal.get(Calendar.DATE);
		attendanceService.createAttendance(attendance);
		return "redirect:/teacher/attendanceList?lectureNo="+attendance.getLectureNo()+"&&target=weekDay"+"&&currentYear="+currentYear+"&&currentMonth="+currentMonth+"&&currentDay="+currentDay;
	}
	
	
	//출석부 학생 출석상태 수정 폼
	//매개변수:강좌번호,학생 아이디,학생 이름,학생 성별,출석 날짜,오늘 년도,오늘 월,오늘 일
	//리턴값:출석부 수정상세보기 폼
	@GetMapping("/teacher/modifyAttendanceOne")
	public String modifyAttendanceStateOne(Model model, 
			@RequestParam(value="lectureNo")int lectureNo,
			@RequestParam(value = "accountId")String accountId,
			@RequestParam(value = "studentName")String studentName,
			@RequestParam(value="studentGender")String studentGender,
			@RequestParam(value = "attendanceDay") String attendanceDay,
			@RequestParam(name = "currentYear",required = false)int currentYear,
			@RequestParam(name = "currentMonth",required = false)int currentMonth,
			@RequestParam(name = "currentDay",required = false)int currentDay) {
		List<Attendance> attendanceStateList = attendanceService.getAttendanceStudentOne(lectureNo, accountId, attendanceDay, studentName,studentGender);
		//Logger 디버깅
		logger.debug("attendanceStateList 출석 수정 폼-->"+attendanceStateList);
		Attendance attendanceList = attendanceService.getAttendanceOne(lectureNo, accountId, attendanceDay);
		//Logger 디버깅
		logger.debug("attendanceList 출석 수정 폼-->"+attendanceList);
		//모델을 통해 뷰에 넘거져줌
		model.addAttribute("lectureNo",lectureNo);
		model.addAttribute("attendanceList",attendanceList);
		model.addAttribute("attendanceStateList",attendanceStateList);
		model.addAttribute("currentYear", currentYear);
		model.addAttribute("currentMonth", currentMonth);
		model.addAttribute("currentDay", currentDay);
		return "teacher/modifyAttendanceOne";
	}

	// 출석부 학생 출석상태 수정 액션
	//매개변수:Attendance
	//리턴값:오늘 날짜에 맞는 학생 출석목록
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