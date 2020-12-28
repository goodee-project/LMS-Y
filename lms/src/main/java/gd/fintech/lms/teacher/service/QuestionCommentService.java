package gd.fintech.lms.teacher.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
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
import gd.fintech.lms.dto.QuestionCommentForm;
import gd.fintech.lms.manager.mapper.LectureManagerMapper;
import gd.fintech.lms.manager.vo.Lecture;
import gd.fintech.lms.student.mapper.QuestionMapper;
import gd.fintech.lms.student.vo.Question;
import gd.fintech.lms.teacher.mapper.QuestionCommentFileMapper;
import gd.fintech.lms.teacher.mapper.QuestionCommentMapper;
import gd.fintech.lms.teacher.mapper.TeacherMapper;
import gd.fintech.lms.teacher.vo.QuestionComment;
import gd.fintech.lms.teacher.vo.QuestionCommentFile;

// 질문게시판 댓글을 관리하는 서비스

@Service
@Transactional
public class QuestionCommentService {
	// 디버깅 메세지 출력을 위한 로거
	Logger logger = LoggerFactory.getLogger(QuestionCommentService.class);
	
	// 검증 및 변수를 가져오는 데 사용하는 매퍼
	@Autowired private TeacherMapper teacherMapper;
	@Autowired private LectureManagerMapper lectureManagerMapper;
	@Autowired private QuestionMapper questionMapper;
	
	// 질문게시판 댓글 관리를 위한 매퍼
	@Autowired private QuestionCommentMapper questionCommentMapper;
	
	// 질문게시판 댓글 파일 관리를 위한 매퍼
	@Autowired private QuestionCommentFileMapper questionCommentFileMapper;
	
	// 질문게시판 댓글과 해당 질문의 상세정보 출력
	// 매개변수: 질문게시판 댓글 고유번호
	// 리턴값: question(질문 상세정보가 담긴 객체), questionComment(댓글 상세정보가 담긴 객체)
	public Map<String, Object> getQuestionCommentDetailAndQuestionDetail(int questionCommentNo) {
		QuestionComment questionComment = questionCommentMapper.selectQuestionCommentDetail(questionCommentNo);
		Question question = questionMapper.selectQuestionOne(questionComment.getQuestionNo());
		
		Map<String, Object> map = new HashMap<>();
		map.put("question", question);
		map.put("questionComment", questionComment);
		
		return map;
	}
	
	// 파일 다운로드
	// 매개변수:
	// #1: 다운로드받을 파일 UUID
	// #2, #3: 다운로드를 진행하기 위한 서블릿 리퀘스트 및 리스폰스 객체
	// 리턴값: 다운로드 성공 여부
	public boolean downloadQuestionCommentFile(String questionCommentFileUUID, HttpServletRequest request, HttpServletResponse response) {
		// 서버에 업로드된 파일을 가져옴
		String fileName = FilePath.getFilePath()+questionCommentFileUUID;
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
			QuestionCommentFile questionCommentFile = questionCommentFileMapper.selectQuestionCommentFileDetail(questionCommentFileUUID);
			String fileContentType = questionCommentFile.getQuestionCommentFileType();
			String originalFileName = questionCommentFile.getQuestionCommentFileOriginal();
			if (request.getHeader("user-agent").indexOf("MSIE") != -1 || request.getHeader("user-agent").indexOf("Trident") != -1) {
				originalFileName = URLEncoder.encode(originalFileName, "UTF-8").replaceAll("\\+", "%20");
			} else {
				originalFileName = new String(originalFileName.getBytes("UTF-8"), "ISO-8859-1");
			}
			
			// MIME 타입을 설정하고 첨부파일 형태로, 파일명은 originalFileName으로 설정함
			response.setContentType(fileContentType);
			response.setContentLength((int)file.length());
			response.setHeader("Content-Disposition", "attachment;filename=\""+originalFileName+"\";");
			response.setHeader("Content-Transfer-Encoding", "binary");
			
			// 파일 컨텐츠를 작성하기 위해 스트림을 불러옴
			sos = response.getOutputStream();
			
			// 서버에 업로드된 파일의 내용을 읽어, 다운로드할 파일의 컨텐츠를 채워넣음
			int read = 0;
			while ((read = bis.read()) != -1) {
				sos.write(read);
			}
			
			// 파일 다운로드 횟수를 1 증가시킴
			questionCommentFileMapper.updateQuestionCommentFileCountIncrease(questionCommentFileUUID);
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
	
	// DTO를 받아와 해당 질문게시판 게시글의 댓글을 생성
	// 매개변수: 
	// #1: 질문게시판 댓글 커맨드 객체 (MultipartFile 포함 가능)
	// #2: 로그인한 사용자가 올바른 접근을 했는지 검증하기 위한 세션 객체
	public boolean createQuestionComment(QuestionCommentForm questionCommentForm, HttpSession session) {
		// 현재 로그인한 사용자의 정보
		String sessionAccountId = (String)session.getAttribute("accountId");
		
		logger.debug("로그인 사용자 ID: "+sessionAccountId);
		
		// 검증 및 검사를 위한 객체
		List<Lecture> lectureList = lectureManagerMapper.selectTeacherLectureDetail(sessionAccountId);
		Question question = questionMapper.selectQuestionOne(questionCommentForm.getQuestionNo());
		
		logger.debug("강사가 관리하는 강좌: "+lectureList);
		logger.debug("학생이 작성한 질문: "+question);
		
		// 해당 강사가 관리하는 강좌가 아닐 경우 실행 중단 후 false 반환
		boolean isCorrectTeacher = false;
		for (Lecture l : lectureList) {
			if (l.getLectureNo() == question.getLectureNo()) {
				isCorrectTeacher = true;
			}
		}
		
		logger.debug("강사의 해당 강좌 관리 가능 여부: "+isCorrectTeacher);
		if (!isCorrectTeacher) {
			return false;
		}
		
		// accountId를 이용해 sessionTeacherName을 가져옴
		String sessionTeacherName = teacherMapper.selectTeacherOne(sessionAccountId).getTeacherName();
		
		logger.debug("강사의 이름: "+sessionTeacherName);
		
		// DTO를 VO로 변환 후 댓글 추가
		QuestionComment questionComment = new QuestionComment();
		questionComment.setQuestionNo(questionCommentForm.getQuestionNo());
		questionComment.setAccountId(sessionAccountId);
		questionComment.setQuestionCommentWriter(sessionTeacherName);
		questionComment.setQuestionCommentContent(questionCommentForm.getQuestionCommentContent());
		questionCommentMapper.insertQuestionComment(questionComment);
		
		// 파일이 있을 경우 for문을 돌면서 MultipartFile을 VO로 변환 후 댓글 첨부파일 추가
		if (questionCommentForm.getQuestionCommentFileList() != null) {
			for (MultipartFile mf : questionCommentForm.getQuestionCommentFileList()) {
				String fileNameUUID = UUID.randomUUID().toString().replaceAll("-", "");
				
				try {
					// 물리적 파일을 생성(하드디스크)
					String fileName = FilePath.getFilePath()+fileNameUUID;
					mf.transferTo(new File(fileName));
					
					logger.debug("파일 생성됨: "+fileName);
				} catch (Exception e) { // 해당 파일 생성 실패 시
					// 원래 예외 메세지를 출력함
					e.printStackTrace();
					
					// Transactional 기능을 수행하는 Service 컴포넌트에게 예외 발생을 알려 작업 내역을 롤백하도록 유도함
					throw new RuntimeException(e);
				}
				
				QuestionCommentFile questionCommentFile = new QuestionCommentFile();
				questionCommentFile.setQuestionCommentFileUUID(fileNameUUID);
				questionCommentFile.setQuestionCommentFileOriginal(mf.getOriginalFilename());
				questionCommentFile.setQuestionCommentNo(questionComment.getQuestionCommentNo()); // selectKey 이용, 위에 추가한 댓글의 고유번호를 가져와서 등록
				questionCommentFile.setQuestionCommentFileSize(mf.getSize());
				questionCommentFile.setQuestionCommentFileType(mf.getContentType());
				questionCommentFileMapper.insertQuestionCommentFile(questionCommentFile);
			}
		}
		
		return true;
	}
	
	// DTO를 받아와 해당 질문게시판 게시글의 댓글을 수정
	// 매개변수: 
	// #1: 질문게시판 댓글 커맨드 객체 (MultipartFile 포함 가능)
	// #2: 로그인한 사용자가 올바른 접근을 했는지 검증하기 위한 세션 객체
	public boolean modifyQuestionComment(QuestionCommentForm questionCommentForm, HttpSession session) {
		// 현재 로그인한 사용자의 정보
		String sessionAccountId = (String)session.getAttribute("accountId");
		
		// QuestionCommentNo에 대한 QuestionNo를 채워넣음(아래 코드에서의 사용 및 완료 후 페이지 리다이렉트에 사용)
		questionCommentForm.setQuestionNo(questionCommentMapper.selectQuestionCommentDetail(questionCommentForm.getQuestionCommentNo()).getQuestionNo());
		
		// 검증 및 검사를 위한 객체
		List<Lecture> lectureList = lectureManagerMapper.selectTeacherLectureDetail(sessionAccountId);
		Question question = questionMapper.selectQuestionOne(questionCommentForm.getQuestionNo());

		logger.debug("강사가 관리하는 강좌: "+lectureList);
		logger.debug("학생이 작성한 질문: "+question);
		
		// 해당 강사가 관리하는 강좌가 아닐 경우 실행 중단 후 false 반환
		boolean isCorrectTeacher = false;
		for (Lecture l : lectureList) {
			if (l.getLectureNo() == question.getLectureNo()) {
				isCorrectTeacher = true;
			}
		}
		
		logger.debug("강사의 해당 강좌 관리 가능 여부: "+isCorrectTeacher);
		if (!isCorrectTeacher) {
			return false;
		}
		
		// DTO를 VO로 변환 후 댓글 수정
		QuestionComment questionComment = new QuestionComment();
		questionComment.setQuestionCommentNo(questionCommentForm.getQuestionCommentNo());
		questionComment.setQuestionCommentContent(questionCommentForm.getQuestionCommentContent());
		questionCommentMapper.updateQuestionComment(questionComment);
		
		// 파일이 있을 경우 for문을 돌면서 MultipartFile을 VO로 변환 후 댓글 첨부파일 추가
		if (questionCommentForm.getQuestionCommentFileList() != null) {
			for (MultipartFile mf : questionCommentForm.getQuestionCommentFileList()) {
				String fileNameUUID = UUID.randomUUID().toString().replaceAll("-", "");
				
				try {
					// 물리적 파일을 생성(하드디스크)
					String fileName = FilePath.getFilePath()+fileNameUUID;
					mf.transferTo(new File(fileName));
					
					logger.debug("파일 생성됨: "+fileName);
				} catch (Exception e) { // 해당 파일 생성 실패 시
					// 원래 예외 메세지를 출력함
					e.printStackTrace();
					
					// Transactional 기능을 수행하는 Service 컴포넌트에게 예외 발생을 알려 작업 내역을 롤백하도록 유도함
					throw new RuntimeException(e);
				}
				
				QuestionCommentFile questionCommentFile = new QuestionCommentFile();
				questionCommentFile.setQuestionCommentFileUUID(fileNameUUID);
				questionCommentFile.setQuestionCommentFileOriginal(mf.getOriginalFilename());
				questionCommentFile.setQuestionCommentNo(questionComment.getQuestionCommentNo()); // selectKey 이용, 위에 추가한 댓글의 고유번호를 가져와서 등록
				questionCommentFile.setQuestionCommentFileSize(mf.getSize());
				questionCommentFile.setQuestionCommentFileType(mf.getContentType());
				questionCommentFileMapper.insertQuestionCommentFile(questionCommentFile);
			}
		}
		
		return true;
	}
	
	// UUID에 해당하는 첨부파일을 삭제
	// 매개변수: 삭제할 첨부파일의 UUID
	public void removeQuestionCommentFile(int questionCommentFileUUID) {
		// 물리적 파일(하드디스크에 존재하는 파일) 제거
		String fileName = FilePath.getFilePath()+questionCommentFileUUID;
		File file = new File(fileName);
		if (file.exists()) {
			file.delete();
			
			logger.debug("파일 제거됨: "+fileName);
		} else {
			logger.debug("파일이 존재하지 않음 (제거 실패): "+fileName);
		}
		
		// DB의 파일 정보 제거
		questionCommentFileMapper.deleteQuestionCommentFile(questionCommentFileUUID);
	}
}
