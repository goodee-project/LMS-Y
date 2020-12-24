package gd.fintech.lms.teacher.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gd.fintech.lms.FilePath;
import gd.fintech.lms.student.vo.ReportSubmit;
import gd.fintech.lms.student.vo.ReportSubmitFile;
import gd.fintech.lms.teacher.mapper.ReportMapper;
import gd.fintech.lms.teacher.vo.Report;

// 과제 출제 및 과제 평가를 관리하는 서비스

@Service
@Transactional
public class ReportService {
	// 디버깅 메세지 출력을 위한 로거
	Logger logger = LoggerFactory.getLogger(ReportService.class);
	
	// 과제 출제 및 과제 평가 관리를 위한 매퍼
	@Autowired private ReportMapper reportMapper;
	
	// 과제제출 첨부파일 다운로드 관련 작업에 사용되는 매퍼
	//@Autowired private ReportSubmitFileMapper reportSubmitFileMapper;
	
	// 해당 강좌에 등록된 과제 리스트를 출력 (currentPage 페이지의 내용 10개만 보여줌)
	// 매개변수:
	// #1: 표시할 페이지 번호
	// #2: 현재 로그인한 사용자의 정보가 담긴 세션 객체
	// 리턴값: 등록된 과제 리스트
	public List<Map<String, Object>> getReportList(int currentPage, HttpSession session) {
		// 현재 로그인한 사용자의 정보
		String sessionAccountId = (String)session.getAttribute("accountId");
		
		// 리턴값으로 보낼 리스트 생성
		List<Map<String, Object>> returnList = new ArrayList<>();
		
		// rowPerPage 및 beginRow 설정
		int rowPerPage = 10;
		int beginRow = (currentPage-1)*rowPerPage;

		// 테스트용 코드
		logger.debug("계산된 시작 행: "+beginRow);
		logger.debug("페이지당 보여줄 행 갯수: "+rowPerPage);
		
		// ReportList를 가져오기 위한 파라미터 설정
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("accountId", sessionAccountId);
		paramMap.put("beginRow", beginRow);
		paramMap.put("rowPerPage", rowPerPage);
		
		// ReportList를 가져오고, Report별로 ReportSubmit 갯수를 첨부해서 Map의 List를 리턴함
		List<Report> list = reportMapper.selectReportList(paramMap);
		for (Report report : list) {
			Map<String, Object> map = new HashMap<>();
			map.put("report", report);
			map.put("reportSubmitCount", reportMapper.selectReportSubmitCount(report.getReportNo()));
			
			returnList.add(map);
		}
		
		return returnList;
	}
	
	// 해당 과제에 대한 상세 정보 출력 (제출된 과제 포함)
	// 매개변수: 과제 고유번호
	// 리턴값: 제출된 과제를 포함한 과제 상세정보
	public Report getReportDetail(int reportNo) {
		return reportMapper.selectReportDetail(reportNo);
	}
	
	// 과제 생성
	// 매개변수: 과제 객체, setter를 사용해 추가할 정보 lectureNo, reportTitle, reportContent, reportStartDate, reportEndDate를 넣을 것
	public void createReport(Report report) {
		reportMapper.insertReport(report);
	}
	
	// 과제 수정
	// 매개변수: 과제 객체, setter를 사용해 변경할 행 고유번호 reportNo, 변경할 정보 reportTitle, reportContent, reportStartDate, reportEndDate를 넣을 것
	public void modifyReport(Report report) {
		reportMapper.updateReport(report);
	}
	
	// 학생이 제출한 과제 평가
	// 매개변수, 과제제출 객체, setter를 사용해 변경할 행 고유번호 reportSubmitNo, 변경할 정보 reportSubmitScore, reportSubmitFeedback을 넣을 것
	public void evaluateReportSubmit(ReportSubmit reportSubmit) {
		reportMapper.updateReportSubmitEvaluation(reportSubmit);
	}
	
	// 파일 다운로드
	// 매개변수:
	// #1: 다운로드받을 파일 UUID
	// #2, #3: 다운로드를 진행하기 위한 서블릿 리퀘스트 및 리스폰스 객체
	// 리턴값: 다운로드 성공 여부
	public boolean downloadReportSubmitFile(String reportSubmitFileUUID, HttpServletRequest request, HttpServletResponse response) {
		// 서버에 업로드된 파일을 가져옴
		String fileName = FilePath.getFilePath()+reportSubmitFileUUID;
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
			ReportSubmitFile reportSubmitFile = new ReportSubmitFile(); //reportSubmitFileMapper.selectReportSubmitFileDetail(reportSubmitFileUUID);
			String fileContentType = reportSubmitFile.getReportSubmitFileType();
			String originalFileName = reportSubmitFile.getReportSubmitFileOriginal();
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
			//reportSubmitFileMapper.updateQuestionCommentFileCountIncrease(reportSubmitFileUUID);
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
