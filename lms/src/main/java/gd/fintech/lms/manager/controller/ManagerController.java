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
import gd.fintech.lms.dto.ManagerForm;
import gd.fintech.lms.manager.service.ManagerService;
import gd.fintech.lms.manager.vo.AccountImage;

// 운영자 관련 컨트롤러 

@Controller
public class ManagerController {
   //Logger
   private final Logger logger = LoggerFactory.getLogger(ManagerController.class);
   
   @Autowired private  ManagerService managerService;
   
   
   // 운영자 상세정보
    // 리턴값: accountId에 해당하는 운영자 상세정보
   @GetMapping("/manager/managerDetail")
   public String managerDetail(Model model, HttpServletRequest sseion) {
      HttpSession session = ((HttpServletRequest)sseion).getSession();
      // 세션에 있는 id를 가져온다
      String accountId = (String)session.getAttribute("accountId");
      Map<String,Object> map = managerService.getManagerDetail(accountId);
      model.addAttribute("accountId", accountId);
      model.addAttribute("map",map);
       logger.debug("map"+ map );
      return "/manager/managerDetail";
   }
   
   // 운영자 정보 수정 폼
   // 리턴값: 운영자 정보 수정 액션
   @GetMapping("/manager/modifyManager")
   public String modifyManager(Model model, HttpServletRequest sseion) {
      HttpSession session = ((HttpServletRequest)sseion).getSession();
      // 세션에 있는 id를 가져온다
      String accountId = (String)session.getAttribute("accountId");
      Map<String, Object> map = managerService.getManagerDetail(accountId);
      AccountImage myImage = managerService.getManagerImage(accountId);
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
      managerService.modifyManager(managerForm, session, accountId);
      return "redirect:/manager/managerDetail";
   }
   
   // 운영자 이미지 삭제
   @GetMapping("manager/removeManagerFile")
   public String removeStudentFile(Model model,
      @RequestParam(value="accountId")String accountId,HttpServletRequest request) {
      AccountImage accountImage = managerService.getManagerImage(accountId);
      logger.debug("accountImage"+ accountImage);
      return "redirect:/manager/modifyManager";
      }
   
   // 운영자의 비밀번호를 변경 폼
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
   // 리턴값: 로그아웃
   @PostMapping("/manager/modifyManagerPasswd")
   public String modifyManagerPasswd(Account account) {
      managerService.modifyManagerPasswd(account);
      return "redirect:/logout";
   }
}