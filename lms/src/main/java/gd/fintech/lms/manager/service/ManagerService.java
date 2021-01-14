package gd.fintech.lms.manager.service;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import gd.fintech.lms.FilePath;
import gd.fintech.lms.account.mapper.AddressMapper;
import gd.fintech.lms.account.vo.Account;
import gd.fintech.lms.dto.ManagerForm;
import gd.fintech.lms.manager.mapper.ManagerMapper;
import gd.fintech.lms.manager.vo.AccountImage;
import gd.fintech.lms.manager.vo.Manager;


// 운영자가 하는 업무를 위한 서비스

@Service
public class ManagerService {
   // Logger
   private final Logger logger = LoggerFactory.getLogger(this.getClass());
      
   // 운영자정보 mapper 
   @Autowired private ManagerMapper managerMapper;
   
   // 주소정보 mapper
   @Autowired private AddressMapper addressMapper;
   
   // 운영자 정보 상세보기
   // 매개변수: 운영자 id
   // 리턴값: 운영자 정보를 출력
   public Map<String,Object> getManagerDetail(String accountId){
         
      // 운영자 정보 
      Manager manager=managerMapper.selectManagerOne(accountId);
         
      StringBuilder uri = null;
      if(managerMapper.selectManagerImage(accountId) != null){
         try {
            // 파일의 경로 소스
            File file = new File(FilePath.getFilePath()+manager.getManagerImage());
            
            // 파일 타입 설정
            String contentType = managerMapper.selectManagerImage(accountId).getImageFileType();

            // 바이트 배열로 파일 부르기
            byte[] date =Files.readAllBytes(file.toPath());
   
            // 베이스64 문자열로 변환하기(자바 8버전)
            String base64str = Base64.getEncoder().encodeToString(date);
               
            // date URI 생성
             uri = new StringBuilder();
             uri.append("data:");
             uri.append(contentType);
             uri.append(";base64,");
             uri.append(base64str);
         } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
         }
      }
         Map<String,Object> map = new HashMap<>();
         //uri를 Controller로 리턴시키기
         map.put("imageURI",uri);
         map.put("manager", manager);
            
         return map;
   }
   // 운영자 정보 수정하기
   // 매개변수:운영자의 정보, 세션, 계정Id
   // 리턴값: 수정된 값
   public boolean modifyManager(ManagerForm managerForm,HttpSession session,String accountId) {
      Manager manager = new Manager();
      manager.setAccountId(managerForm.getAccountId());
      manager.setManagerEmail(managerForm.getManagerEmail());
      manager.setManagerName(managerForm.getManagerName());
      manager.setManagerPhone(managerForm.getManagerPhone());
      manager.setManagerGender(managerForm.getManagerGender());
      manager.setManagerBirth(managerForm.getManagerBirth());
      manager.setManagerPosition(managerForm.getManagerPosition());
      manager.setManagerAddressMain(managerForm.getManagerAddressMain());
      manager.setManagerAddressSub(managerForm.getManagerAddressSub());
      logger.debug(manager.toString());
      managerMapper.updateManager(manager);
         
      // 파일이 존재할 경우 for문을 돌면서 Multipartfile을 vo로 변환 후 첨부파일 추가
      if(managerForm.getImageFileList() !=null) {
         // 기존에 있던 사진 정보 불러오기
         managerMapper.selectManagerImage(accountId);
         managerMapper.selectManagerImageanddelete(accountId);

         for(MultipartFile mf : managerForm.getImageFileList()) {
            String fileNameUUID = UUID.randomUUID().toString().replace("-","");

            try {
               // 물리적 파일을 생성(하드 디스크)
               String fileName = FilePath.getFilePath()+fileNameUUID;
               mf.transferTo(new File(fileName));
         
            }catch(Exception e) {
               // 해당 파일 생성 실패시
               // 예외 메세지를 출력
               e.printStackTrace();

                // Transactional 기능을 수행하는 Service 컴포넌트에게 예외 발생을 알려 작업 내역을 롤백하도록 유도함
               throw new RuntimeException(e);
            }
            session.setAttribute("managerImage", fileNameUUID);
            AccountImage accountImage = new AccountImage();
            accountImage.setImageFileOriginal(manager.getManagerImage());
            accountImage.setAccountId(manager.getAccountId());
            accountImage.setImageFileUUID(fileNameUUID);
            accountImage.setImageFileSize(mf.getSize());
            accountImage.setImageFileOriginal(mf.getOriginalFilename());
            accountImage.setImageFileType(mf.getContentType());
            // 운영자 이미지 조회 null 일시
            if(managerMapper.selectManagerImage(accountId) == null) {
               managerMapper.insertManagerImage(accountImage);
               if(managerMapper.selectManagerImage(accountId)!=null) {
                  managerMapper.updateManagerImage(accountId, fileNameUUID);
               }
            }
         }
      }
      return true;
   }
      // 운영자 이미지
      // 매개변수: 운영자 id
      // 리턴값: 운영자 자신 이미지
      public AccountImage getManagerImage(String accountId) {
         return managerMapper.selectManagerImage(accountId);
      }
      
      // 운영자 이미지 제거
      // 매개변수: 운영자의 계정 id
      public void removeManagerImage(String accountId) {
         // 파일 제거
         String fileName = FilePath.getFilePath()+accountId;
         // 파일 경로 이름지정
         File file = new File(fileName);
         // 파일이 존재할시
         if(file.exists()) {
            file.delete();
         }
         managerMapper.deleteManagerImage(accountId);
      }
         
      
      
   // 운영자의 개인 정보를 수정하는 서비스 메소드 
   // 매개변수: 운영자 정보
   // 리턴값: 운영자의 수정된 정보 
   public int modifyManager(Manager manager) {
      return managerMapper.updateManager(manager);
   }
   
   // 우편주소로 주소 리스트를 조회 메서드
   // 매개변수: 우편주소 
   // 리턴값: 우편주소에 따른 주소목록
   public List<String> getAddressListByZipcode(String zipCode){
      List<String> list = addressMapper.selectAddressByZipCode(zipCode);
      return list;
   }
   
   // 운영자의 비밀번호 변경
   // 매개변수: 계정의 정보
   // 리턴값: 해당하는 행의 변경
   public void modifyManagerPasswd(Account account) {
      managerMapper.updateManagerPassword(account);
   }
      
   // 운영자의 변경전의 Id 및 password
   // 매개변수: 운영자의 id 및 password
   // 리턴값: 변경전의 id 와 password의 확인
   public String getManagerPassword(String accountId,String accountPw) {
      return managerMapper.selectManagerPassword(accountId, accountPw);
   }   
}