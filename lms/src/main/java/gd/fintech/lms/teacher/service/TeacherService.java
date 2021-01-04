package gd.fintech.lms.teacher.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.ranges.RangeException;

import gd.fintech.lms.FilePath;
import gd.fintech.lms.account.mapper.AddressMapper;
import gd.fintech.lms.account.vo.Address;
import gd.fintech.lms.dto.TeacherForm;
import gd.fintech.lms.teacher.mapper.TeacherMapper;
import gd.fintech.lms.teacher.vo.AccountImage;
import gd.fintech.lms.teacher.vo.Teacher;
import jdk.internal.org.jline.utils.Log;

//강사 자신의 정보 수정 및 상세보기 할 수 있는 서비스

@Service
@Transactional
public class TeacherService {
	//Logger
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//TeacherMapper 객체 주입
	@Autowired private TeacherMapper teacherMapper;
	//AddressMapper 객체 주입
	@Autowired private AddressMapper addressMapper;
	
	//강사 아이디를 조회하여 정보결과를 가져오는 메서드
	//매개변수:로그인뷰에 넣은 계정 ID
	//리턴값:로그인한 계정 ID에 값이 있는 ID에 관련된 정보를 반환
	public Map<String, Object> getTeacherOne(String accountId){
		Teacher teacher = teacherMapper.selectTeacherOne(accountId);
		  
		StringBuilder uri = null;
	if (teacherMapper.selectMyImage(accountId) != null) {
		try {
		    // 소스 파일 불러오기
		    File file = new File(FilePath.getFilePath()+teacher.getTeacherImage());

		    // 파일 컨텐츠타입 설정
		    String contentType = teacherMapper.selectMyImage(accountId).getImageFileType();

		    // 바이트 배열로 파일 불러오기
		    byte[] data = Files.readAllBytes(file.toPath());

		    // 베이스64 문자열로 변환하기 (자바 8버전)
		    String base64str = Base64.getEncoder().encodeToString(data);

		    // "data URI" 생성
		    uri = new StringBuilder();
		    uri.append("data:");
		    uri.append(contentType);
		    uri.append(";base64,");
		    uri.append(base64str);
		} catch (IOException e) { // 파일 로드에 실패했을 경우 Transactional 애노테이션에게 알려줌
		    e.printStackTrace();
		    throw new RuntimeException(e);
		}
		  
	}
		
		Map<String,Object> map = new HashMap<>();
		// uri를 Controller로 리턴시켜주기
		map.put("imageURI", uri);
		map.put("teacher",teacher);
		
		return map;

	}
	
	//강사 이미지를 조회
	public AccountImage selectMyImage(String accountId) {
		return teacherMapper.selectMyImage(accountId);
	}
	
	//강사 아이디를 조회아여 정보를 수정하게 해주는 메서드
	//매개변수:로그인뷰에 넣은 계정 ID
	//리턴값:수정된 값
	public boolean modifyTeacherOne(TeacherForm teacherForm,HttpSession session,String accountId) {
		
		//teacherForm의 강사 아이디,이메일,이름,폰번,성별,생일,주소 내용을 객체에 넣어둠
		Teacher teacher = new Teacher();
		teacher.setAccountId(teacherForm.getAccountId());
		teacher.setTeacherEmail(teacherForm.getTeacherEmail());
		teacher.setTeacherName(teacherForm.getTeacherName());
		teacher.setTeacherPhone(teacherForm.getTeacherPhone());
		teacher.setTeacherGender(teacherForm.getTeacherGender());
		teacher.setTeacherBirth(teacherForm.getTeacherBirth());
		teacher.setTeacherAddressMain(teacherForm.getTeacherAddressMain());
		teacher.setTeacherAddressSub(teacherForm.getTeacherAddressSub());
		teacher.setTeacherInfo(teacherForm.getTeacherInfo());
		logger.debug(teacher.toString());
		teacherMapper.updateTeacherInfo(teacher);
	
		
		//파일이 있을 경우 for문을 돌면서 Multipartfile을 vo로 변환 후 첨부파일 추가
		if(teacherForm.getImageFileList() !=null) {
			//기존에 올라와 있던 사진에 대한 정보를 불러옴
			teacherMapper.selectMyImage(accountId);
			teacherMapper.selectTeacherImageanddelete(accountId);
			
			for(MultipartFile mf : teacherForm.getImageFileList()) {
				String fileNameUUID = UUID.randomUUID().toString().replace("-","");
				
				try {
					//물리적 파일을 생성(하드디스크)
					String fileName = FilePath.getFilePath()+fileNameUUID;
					mf.transferTo(new File(fileName));
					
					logger.debug("파일 생성됨:"+fileName);
				}catch(Exception e) {
					//해당 파일 생성 실패시
					//예외 메세지를 출력
					e.printStackTrace();
					
					//Transactional 기능을 수행하는 Service 컴포넌트에게 예외 발생을 알려 작업 내역을 롤백하도록 유도함
					throw new RuntimeException(e);
				}
			
				session.setAttribute("teacherImage", fileNameUUID);
				AccountImage accountImage = new AccountImage();
				accountImage.setImageFileOriginal(teacher.getTeacherImage());
				accountImage.setAccountId(teacher.getAccountId());
				accountImage.setImageFileUUID(fileNameUUID);
				accountImage.setImageFileSize(mf.getSize());
				accountImage.setImageFileOriginal(mf.getOriginalFilename());
				accountImage.setImageFileType(mf.getContentType());
				//teacherMapper.insertTeacherImage(accountImage);
				//teacherMapper.updateTeacherImage(accountId, fileNameUUID);
					//강사 이미지 조회 NULL값 일시
					if(teacherMapper.selectMyImage(accountId) == null) {
						teacherMapper.updateTeacherImage(accountId, fileNameUUID); 
						teacherMapper.insertTeacherImage(accountImage);
					}
				}
			 }
		return true;
	}
	
	//강사ID에 해당되는 이미지파일을 삭제
	//매개변수:삭제할 강사ID
	public void removeFile(String accountId) {
		//물리적 파일 제거
		String fileName = FilePath.getFilePath()+accountId;
		
		//파일 경로, 이름지정
		File file = new File(fileName);
		//파일이 있는경우
		if(file.exists()) {
			file.delete();
		}
		
		teacherMapper.updateTeacherImage(accountId, fileName);
		teacherMapper.deleteMyImage(accountId);
	}
	
	//강사 자신의 이미지
	public AccountImage getTeacherImageFile(String accountId) {
		return teacherMapper.selectMyImage(accountId);
	}

	//우편주소로 주소 리스트를 조회 메서드
	//매개변수 :우편주소
	//리턴값:우편주소에 따른 주소 목록
	public List<String> getAddressListByZipcode1(String zipCode){
		List<String> list = addressMapper.selectAddressByZipCode(zipCode);
		return list;
	}
	
}
