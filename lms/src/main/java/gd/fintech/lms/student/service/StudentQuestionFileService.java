package gd.fintech.lms.student.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gd.fintech.lms.FilePath;
import gd.fintech.lms.student.mapper.StudentQuestionCommentFileMapper;
import gd.fintech.lms.student.vo.StudentQuestionCommentFile;

//학생이 질문에 대한 답의 파일 서비스

@Service
@Transactional
public class StudentQuestionFileService {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired StudentQuestionCommentFileMapper studentQuestionCommentFileMapper;
	
	// 파일 다운로드
	// 매개변수:
	// #1: 다운로드받을 파일 UUID
	// #2, #3: 다운로드를 진행하기 위한 서블릿 리퀘스트 및 리스폰스 객체
	// 리턴값: 다운로드 성공 여부
	public boolean downloadStudentQuestionCommentFile(String questionCommentFileUUID, HttpServletRequest request, HttpServletResponse response) {
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
			StudentQuestionCommentFile studentQuestionCommentFile = studentQuestionCommentFileMapper.selectQuestionCommentFileDetail(questionCommentFileUUID);
			String fileContentType = studentQuestionCommentFile.getQuestionCommentFileType();
			String originalFileName = studentQuestionCommentFile.getQuestionCommentFileOriginal();
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
			studentQuestionCommentFileMapper.updateQuestionCommentFileCountIncrease(questionCommentFileUUID);
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
}
