package gd.fintech.lms.manager.controller;


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
import gd.fintech.lms.account.vo.Career;
import gd.fintech.lms.account.vo.Education;
import gd.fintech.lms.account.vo.License;
import gd.fintech.lms.dto.ManagerForm;
import gd.fintech.lms.manager.service.ManagerService;
import gd.fintech.lms.manager.vo.AccountImage;

// 운영자 관련 컨트롤러 

@Controller
public class ManagerController {
   //Logger
   private final Logger logger = LoggerFactory.getLogger(ManagerController.class);
   // 운영자 정보 관련 서비스
   @Autowired private  ManagerService managerService;
   
   
   // 운영자 상세정보
   // 리턴값: accountId에 해당하는 운영자 상세정보
   @GetMapping("/manager/managerDetail")
   public String managerDetail(Model model, HttpServletRequest request) {
      HttpSession session = ((HttpServletRequest)request).getSession();
      // 세션에 있는 id를 가져온다
      String accountId = (String)session.getAttribute("accountId");
      Map<String,Object> map = managerService.getManagerDetail(accountId);
      Map<String,Object> paramMap = managerService.getManagerInforOne(accountId);
      model.addAttribute("accountId", accountId);
      model.addAttribute("map",map);
      model.addAttribute("paramMap", paramMap);
       logger.debug("map"+ map );
      return "/manager/managerDetail";
   }
   
   // 운영자 정보 수정 폼
   // 매개변수: model
   // 리턴값: 운영자 정보 수정 액션
   @GetMapping("/manager/modifyManager")
   public String modifyManager(Model model, HttpServletRequest request) {
      HttpSession session = ((HttpServletRequest)request).getSession();
      // 세션에 있는 id를 가져온다
      String accountId = (String)session.getAttribute("accountId");
      Map<String, Object> map = managerService.getManagerDetail(accountId);
      AccountImage myImage = managerService.selectMyImage(accountId);
      model.addAttribute("ImageFileUUID", managerService.getImageFileUUIDCk(accountId));
      model.addAttribute("session", session);
      model.addAttribute("accountId", accountId);
      model.addAttribute("map",map);
      model.addAttribute("myImage",myImage);
      logger.debug("session"+ session);
      logger.debug("accountId"+ accountId);
      logger.debug("map"+ map );
      logger.debug("myImage"+ myImage);
      return "/manager/modifyManager";
   }
   
   // 운영자 정보수정 액션
   // 리턴값: 수정된 운영자 정보
   @PostMapping("/manager/modifyManager")
   public String modifyManager(ManagerForm managerForm, HttpServletRequest request) {
      HttpSession session = ((HttpServletRequest)request).getSession();
      // 세션에 있는 id를 가져온다
      String accountId = (String)session.getAttribute("accountId");
      if(managerService.getManagerDetail(accountId) != managerForm.getManagerImage()) {
    	  managerService.modifyManager(managerForm, session, accountId);
      }
      
      return "redirect:/manager/managerDetail";
   }
   
   // 운영자 이미지 삭제
   // 리턴값: 이미지가 삭제된 운영자 수정 폼
   @GetMapping("manager/removeManagerFile")
   public String removeManagerFile(Model model,
      @RequestParam(value="accountId")String accountId,HttpServletRequest request) {
      AccountImage accountImage = managerService.getManagerImageFIle(accountId);
      logger.debug("accountImage"+ accountImage);
      managerService.removeFIle(accountId);
      return "redirect:/manager/modifyManager";
      }
   
   // 운영자의 비밀번호 변경 폼
   // 리턴값: 운영자의 pw 수정 액션
   @GetMapping("/manager/modifyManagerPasswd")
   public String modifyManagerPasswd(Model model , HttpServletRequest sseion) {
      // 세션에서 계정 id를 가져옴
      HttpSession session =((HttpServletRequest)sseion).getSession();
      String accountId = (String)session.getAttribute("accountId");
      model.addAttribute("accountId", accountId);
      return"/manager/modifyManagerPasswd";
   }
   
   // 운영자의 비밀번호를 변경 액션
   // 매개변수: 계정 정보
   // 리턴값: 로그아웃
   @PostMapping("/manager/modifyManagerPasswd")
   public String modifyManagerPasswd(Account account) {
      managerService.modifyManagerPasswd(account);
      return "redirect:/logout";
   }
   
   // 운영자 경력추가 폼
   // 리턴값: 운영자 경력입력 페이지
   @GetMapping("/manager/createManagerCareer")
   public String createManagerCareer(HttpServletRequest request,Model model) {
 	  //세션정보 가져옴
 	  HttpSession session = ((HttpServletRequest)request).getSession();
 	  //세션에 있는 아이디 가져옴
 	  String accountId = (String)session.getAttribute("accountId");
 	  model.addAttribute("accountId",accountId);
 	  return "/manager/createManagerCareer";
 	}
 	
   // 운영자 경력추가 액션
   // 리턴값: 운영자  상세보기페이지
   @PostMapping("/manager/createManagerCareer")
   public String createManagerCareer(Career career) {
 		managerService.createManagerCareer(career);
 		return "redirect:/manager/managerDetail";
 	}
 	
   
   
   
 	// 운영자 경력삭제
   	// 매개 변수: 경력 고유번호
   	// 리턴값: 경력 삭제와 운영자 상세보기 페이지로 이동
 	@GetMapping("/manager/removeManagerCareer")
 	public String removeManagerCareer(HttpServletRequest request,
 		@RequestParam(value="careerNo")int careerNo) {
 		//세션정보 가져옴
 		HttpSession session = ((HttpServletRequest)request).getSession();
 		//세션에 있는 아이디 가져옴
 		String accountId = (String)session.getAttribute("accountId");
 		managerService.removeManagerCareer(careerNo, accountId);
 		return "redirect:/manager/managerDetail";
 	}
 	
 	// 운영자 자격증 추가 폼
 	// 매개변수:운영자ID
 	// 리턴값: 운영자 자격증 입력 페이지
 	@GetMapping("/manager/createManagerLicense")
 	public String createManagerLicense(HttpServletRequest request,Model model) {
 		//세션정보 가져옴
 		HttpSession session = ((HttpServletRequest)request).getSession();
 		//세션에 있는 아이디 가져옴
 		String accountId = (String)session.getAttribute("accountId");
 		model.addAttribute("accountId",accountId);
 		return "/manager/createManagerLicense";
 	}
 	// 운영자 자격증 추가 액션
 	// 매개변수: 자격증 정보
 	// 리턴값: 운영자 상세보기 페이지
 	@PostMapping("/manager/createManagerLicense")
 	public String createManagerLicense(License license) {
 		managerService.createManagerLicense(license);
 		return "redirect:/manager/managerDetail";
 	}
 	
 	// 운영자 자격증 삭제
 	// 리턴값: 자격증 삭제 운영자 상세보기페이지로 이동
 	@GetMapping("/manager/removeManagerLicense")
 	public String removeManagerLicense(HttpServletRequest request,
 		@RequestParam(value="licenseNo")int licenseNo) {
 		//세션정보 가져옴
 		HttpSession session = ((HttpServletRequest)request).getSession();
 		//세션에 있는 아이디 가져옴
 		String accountId = (String)session.getAttribute("accountId");
 		managerService.removeManagerLicense(licenseNo, accountId);
 		return "redirect:/manager/managerDetail";
 	}
 	
 	// 운영자 학력 추가 폼
 	// 매개변수: 운영자ID
 	// 리턴값: 운영자 학력 입력 페이지
 	@GetMapping("/manager/createManagerEducation")
 	public String createManagerEducation(HttpServletRequest request,Model model) {
 		//세션정보 가져옴
 		HttpSession session = ((HttpServletRequest)request).getSession();
 		//세션에 있는 아이디 가져옴
 		String accountId = (String)session.getAttribute("accountId");
 		model.addAttribute("accountId",accountId);
 		return "/manager/createManagerEducation";
 	}
 	
 	// 운영자 학력 추가 액션
 	// 학력 정보
 	// 리턴값: 운영자 상세보기 페이지
 	@PostMapping("/manager/createManagerEducation")
 	public String createManagerEducation(Education education) {
 		managerService.createManagerEducation(education);
 		return "redirect:/manager/managerDetail";
 	}
 	
 	
 	// 운영자 학력 삭제
 	// 학력 고유 번호
 	// 리턴값: 학력 삭제 운영자 상세보기페이지로 이동
 	@GetMapping("/manager/removeManagerEducation")
 	public String removeManagerEducation(HttpServletRequest request,
 		@RequestParam(value="educationNo")int educationNo) {
 		//세션정보 가져옴
 		HttpSession session = ((HttpServletRequest)request).getSession();
 		//세션에 있는 아이디 가져옴
 		String accountId = (String)session.getAttribute("accountId");
 		managerService.removeManagerEducation(educationNo, accountId);
 		return "redirect:/manager/managerDetail";
 	}
}