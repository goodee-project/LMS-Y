package gd.fintech.lms.teacher.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import gd.fintech.lms.manager.mapper.LectureManagerMapper;
import gd.fintech.lms.manager.vo.Lecture;
import gd.fintech.lms.student.mapper.ReportSubmitFileMapper;
import gd.fintech.lms.student.mapper.ReportSubmitMapper;
import gd.fintech.lms.student.vo.ReportSubmit;
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
	
	// 과제 평가 관리를 위한 매퍼
	@Autowired private ReportSubmitMapper reportSubmitMapper;
	
	// 검증 및 변수를 가져오는 데 사용하는 매퍼
	@Autowired private LectureManagerMapper lectureManagerMapper;
	
	// 과제제출 첨부파일 다운로드 관련 작업에 사용되는 매퍼
	@Autowired private ReportSubmitFileMapper reportSubmitFileMapper;
	
	// 해당 강좌에 등록된 과제 리스트를 출력 (currentPage 페이지의 내용 10개만 보여줌)
	// 매개변수:
	// #1: 어떤 강좌의 과제인지를 식별하기 위한 강좌번호
	// #2: 표시할 페이지 번호
	// #3: 현재 로그인한 사용자의 정보가 담긴 세션 객체
	// 리턴값: 등록된 과제 리스트
	public Map<String, Object> getReportList(int lectureNo, int currentPage, HttpSession session) {
		// 현재 로그인한 사용자의 정보
		String sessionAccountId = (String)session.getAttribute("accountId");

		// 검증 및 검사를 위한 객체
		List<Lecture> lectureList = lectureManagerMapper.selectTeacherLectureDetail(sessionAccountId);

		// 해당 강사가 관리하는 강좌가 아닐 경우 실행 중단 후 false 반환
		boolean isCorrectTeacher = false;
		for (Lecture l : lectureList) {
			if (l.getLectureNo() == lectureNo) {
				isCorrectTeacher = true;
			}
		}
		
		logger.debug("강사의 해당 강좌 관리 가능 여부: "+isCorrectTeacher);
		if (!isCorrectTeacher) {
			return null;
		}
		
		// 리턴값으로 보낼 리스트 생성
		Map<String, Object> map = new HashMap<>();
		
		// rowPerPage 및 beginRow 계산
		int rowPerPage = 10;
		int beginRow = (currentPage-1)*rowPerPage;
		
		// 페이지 네비게이션용 값 연산
		int pageNaviSize = 10;
		
		int lastPage = reportMapper.selectReportCount(lectureNo)/pageNaviSize;
		if (reportMapper.selectReportCount(lectureNo)%pageNaviSize != 0) {
			lastPage += 1;
		}
		
		int pageNaviBegin = (currentPage-1)/pageNaviSize*pageNaviSize+1;	// 페이지의 첫번째 값을 구한 후(pageNaviSize가 10일 경우 0~9->0, 10~19->10, 20~29->20)후 1을 더해줌(1, 11, 21, ...)
		int pageNaviEnd = pageNaviBegin+pageNaviSize-1;						// pageNaviBegin도 페이지 네비에 포함되므로 -1을 하여 딱 pageNaviSize개의 페이지 네비 리스트가 보이도록 설정함
		if (pageNaviEnd > lastPage) {
			pageNaviEnd = lastPage;
		}
		
		map.put("pageNaviBegin", pageNaviBegin);
		map.put("pageNaviEnd", pageNaviEnd);
		map.put("lastPage", lastPage);
		
		// 테스트용 코드
		logger.debug("계산된 시작 행: "+beginRow);
		logger.debug("페이지당 보여줄 행 갯수: "+rowPerPage);
		logger.debug("페이지 네비게이션 시작값: "+pageNaviBegin);
		logger.debug("페이지 네비게이션 종료값: "+pageNaviEnd);
		logger.debug("총 페이지 갯수: "+lastPage);
		
		// ReportList를 가져오기 위한 파라미터 설정
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("lectureNo", lectureNo);
		paramMap.put("beginRow", beginRow);
		paramMap.put("rowPerPage", rowPerPage);
		
		// ReportList를 가져오고, Report별로 ReportSubmit 갯수를 첨부해서 info(정보)의 List를 리턴함
		List<Map<String, Object>> infoList = new ArrayList<>();
		for (Report report : reportMapper.selectReportList(paramMap)) {
			Map<String, Object> info = new HashMap<>();
			info.put("report", report);
			info.put("reportSubmitCount", reportMapper.selectReportSubmitCount(report.getReportNo()));
			
			infoList.add(info);
		}
		map.put("infoList", infoList);
		
		return map;
	}
	
	// 해당 과제에 대한 상세 정보 출력 (제출된 과제 포함)
	// 매개변수: 과제 고유번호
	// 리턴값: 제출된 과제를 포함한 과제 상세정보
	public Map<String, Object> getReportDetail(int reportNo) {
		Report report = reportMapper.selectReportDetail(reportNo);
		boolean isEditable = true;		// 과제가 수정 가능한지 확인하는 용도의 변수
		boolean isEvaluatable = true;	// 과제 평가 기간인지 확인하는 용도의 변수
		
		// 과제 평가 기간 내에 속해있는지 확인하기 위해 Date타입으로 현재 날짜와 report에 기입된 날짜를 변환
		// 변환 도중 예외 발생 시 스택트레이싱 후 Transactional 애노테이션에게 예외 발생을 알림
		try {
			SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			
			Date currentDate = Calendar.getInstance().getTime();
			
			if (fmt.parse(report.getReportStartDate()).getTime() < currentDate.getTime()) {
				// 과제 시작 기간 이후라면 false로 플래그를 변경
				isEditable = false;
			}
			
			if (fmt.parse(report.getReportStartDate()).getTime() > currentDate.getTime()
			 || fmt.parse(report.getReportEndDate()).getTime() < currentDate.getTime()) {
				// 과제 평가 기간이 아닐 경우 false로 플래그를 변경
				isEvaluatable = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		Map<String, Object> map = new HashMap<>();
		map.put("report", report);
		map.put("isEditable", isEditable);
		map.put("isEvaluatable", isEvaluatable);
		
		return map;
	}
	
	// 해당 과제에 대한 상세 정보에서 날짜 포맷을 yyyy-MM-dd(시분초 제외)로 변경 후 출력 
	// 매개변수: 과제 고유번호
	// 리턴값: 제출된 과제를 포함한 과제 상세정보
	public Report getReportDetailWithDateFormatting(int reportNo) {
		Map<String, Object> map = this.getReportDetail(reportNo);
		
		// 날짜 포맷을 변경하기 위해 참조값을 가져옴
		Report report = (Report)map.get("report");
		
		// HTML이 읽을 수 있는 날짜데이터로 변경하기 위해 hh:MM:ss.SSS(시분초 및 밀리초) 부분을 없앰
		report.setReportStartDate(report.getReportStartDate().replaceAll("\\s*\\d+:\\d+:\\d+\\.\\d+$", ""));
		report.setReportEndDate(report.getReportEndDate().replaceAll("\\s*\\d+:\\d+:\\d+\\.\\d+$", ""));
		
		return report;
	}
	
	// 평가의 기준으로 참고할 학생의 과제제출 내용 및 파일 출력
	// 매개변수: 과제제출 고유변호
	// 리턴값: 과제제출 객체
	public ReportSubmit getReportSubmitDetail(int reportSubmitNo) {
		return reportSubmitMapper.selectReportSubmitOne(reportSubmitNo);
	}
	
	// 과제제출 고유번호를 이용해 과제 번호 출력
	// 매개변수: 과제제출 고유변호
	// 리턴값: 과제제출을 등록한 과제의 고유번호
	public int getReportNoByReportSubmitNo(int reportSubmitNo) {
		return reportMapper.selectReportNoByReportSubmitNo(reportSubmitNo);
	}
	
	// 과제 생성
	// 매개변수:
	// #1: 과제 객체, setter를 사용해 추가할 정보 lectureNo, reportTitle, reportContent, reportStartDate, reportEndDate를 넣을 것
	// #2: 로그인한 사용자가 올바른 접근을 했는지 검증하기 위한 세션 객체
	public boolean createReport(Report report, HttpSession session) {
		// 현재 로그인한 사용자의 정보
		String sessionAccountId = (String)session.getAttribute("accountId");
		
		logger.debug("로그인 사용자 ID: "+sessionAccountId);
		
		// 검증 및 검사를 위한 객체
		List<Lecture> lectureList = lectureManagerMapper.selectTeacherLectureDetail(sessionAccountId);
		
		logger.debug("강사가 관리하는 강좌: "+lectureList);
		
		// 해당 강사가 관리하는 강좌가 아닐 경우 실행 중단 후 false 반환
		boolean isCorrectTeacher = false;
		for (Lecture l : lectureList) {
			if (l.getLectureNo() == report.getLectureNo()) {
				isCorrectTeacher = true;
			}
		}
		
		logger.debug("강사의 해당 강좌 관리 가능 여부: "+isCorrectTeacher);
		if (!isCorrectTeacher) {
			return false;
		}
		
		reportMapper.insertReport(report);
		return true;
	}
	
	// 과제 수정
	// 매개변수: 
	// #1: 과제 객체, setter를 사용해 변경할 행 고유번호 reportNo, 변경할 정보 reportTitle, reportContent, reportStartDate, reportEndDate를 넣을 것
	// #2: 로그인한 사용자가 올바른 접근을 했는지 검증하기 위한 세션 객체
	public boolean modifyReport(Report report, HttpSession session) {
		// 현재 로그인한 사용자의 정보
		String sessionAccountId = (String)session.getAttribute("accountId");
		
		logger.debug("로그인 사용자 ID: "+sessionAccountId);
		
		// 검증 및 검사를 위한 객체
		List<Lecture> lectureList = lectureManagerMapper.selectTeacherLectureDetail(sessionAccountId);
		
		logger.debug("강사가 관리하는 강좌: "+lectureList);
		
		// 해당 강사가 관리하는 강좌가 아닐 경우 실행 중단 후 false 반환
		boolean isCorrectTeacher = false;
		for (Lecture l : lectureList) {
			if (l.getLectureNo() == reportMapper.selectReportDetail(report.getReportNo()).getLectureNo()) {
				isCorrectTeacher = true;
			}
		}
		
		logger.debug("강사의 해당 강좌 관리 가능 여부: "+isCorrectTeacher);
		if (!isCorrectTeacher) {
			return false;
		}
		
		reportMapper.updateReport(report);
		return true;
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
			String originalFileName = reportSubmitFileMapper.selectReportSubmitFileOriginalName(reportSubmitFileUUID);
			if (request.getHeader("user-agent").indexOf("MSIE") != -1 || request.getHeader("user-agent").indexOf("Trident") != -1) {
				originalFileName = URLEncoder.encode(originalFileName, "UTF-8").replaceAll("\\+", "%20");
			} else {
				originalFileName = new String(originalFileName.getBytes("UTF-8"), "ISO-8859-1");
			}
			
			// MIME 타입을 설정하고 첨부파일 형태로, 파일명은 originalFileName으로 설정함
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
			reportSubmitFileMapper.updateReportSubmitFileCountOfDownload(reportSubmitFileUUID);
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
