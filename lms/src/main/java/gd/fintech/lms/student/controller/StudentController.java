package gd.fintech.lms.student.controller;

//학생의 정보에대한 컨트롤러

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gd.fintech.lms.account.vo.Account;
import gd.fintech.lms.account.vo.License;
import gd.fintech.lms.dto.StudentForm;
import gd.fintech.lms.student.service.StudentService;
import gd.fintech.lms.student.vo.AccountImage;
import gd.fintech.lms.student.vo.Student;

@Controller
public class StudentController {
	Logger logger = LoggerFactory.getLogger(StudentController.class);
	@Autowired StudentService studentService;
	
	//학생 자신의 정보 상세보기
	//매개변수
	//리턴값:
	@GetMapping("/student/studentDetail")
	public String getStudentDetail(Model model,HttpServletRequest request) {
		//세션 가져오기
		HttpSession session =((HttpServletRequest)request).getSession();
		//Id를 가져오기
		String accountId =(String)session.getAttribute("accountId");
		Map<String,Object> map= studentService.getStudentDetail(accountId);
		Map<String,Object> licenseMap = studentService.getLicensList(accountId); 
		model.addAttribute("licenseMap",licenseMap);
		model.addAttribute("accountId", accountId);
		model.addAttribute("map",map);
		return "/student/studentDetail";
	}	
	
	//학생정보 수정 폼
	//매개변수:
	//리턴값:
	@GetMapping("student/modifyStudent")
	public String getStudentModify(Model model,HttpServletRequest request) {
		
		//세션 가져오기
		HttpSession session =((HttpServletRequest)request).getSession();
		//Id를 가져오기
		String accountId =(String)session.getAttribute("accountId");
		Map<String,Object> map = studentService.getStudentDetail(accountId);
		//Student student= studentService.getStudentDetail(accountId);
		AccountImage myImage = studentService.getStudentImage(accountId);
		
		model.addAttribute("myImage",myImage);
		model.addAttribute("session",session);
		model.addAttribute("accountId",accountId);
		model.addAttribute("map",map);
		return "/student/modifyStudent";
	}
	
	//학생정보 수정 액션
	//매개변수:
		//리턴값:
	@PostMapping("student/modifyStudent")
	public String getStudentModify(StudentForm studentForm,HttpServletRequest request) {
		//세션 가져오기
		HttpSession session =((HttpServletRequest)request).getSession();
		//Id를 가져오기
		String accountId =(String)session.getAttribute("accountId");
		studentService.modifyStudent(studentForm, session, accountId);
		logger.debug(studentForm+"학생의 폼값");
		return "redirect:/student/studentDetail";
	}
	
	//학생 이미지 삭제
	//매개변수:
		//리턴값:
	@GetMapping("student/removeStudentFile")
	public String removeStudentFile(Model model,
			@RequestParam(value="accountId")String accountId,
			HttpServletRequest request) {
		AccountImage accountImage = studentService.getStudentImage(accountId);
		return "redirect:/student/modifyStudent";
	}
	
	//학생 비밀번호 수정 폼
	//매개변수:
		//리턴값:
	@GetMapping("student/modifyStudentPw")
	public String modifyStudentPw(HttpServletRequest request,Model model) {
		//세션 가져오기
		HttpSession session =((HttpServletRequest)request).getSession();
		//Id를 가져오기
		String accountId =(String)session.getAttribute("accountId");
		model.addAttribute("accountId",accountId);
		return "student/modifyStudentPw";
	}
	
	//학생 비밀번호 수정 액션
	//매개변수:
		//리턴값:
	@PostMapping("student/modifyStudentPw")
	public String modifyStduentPw(Account account) {
		studentService.modifyStudentPw(account);
		return "redirect:/logout";
	}
	
	//학생 자격증 추가 폼
	//매개변수:Session의 id
	//리턴값:자격증 생성폼으로 이동
	@GetMapping("/student/createStudentLicense")
	public String createStudentLicense(HttpSession session,Model model) {
		model.addAttribute("accountId",session.getAttribute("accountId"));
		return "student/createStudentLicense";
	}
	//학생 자격증 추가 액션
	//매개변수:자격증vo
	//리턴값:추가되는 학생의 자격증 행 
	@PostMapping("/student/createStudentLicense")
	public String createStudentLicense(License license) {
		studentService.createLicense(license);
		return "redirect:/student/studentDetail";
	}
	//학생 자격증 삭제
	//매개변수:자격증 고유번호
	//리턴값:삭제되는 학생의 자격증 행
	@GetMapping("/student/removeLicense")
	public String removeLicense(
			@RequestParam(value="licenseNo")int licenseNo){
		studentService.removeLicense(licenseNo);
		return "redirect:/student/studentDetail";
	}
}
