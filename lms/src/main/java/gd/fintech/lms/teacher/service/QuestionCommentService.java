package gd.fintech.lms.teacher.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import gd.fintech.lms.dto.QuestionCommentForm;
import gd.fintech.lms.teacher.mapper.QuestionCommentFileMapper;
import gd.fintech.lms.teacher.mapper.QuestionCommentMapper;
import gd.fintech.lms.teacher.vo.QuestionComment;
import gd.fintech.lms.teacher.vo.QuestionCommentFile;

// 질문게시판 댓글을 관리하는 서비스

@Service
@Transactional
public abstract class QuestionCommentService {
	// 질문게시판 댓글 관리를 위한 매퍼
	@Autowired private QuestionCommentMapper questionCommentMapper;
	
	// 질문게시판 댓글 파일 관리를 위한 매퍼
	@Autowired private QuestionCommentFileMapper questionCommentFileMapper;
	
	// DTO를 받아와 해당 질문게시판 게시글의 댓글을 생성
	// 매개변수: 질문게시판 댓글 커맨드 객체 (MultipartFile 포함 가능)
	public void createQuestionComment(QuestionCommentForm questionCommentForm) {
		// DTO를 VO로 변환 후 댓글 추가
		QuestionComment questionComment = new QuestionComment();
		questionComment.setQuestionNo(questionCommentForm.getQuestionNo());
		questionComment.setAccountId(questionCommentForm.getAccountId());
		questionComment.setQuestionCommentContent(questionCommentForm.getQuestionCommentContent());
		questionCommentMapper.insertQuestionComment(questionComment);
		
		// 파일이 있을 경우 for문을 돌면서 MultipartFile을 VO로 변환 후 댓글 첨부파일 추가
		for (MultipartFile mf : questionCommentForm.getQuestionCommentFileList()) {
			String filenameUUID = UUID.randomUUID().toString().replaceAll("-", "");
			
			// TODO File 경로 설정 관련 클래스 만들것! (리눅스냐 윈도우즈냐에 따라서 경로를 유연하게 변경해주는 코드가 필요함)
			
			QuestionCommentFile questionCommentFile = new QuestionCommentFile();
			questionCommentFile.setQuestionCommentFileUUID(filenameUUID);
			questionCommentFile.setQuestionCommentFileOriginal(mf.getOriginalFilename());
			questionCommentFile.setQuestionCommentNo(questionComment.getQuestionCommentNo()); // selectKey 이용, 위에 추가한 댓글의 고유번호를 가져와서 등록
			questionCommentFile.setQuestionCommentFileSize(mf.getSize());
			questionCommentFile.setQuestionCommentFileType(mf.getContentType());
			questionCommentFileMapper.insertQuestionCommentFile(questionCommentFile);
		}
	}
	
	// DTO를 받아와 해당 질문게시판 게시글의 댓글을 수정
	// 매개변수: 질문게시판 댓글 커맨드 객체 (MultipartFile 포함 가능)
	public void modifyQuestionComment(QuestionCommentForm questionCommentForm) {
		QuestionComment questionComment = new QuestionComment();
		questionComment.setQuestionCommentNo(questionCommentForm.getQuestionCommentNo());
		questionComment.setQuestionCommentContent(questionCommentForm.getQuestionCommentContent());
		questionCommentMapper.updateQuestionComment(questionComment);
		
		for (MultipartFile mf : questionCommentForm.getQuestionCommentFileList()) {
			QuestionCommentFile questionCommentFile = new QuestionCommentFile();
			questionCommentFile.setQuestionCommentFileUUID(UUID.randomUUID().toString().replaceAll("-", ""));
			questionCommentFile.setQuestionCommentFileOriginal(mf.getOriginalFilename());
			questionCommentFile.setQuestionCommentNo(questionComment.getQuestionCommentNo());
			questionCommentFile.setQuestionCommentFileSize(mf.getSize());
			questionCommentFile.setQuestionCommentFileType(mf.getContentType());
			questionCommentFileMapper.insertQuestionCommentFile(questionCommentFile);
		}
	}
	
	// UUID에 해당하는 첨부파일을 삭제
	// 매개변수: 삭제할 첨부파일의 UUID
	public abstract void removeQuestionCommentFile(int questionCommentFileUUID);
}
