package gd.fintech.lms.teacher.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import gd.fintech.lms.FilePath;
import gd.fintech.lms.dto.SyllabusForm;
import gd.fintech.lms.manager.mapper.ManagerMapper;
import gd.fintech.lms.manager.vo.Lecture;
import gd.fintech.lms.teacher.mapper.SyllabusFileMapper;
import gd.fintech.lms.teacher.mapper.SyllabusMapper;
import gd.fintech.lms.teacher.mapper.TeacherLectureMapper;
import gd.fintech.lms.teacher.vo.Syllabus;
import gd.fintech.lms.teacher.vo.SyllabusFile;

// 강의계획서 정보 관련 Service

@Service
@Transactional
public class SyllabusService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// 강의계획서 관련 Mapper
	@Autowired SyllabusMapper syllabusMapper;
	// 강의계획서 첨부파일 관련 Mapper
	@Autowired SyllabusFileMapper syllabusfileMapper;
	// 운영자 관련 Mapper
	@Autowired ManagerMapper managerMapper;
	// 강좌 관련 Mapper
	@Autowired TeacherLectureMapper lectureMapper;
	
	// 강의계획서를 출력하는 메소드
	// 매개변수: syllabusNo(강의계획서 고유번호)
	// 리턴값:
	// #1. 고유번호에 해당하는 강의계획서
	// #2. 강의계획서 작성자 이름
	public Map<String, Object> getSyllabusDetail(int lectureNo) {
		Syllabus syllabusDetail = syllabusMapper.selectSyllabusDetail(lectureNo);
		Lecture lectureDetail = lectureMapper.selectTeacherLectureOne(lectureNo);
		
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("syllabusDetail", syllabusDetail);
		returnMap.put("lectureDetail", lectureDetail);
		
		return returnMap;
	}
	
	// 강사가 강의계획서를 작성하는 메소드
	// 매개변수:
	// session
	// syllabus(강의계획서 정보)
	// lectureNo(강좌 고유번호)
	// 리턴값: 없음
	// 강의계획서 작성
	// 강좌를 담당하는 강사만 강의계획서 작성 가능
	public void createSyllabus(HttpSession session, int lectureNo, SyllabusForm syllabusForm) {
		// 현재 로그인한 강사의 아이디와 이름을 출력
		// session에서 accountId(아이디)를 출력
		String accountId = (String)session.getAttribute("accountId");
		// syllabusWriter(강의계획서 작성자)을 출력
		String syllabusWriter = syllabusMapper.selectTeacherName(accountId);
		
		// 강좌를 담당하는 강사의 아이디를 확인하기 위한 코드
		// 강좌 고유번호에 해당하는 강좌 정보를 출력
		Lecture lectureDetail = lectureMapper.selectTeacherLectureOne(lectureNo);
		// 강좌를 담당하는 강사 아이디를 teacherId로 출력
		String teacherId = lectureDetail.getAccountId();
		
		// teacherId(강좌를 담당하는 강사 아이디)와 accountId(현재 로그인 한 강사의 아이디)가 같으면 강의계획서 작성
		if(teacherId.equals(accountId)) {
			// syllabus 객체를 생성하여 DTO를 VO로 변환 후 강의계획서 정보 저장
			Syllabus syllabus = new Syllabus();
			// syllabus(강의계획서 정보)에 lectureNo(강의계획서 고유번호) 추가
			syllabus.setLectureNo(syllabusForm.getLectureNo());
			// syllabus(강의계획서 정보)에 accountId(아이디) 추가
			syllabus.setAccountId(accountId);
			// syllabus(강의계획서 정보)에 syllabusWriter(강의계획서 작성자) 추가
			syllabus.setSyllabusWriter(syllabusWriter);
			// syllabus(강의계획서 정보)에 syllabusContent(강의계획서 내용) 추가
			syllabus.setSyllabusContent(syllabusForm.getSyllabusContent());
			
			syllabusMapper.insertSyllabus(syllabus);
			
			// 첨부된 파일이 있을 경우 for문을 돌면서 MultipartFile을 VO로 변환 후 첨부파일 추가
			if(syllabusForm.getSyllabusFileList() != null) {
				for(MultipartFile mf: syllabusForm.getSyllabusFileList()) {
					String fileNameUUID = UUID.randomUUID().toString().replaceAll("-", "");
					
					try {
						//물리적 파일을 생성(하드디스크)
						String fileName = FilePath.getFilePath() + fileNameUUID;
						mf.transferTo(new File(fileName));
						
						logger.debug("강의계획서 첨부파일 생성됨 " + fileName);
					// 해당 파일 생성 실패 시
					}catch (Exception e) {
						// 원래 예외 메세지를 출력함
						e.printStackTrace();
						
						// Transactional 기능을 수행하는 Service 컴포넌트에게 예외 발생을 알려 작업 내역을 롤백하도록 유도함
						throw new RuntimeException(e);
					}
					
					// syllabusFile 객체를 생성하여 강의계획서 첨부파일 정보를 syllabusFile(강의계획서 첨부파일)에 저장
					SyllabusFile syllabusFile = new SyllabusFile();
					// 강의계획서 첨부파일 UUID 저장
					syllabusFile.setSyllabusFileUUID(fileNameUUID);
					// 강의계획서 첨부파일 원본이름 저장
					syllabusFile.setSyllabusFileOriginal(mf.getOriginalFilename());
					// 강의계획서 고유번호 저장
					syllabusFile.setLectureNo(syllabus.getLectureNo());
					// 강의계획서 첨부파일 크기 저장
					syllabusFile.setSyllabusFileSize(mf.getSize());
					// 강의계획서 첨부파일 타입 저장
					syllabusFile.setSyllabusFileType(mf.getContentType());
					
					syllabusfileMapper.insertSyllabusFile(syllabusFile);
					
					logger.debug("강의계획서 파일 첨부 성공");
				}
			}
			logger.debug("강의계획서 작성 성공");
		} else {
			logger.debug("강의계획서 작성 실패");
		}
	}
	
	// 강사가 강의계획서를 수정하는 메소드
	// 매개변수: syllabus(강의계획서 정보)
	// 리턴값: 없음
	// 강의계획서 수정
	public void modifySyllabus(HttpSession session, int lectureNo, SyllabusForm syllabusForm) {	
		// 현재 로그인한 강사의 아이디 출력
		// session에서 accountId(아이디)를 출력
		String accountId = (String)session.getAttribute("accountId");
		
		logger.debug("modifySyllabus 디버그 아이디 " + accountId);
		
		// 강좌를 담당하는 강사의 아이디를 확인하기 위한 코드
		// 강좌 고유번호에 해당하는 강좌 정보를 출력
		Lecture lectureDetail = lectureMapper.selectTeacherLectureOne(lectureNo);
		// 강좌를 담당하는 강사 아이디를 teacherId로 출력
		String teacherId = lectureDetail.getAccountId();
		
		// 강의계획서에 운영자 서명이 있는지 확인하는 코드
		// 강좌 고유번호에 해당하는 강의계획서 정보 출력
		Syllabus syllabusDetail = syllabusMapper.selectSyllabusDetail(lectureNo);
		// 강의계획서에 있는 운영자 서명을 managerSign로 출력
		String managerSign = syllabusDetail.getSyllabusManagerSign();
		
		// teacherId(강좌를 담당하는 강사 아이디)와 accountId(현재 로그인 한 강사의 아이디)가 같고, 운영자 서명이 없으면 강의계획서 수정
		if(teacherId.equals(accountId) && managerSign == null) {
			// syllabus 객체를 생성하여 DTO를 VO로 변환 후 강의계획서 정보 저장
			Syllabus syllabus = new Syllabus();
			// syllabus(강의계획서 정보)에 lectureNo(강의계획서 고유번호) 추가
			syllabus.setLectureNo(syllabusForm.getLectureNo());
			// syllabus(강의계획서 정보)에 syllabusContent(강의계획서 내용) 추가
			syllabus.setSyllabusContent(syllabusForm.getSyllabusContent());
			
			syllabusMapper.updateSyllabus(syllabus);
			
			// 첨부된 파일이 있을 경우 for문을 돌면서 MultipartFile을 VO로 변환 후 첨부파일 추가
			if(syllabusForm.getSyllabusFileList() != null) {
				for(MultipartFile mf: syllabusForm.getSyllabusFileList()) {
					String fileNameUUID = UUID.randomUUID().toString().replaceAll("-", "");
					
					try {
					//물리적 파일을 생성(하드디스크)
					String fileName = FilePath.getFilePath() + fileNameUUID;
					mf.transferTo(new File(fileName));
					
					logger.debug("강의계획서 첨부파일 생성됨 " + fileName);
					// 해당 파일 생성 실패 시
					}catch (Exception e) {
					// 원래 예외 메세지를 출력함
					e.printStackTrace();
					
					// Transactional 기능을 수행하는 Service 컴포넌트에게 예외 발생을 알려 작업 내역을 롤백하도록 유도함
					throw new RuntimeException(e);
					}
								
					// syllabusFile 객체를 생성하여 강의계획서 첨부파일 정보를 syllabusFile(강의계획서 첨부파일)에 저장
					SyllabusFile syllabusFile = new SyllabusFile();
					// 강의계획서 첨부파일 UUID 저장
					syllabusFile.setSyllabusFileUUID(fileNameUUID);
					// 강의계획서 첨부파일 원본이름 저장
					syllabusFile.setSyllabusFileOriginal(mf.getOriginalFilename());
					// 강의계획서 고유번호 저장
					syllabusFile.setLectureNo(syllabus.getLectureNo());
					// 강의계획서 첨부파일 크기 저장
					syllabusFile.setSyllabusFileSize(mf.getSize());
					// 강의계획서 첨부파일 타입 저장
					syllabusFile.setSyllabusFileType(mf.getContentType());
					
					syllabusfileMapper.insertSyllabusFile(syllabusFile);
					
					logger.debug("강의계획서 파일 첨부 성공");
				}
			}
			logger.debug("강의계획서 수정 성공");
		} else {
			logger.debug("강의계획서 수정 실패");
		}
	}
	
	// UUID에 해당하는 첨부파일을 삭제하는 메소드
	// 매개변수: syllabusFileUUID(강의계획서 첨부파일 UUID)
	// 리턴값: 없음
	// UUID에 해당하는 첨부파일 삭제
	public void removeSyllabusFile(String syllabusFileUUID) {
		// 물리적 파일(하드디스크에 존재하는 파일) 제거
		String fileName = FilePath.getFilePath() + syllabusFileUUID;
		File file = new File(fileName);
		if (file.exists()) {
			file.delete();
			
			logger.debug("첨부파일 제거됨: "+fileName);
		} else {
			logger.debug("첨부 파일이 존재하지 않음(제거 실패) "+fileName);
		}
		
		// DB의 강의계획서 첨부파일 정보 제거
		syllabusfileMapper.deleteSyllabusFile(syllabusFileUUID);
	}
	
	// 강의계획서에 첨부된 파일을 다운로드하는 메소드
	// 매개변수:
	// #1: request
	// #2: response
	// #3: syllabusFileUUID
	// 리턴값: 다운로드 성공 여부
	// 다운로드를 진행하기 위한 서블릿 리퀘스트 및 리스폰스 객체를 매개변수로 받아옴
	public boolean downloadQuestionCommentFile(HttpServletRequest request, HttpServletResponse response, String syllabusFileUUID) {
		// 서버에 업로드된 파일을 가져옴
		String fileName = FilePath.getFilePath() + syllabusFileUUID;
		File file = new File(fileName);
		
		// 파일 컨텐츠를 읽어오기 위한 스트림 객체
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		
		// 다운로드할 파일의 컨텐츠를 채워주기 위한 스트림 객체
		ServletOutputStream sos = null;
		
		try {
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			
			// 파일타입 및 원본 파일명을 받아오고, 브라우저별로 파일 이름이 제대로 인식되도록 처리함
			SyllabusFile syllabusFile = syllabusfileMapper.selectSyllabusFileList(syllabusFileUUID);
			String fileContentType = syllabusFile.getSyllabusFileType();
			String originalFileName = syllabusFile.getSyllabusFileOriginal();
			if (request.getHeader("user-agent").indexOf("MSIE") != -1 || request.getHeader("user-agent").indexOf("Trident") != -1) {
				originalFileName = URLEncoder.encode(originalFileName, "UTF-8").replaceAll("\\+", "%20");
			} else {
				originalFileName = new String(originalFileName.getBytes("UTF-8"), "ISO-8859-1");
			}
			
			// MIME 타입을 설정하고 첨부파일 형태로, 파일명은 originalFileName으로 설정함
			response.setContentType(fileContentType);
			response.setContentLength((int)file.length());
			response.setHeader("Content-Disposition", "attachment;filename=\"" + originalFileName + "\";");
			response.setHeader("Content-Transfer-Encoding", "binary");
			
			// 파일 컨텐츠를 작성하기 위해 스트림을 불러옴
			sos = response.getOutputStream();
			
			// 서버에 업로드된 파일의 내용을 읽어, 다운로드할 파일의 컨텐츠를 채워넣음
			int read = 0;
			while ((read = bis.read()) != -1) {
				sos.write(read);
			}
			
			// 파일 다운로드 횟수를 1 증가시킴
			syllabusfileMapper.updateSyllabusFileCountIncrease(syllabusFileUUID);
		} catch (Exception e) { // 파일 다운로드 실패 시
			// 원래 예외 메세지를 출력함
			e.printStackTrace();
			
			// Transactional 기능을 수행하는 Service 컴포넌트에게 예외 발생을 알려 작업 내역을 롤백하도록 유도함
			throw new RuntimeException(e);
		} finally {
			// 작업이 끝나는대로 스트림 객체의 리소스를 반환함
			try {
				sos.close();
				bis.close();
				fis.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return true;
	}
	
	// 강사가 강의계획서에 서명하는 메소드
	// 매개변수:
	// #1. syllabusNo(강의계획서 고유번호)
	// #2. syllabusTeacherSign(강사 서명)
	// 리턴값: 없음
	// 서명 여부를 확인하여 서명
	public void signSyllabusByTeacher(HttpSession session, int lectureNo) {
		// session에서 accountId(아이디)을 출력
		String accountId = (String)session.getAttribute("accountId");
		// accountId(아이디)로 서명할 이름 syllabusTeacherSign(운영자 서명)으로 출력
		String syllabusTeacherSign = syllabusMapper.selectTeacherName(accountId);
		
		// 강좌를 담당하는 강사의 아이디를 확인하기 위한 코드
		// 강좌 고유번호에 해당하는 강좌 정보를 출력
		Lecture lectureDetail = lectureMapper.selectTeacherLectureOne(lectureNo);
		// 강좌를 담당하는 강사 아이디를 teacherId로 출력
		String teacherId = lectureDetail.getAccountId();
		
		// 서명 여부를 확인하기 위한 코드
		// syllabusNo(강의계획서 고유번호)에 해당하는 syllabusDetail(강의계획서 정보) 출력 
		Syllabus syllabusDetail = syllabusMapper.selectSyllabusDetail(lectureNo);
		// syllabusDetail의 syllabusTeacherSign(강사 서명)를 TeacherSign(강사 서명)으로 출력
		String teacherSign = syllabusDetail.getSyllabusTeacherSign();
		// syllabusDetail의 syllabusTeacherSignDate(강사 서명일자)를 teacherSignDate(강사 서명일자)로 출력
		String teacherSignDate = syllabusDetail.getSyllabusTeacherSignDate();
		
		// 만약 강좌를 담당하는 강사 아이디와 현대 로그인 한 강사의 아이디가 같고, 강사 서명과 강사 서명일자가 없다면 서명
		if((teacherId.equals(accountId)) && (teacherSign == null && teacherSignDate == null)) {
			// syllabus(강의계획서 정보) 객체 생성
			Syllabus syllabus = new Syllabus();
			// syllabus에 lectureNo(강의계획서 고유번호) 저장
			syllabus.setLectureNo(lectureNo);
			// syllabus에 syllabusTeacherSign(강의계획서 강사 서명) 저장
			syllabus.setSyllabusTeacherSign(syllabusTeacherSign);
			// 강사가 강의계획서에 서명하는 Mapper 실행
			syllabusMapper.updateTeacherSign(syllabus);
			logger.debug("서명 성공");
		} else {
			logger.debug("서명 실패");
		}
	}

	// 운영자가 강의계획서에 서명하는 메소드
	// 매개변수:
	// #1. syllabusNo(강의계획서 고유번호)
	// #2. syllabusManagerSign(운영자 서명)
	// 리턴값: 없음
	// 서명 여부를 확인하여 서명
	public void signSyllabusByManager(HttpSession session, int lectureNo) {
		// session에서 accountId(아이디)을 출력
		String accountId = (String)session.getAttribute("accountId");
		// accountId(아이디)로 서명할 이름 syllabusManagerSign(운영자 서명)으로 출력
		String syllabusManagerSign = managerMapper.selectManagerName(accountId);
		
		// 서명 여부를 확인하기 위한 코드
		// syllabusNo(강의계획서 고유번호)에 해당하는 syllabusDetail(강의계획서 정보) 출력
		Syllabus syllabusDetail = syllabusMapper.selectSyllabusDetail(lectureNo);
		// syllabusDetail의 syllabusTeacherSign(강사 서명)를 TeacherSign(강사 서명)으로 출력
		String teacherSign = syllabusDetail.getSyllabusTeacherSign();
		// syllabusDetail의 syllabusTeacherSignDate(강사 서명일자)를 teacherSignDate(강사 서명일자)로 출력
		String teacherSignDate = syllabusDetail.getSyllabusTeacherSignDate();
		// syllabusDetail(강의계획서 정보)에서 syllabusManagerSign(운영자 서명)를 managerSign(운영자 서명)으로 출력
		String managerSign = syllabusDetail.getSyllabusManagerSign();
		// syllabusDetail(강의계획서 정보)에서 syllabusManagerSignDate(운영자 서명일자)를 managerSignDate(운영자 서명일자)로 출력
		String managerSignDate = syllabusDetail.getSyllabusManagerSignDate();
		
		// 만약 강사 서명과 강사 서명일자, 운영자 서명과 운영자 서명일자가 없다면 서명
		if((teacherSign != null && teacherSignDate != null) && (managerSign == null && managerSignDate == null)) {
			// syllabus(강의계획서 정보) 객체 생성
			Syllabus syllabus = new Syllabus();
			// syllabus에 lectureNo(강의계획서 고유번호) 저장
			syllabus.setLectureNo(lectureNo);
			// syllabus에 syllabusTeacherSign(강의계획서 운영자 서명) 저장
			syllabus.setSyllabusManagerSign(syllabusManagerSign);
			// 운영자가 강의계획서에 서명하는 Mapper 실행
			syllabusMapper.updateManagerSign(syllabus);
			logger.debug("서명 성공");
		} else {
			logger.debug("서명 실패");
		}
	}
}
