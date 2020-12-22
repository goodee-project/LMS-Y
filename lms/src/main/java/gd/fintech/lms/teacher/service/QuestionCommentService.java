package gd.fintech.lms.teacher.service;

import java.io.File;
import java.util.UUID;

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
import gd.fintech.lms.teacher.vo.Teacher;

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
	
	// UUID를 이용해 원본 파일명 출력
	// 매개변수: 질문게시판 댓글에 등록된 첨부파일 UUID
	// 리턴값: 원본 파일명
	public String getQuestionCommentFileOriginal(String questionCommentFileUUID) {
		return questionCommentFileMapper.selectQuestionCommentFileOriginal(questionCommentFileUUID);
	}
	
	// UUID에 해당되는 파일의 다운로드 횟수를 1 증가
	// 매개변수: 질문게시판 댓글에 등록된 첨부파일 UUID
	public void increaseQuestionCommentFileCount(String questionCommentFileUUID) {
		questionCommentFileMapper.updateQuestionCommentFileCountIncrease(questionCommentFileUUID);
	}
	
	// DTO를 받아와 해당 질문게시판 게시글의 댓글을 생성
	// 매개변수: 
	// #1: 질문게시판 댓글 커맨드 객체 (MultipartFile 포함 가능)
	// #2: 현재 로그인한 사용자를 검증하기 위한 세션 객체
	public boolean createQuestionComment(QuestionCommentForm questionCommentForm, HttpSession session) {
		// 현재 로그인한 사용자의 정보
		String sessionAccountId = (String)session.getAttribute("accountId");
		
		// 검증 및 검사를 위한 객체
		Lecture lecture = lectureManagerMapper.selectTeacherLectureDetail(sessionAccountId);
		Question question = questionMapper.selectQuestionOne(questionCommentForm.getQuestionNo());
		
		// 해당 강사가 관리하는 강좌가 아닐 경우 실행 중단 후 false 반환
		if (lecture.getLectureNo() != question.getLectureNo()) {
			return false;
		}
		
		// accountId를 이용해 sessionTeacherName을 가져옴
		String sessionTeacherName = teacherMapper.selectTeacherOne(sessionAccountId).getTeacherName();
		
		// DTO를 VO로 변환 후 댓글 추가
		QuestionComment questionComment = new QuestionComment();
		questionComment.setQuestionNo(questionCommentForm.getQuestionNo());
		questionComment.setAccountId(sessionAccountId);
		questionComment.setQuestionCommentWriter(sessionTeacherName);
		questionComment.setQuestionCommentContent(questionCommentForm.getQuestionCommentContent());
		questionCommentMapper.insertQuestionComment(questionComment);
		
		// 파일이 있을 경우 for문을 돌면서 MultipartFile을 VO로 변환 후 댓글 첨부파일 추가
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
		
		return true;
	}
	
	// DTO를 받아와 해당 질문게시판 게시글의 댓글을 수정
	// 매개변수: 질문게시판 댓글 커맨드 객체 (MultipartFile 포함 가능)
	public void modifyQuestionComment(QuestionCommentForm questionCommentForm) {
		// DTO를 VO로 변환 후 댓글 수정
		QuestionComment questionComment = new QuestionComment();
		questionComment.setQuestionCommentNo(questionCommentForm.getQuestionCommentNo());
		questionComment.setQuestionCommentContent(questionCommentForm.getQuestionCommentContent());
		questionCommentMapper.updateQuestionComment(questionComment);
		
		// 파일이 있을 경우 for문을 돌면서 MultipartFile을 VO로 변환 후 댓글 첨부파일 추가
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
