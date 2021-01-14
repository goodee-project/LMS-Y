package gd.fintech.lms.student.service;

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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.deser.Deserializers.Base;

import gd.fintech.lms.FilePath;
import gd.fintech.lms.account.mapper.AddressMapper;
import gd.fintech.lms.account.vo.Account;
import gd.fintech.lms.dto.StudentForm;
import gd.fintech.lms.student.mapper.StudentMapper;
import gd.fintech.lms.student.vo.AccountImage;
import gd.fintech.lms.student.vo.Student;

@Service
@Transactional
public class StudentService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired StudentMapper studentMapper;

	@Autowired AddressMapper addressMapper;
	//학생 자신의 정보 상세보기
	//매개변수:학생의 id
	//리턴값: 학생의 정보를 출력
	public Map<String,Object> getStudentDetail(String accountId){
		//학생의 정보
		Student student=studentMapper.selectStudentOne(accountId);
		
		StringBuilder uri = null;
		if(studentMapper.selectStudentImage(accountId) != null){
			try {
				//파일의 경로 소스
				File file = new File(FilePath.getFilePath()+student.getStudentImage());
				// 파일 타입 설정
				String contentType = studentMapper.selectStudentImage(accountId).getImageFileType();
				
				//바이트 배열로 파일 부르기
				byte[] date =Files.readAllBytes(file.toPath());
				
				//베이스64 문자열로 변환하기(자바 8버전)
				String base64str = Base64.getEncoder().encodeToString(date);
				
				//date URI 생성
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
			map.put("student", student);
			
			return map;
	}
	
	//학생 자신의 정보 수정하기
	//매개변수:학생의 정보
	//리턴값:수정된 값
	public boolean modifyStudent(StudentForm studentForm,HttpSession session,String accountId) {
		Student student = new Student();
		student.setAccountId(studentForm.getAccountId());
		student.setStudentEmail(studentForm.getStudentEmail());
		student.setStudentName(studentForm.getStudentName());
		student.setStudentPhone(studentForm.getStudentPhone());
		student.setStudentGender(studentForm.getStudentGender());
		student.setStudentBirth(studentForm.getStudentBirth());
		student.setStudentAddressMain(studentForm.getStudentAddressMain());
		student.setStudentAddressSub(studentForm.getStudentAddressSub());
		logger.debug(student.toString());
		studentMapper.updateStudent(student);
		
		//파일이 존재할 경우 for문을 돌면서 Multipartfile을 vo로 변환 후 첨부파일 추가
		if(studentForm.getImageFileList() !=null) {
			//기존에 있던 사진 정보 불러오기
			studentMapper.selectStudentImage(accountId);
			studentMapper.updateStudentImagePrevious(accountId);
			
			for(MultipartFile sf : studentForm.getImageFileList()) {
				String fileNameUUID = UUID.randomUUID().toString().replace("-","");
				
				try {
					//물리적 파일을 생성(하드 디스크)
					String fileName = FilePath.getFilePath()+fileNameUUID;
					sf.transferTo(new File(fileName));
					logger.debug("fileName"+fileName);
					
				}catch(Exception e) {
					//해당 파일 생성 실패시
					//예외 메세지를 출력
					e.printStackTrace();
					
					//Transactional 기능을 수행하는 Service 컴포넌트에게 예외 발생을 알려 작업 내역을 롤백하도록 유도함
					throw new RuntimeException(e);
				}
				session.setAttribute("studentImage", fileNameUUID);
				AccountImage accountImage = new AccountImage();
				accountImage.setImageFileOriginal(student.getStudentImage());
				accountImage.setAccountId(student.getAccountId());
				accountImage.setImageFileUUID(fileNameUUID);
				accountImage.setImageFileSize(sf.getSize());
				accountImage.setImageFileOriginal(sf.getOriginalFilename());
				accountImage.setImageFileType(sf.getContentType());
				studentMapper.insertStudentImage(accountImage);
				studentMapper.updateStudentImage(accountId, fileNameUUID);
				//학생 이미지 조회 null 일시
				if(studentMapper.selectStudentImage(accountId)==null) {
					
					if(studentMapper.selectStudentImage(accountId)!=null) {
						
					}
				}
			}
		}
		return true;
	}
	
	//우편주소로 조회하는 주소 리스트
	//매개변수:우편주소
	//리턴값:우편주소에 해당하는 값
	public List<String> getAddressListByZipCode(String zipCode){
		List<String> list = addressMapper.selectAddressByZipCode(zipCode);
		return list;
	}
	
	//학생이 볼 해당 강좌의 자신의 과제
	//매개변수:학생의 id
	//리턴값:학생에 해당하는 과제물 출력
	public List<Student> getReportDetail(String accountId) {
		return studentMapper.selectReportOne(accountId);
	}
	
	//학생의 이미지
	//매개변수:학생의 id
	//리턴값:학생 자신 이미지
	public AccountImage getStudentImage(String accountId) {
		return studentMapper.selectStudentImage(accountId);
	}
	
	//학생 이미지 제거
	//매개변수:학생의 id
	public void removeStudentImage(String accountId) {
		//파일 제거
		String fileName = FilePath.getFilePath()+accountId;
		//파일 경로 이름지정
		File file = new File(fileName);
		//파일이 존재할시
		if(file.exists()) {
			file.delete();
		}
		studentMapper.removeStudentImage(accountId);
	}
	
	//학생 비밃런호 변경
	//매개변수:학생의 id,pw
	//리턴값:변경된 비밀번호
	public void modifyStudentPw(Account account) {
		studentMapper.updateStudentPw(account);
	}
	
	//학생 id pw 조회
	//매개변수:
	//리턴값:id/pw 조회
	public String getStudentPw(String accountId,String accountPw) {
		return studentMapper.selectStudentPw(accountId, accountPw);
	}
}
