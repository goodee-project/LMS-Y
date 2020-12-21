package gd.fintech.lms.student.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import gd.fintech.lms.FilePath;
import gd.fintech.lms.dto.ReportSubmitForm;
import gd.fintech.lms.student.mapper.ReportSubmitFileMapper;
import gd.fintech.lms.student.mapper.ReportSubmitMapper;
import gd.fintech.lms.student.vo.ReportSubmit;
import gd.fintech.lms.student.vo.ReportSubmitFile;
import gd.fintech.lms.teacher.vo.Report;

// 과제 제출 service 

@Service
@Transactional
public class ReportSubmitService {
	// debug를 하기위한 logger 
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	// 과제제출 mapper
	@Autowired private ReportSubmitMapper reportSubmitMapper;
	// 과제제출 첨부파일 mapper
	@Autowired private ReportSubmitFileMapper reportSubmitFileMapper;
	
	// 과제제출할 과제의 리스트들 출력
	// 매개변수 : 과제제출할 학생의 계정 id
	// 리턴값 : 강좌 1개에 대한 과제리스트
	public List<Report> getReportList(String accountId) {
		return reportSubmitMapper.selectReportList(accountId);
	}
	
	// 과제의 정보, 과제 제출 정보, 과제 제출 첨부파일의 정보의 상세 정보 출력
	// 매개변수 : 과제 번호, 제출할 학생의 계정 id
	// 리턴값 : 과제 정보, 제출한 과제 정보
	public Report getReportSubmitDetail(int reportNo, String accountId) {
		Map<String, Object> map = new HashMap<>();
		map.put("reportNo", reportNo);
		map.put("accountId", accountId);
		
		return reportSubmitMapper.selectReportSubmitDetail(map);
	}
	
	// 과제 제출할 정보 입력 
	// 매개변수 : 과제 제출 정보
	public void createReportSubmit(ReportSubmitForm reportSubmitForm) {
		ReportSubmit reportSubmit = new ReportSubmit();
		reportSubmit.setReportNo(reportSubmitForm.getReportNo());
		reportSubmit.setAccountId(reportSubmitForm.getAccountId());
		reportSubmit.setReportSubmitTitle(reportSubmitForm.getReportSubmitTitle());
		reportSubmit.setReportSubmitContent(reportSubmitForm.getReportSubmitContent());
		
		reportSubmitMapper.insertReportSubmit(reportSubmit);
		
		List<ReportSubmitFile> reportSubmitFileList = null; 
		if(reportSubmitForm.getReportSubmitFileList() != null) {
			reportSubmitFileList = new ArrayList<ReportSubmitFile>();
			for(MultipartFile mf : reportSubmitForm.getReportSubmitFileList()) {
				ReportSubmitFile reportSubmitFile = new ReportSubmitFile();
				reportSubmitFile.setReportSubmitNo(reportSubmit.getReportSubmitNo());
				
				String fileUUID = UUID.randomUUID().toString().replace("-", "");
				reportSubmitFile.setReportSubmitFileUUID(fileUUID);
				reportSubmitFile.setReportSubmitFileOriginal(mf.getOriginalFilename());
				reportSubmitFile.setReportSubmitFileSize(mf.getSize());
				reportSubmitFile.setReportSubmitFileType(mf.getContentType());
				reportSubmitFileList.add(reportSubmitFile);
				logger.debug(reportSubmitFile.toString());
				try {
					mf.transferTo(new File(FilePath.getFilePath()+fileUUID));
				} catch(Exception e) {
					e.printStackTrace();
					throw new RuntimeException();
				}
			}
		}
		if(reportSubmitFileList != null) {
			for(ReportSubmitFile reportSubmitFile : reportSubmitFileList) {
				reportSubmitFileMapper.insertReportSubmitFile(reportSubmitFile);
			}
		}
	}
	
	// 과제 제출할 정보 수정
	// 매개변수 : 과제 제출 정보
	public void modifyReportSubmit(ReportSubmitForm reportSubmitForm) {
		ReportSubmit reportSubmit = new ReportSubmit();
		reportSubmit.setReportNo(reportSubmitForm.getReportNo());
		reportSubmit.setAccountId(reportSubmitForm.getAccountId());
		reportSubmit.setReportSubmitTitle(reportSubmitForm.getReportSubmitTitle());
		reportSubmit.setReportSubmitContent(reportSubmitForm.getReportSubmitContent());
		
		reportSubmitMapper.updateReportSubmit(reportSubmit);
		
		List<ReportSubmitFile> reportSubmitFileList = null; 
		if(reportSubmitForm.getReportSubmitFileList() != null) {
			reportSubmitFileList = new ArrayList<ReportSubmitFile>();
			for(MultipartFile mf : reportSubmitForm.getReportSubmitFileList()) {
				ReportSubmitFile reportSubmitFile = new ReportSubmitFile();
				reportSubmitFile.setReportSubmitNo(reportSubmit.getReportSubmitNo());
				
				String fileUUID = UUID.randomUUID().toString().replace("-", "");
				reportSubmitFile.setReportSubmitFileUUID(fileUUID);
				reportSubmitFile.setReportSubmitFileOriginal(mf.getOriginalFilename());
				reportSubmitFile.setReportSubmitFileSize(mf.getSize());
				reportSubmitFile.setReportSubmitFileType(mf.getContentType());
				reportSubmitFileList.add(reportSubmitFile);
				logger.debug(reportSubmitFile.toString());
				try {
					mf.transferTo(new File(FilePath.getFilePath()+fileUUID));
				} catch(Exception e) {
					e.printStackTrace();
					throw new RuntimeException();
				}
			}
		}
		if(reportSubmitFileList != null) {
			for(ReportSubmitFile reportSubmitFile : reportSubmitFileList) {
				reportSubmitFileMapper.insertReportSubmitFile(reportSubmitFile);
			}
		}
	}
	
	// 과제제출한 첨부파일을 삭제
	// 매개변수 : 첨부파일의 UUID
	public void removeReportSubmitFile(String reportSubmitFileUUID) {
		File file = new File(FilePath.getFilePath()+reportSubmitFileUUID);
		if(file.exists()) {
			file.delete();
		}
		reportSubmitFileMapper.deleteReportSubmitFile(reportSubmitFileUUID);
	}
}
