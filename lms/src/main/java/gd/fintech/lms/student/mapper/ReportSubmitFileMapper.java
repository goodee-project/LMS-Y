package gd.fintech.lms.student.mapper;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.student.vo.ReportSubmitFile;

// 과제제출 첨부파일 mapper

@Mapper
public interface ReportSubmitFileMapper {
	// 과제제출 첨부파일 출력
	// 매개변수 : 과제제출 번호
	// 리턴값 : 과제제출 첨부파일의 정보를 가져옴
	ReportSubmitFile selectReportSubmitFile(int reportSubmitNo);

	// 과제제출 첨부파일 입력
	// 매개변수 : 과제제출 첨부파일 정보(파일 원래 이름, 과제제출 번호, 사이즈, 타입, 입력날짜)
	// 리턴값 : 과제제출 첨부파일의 정보를 가져옴
	int insertReportSubmitFile(ReportSubmitFile reportSubmitFile);
	
	// 과제제출 첨부파일 삭제
	// 매개변수 : 과제제출 첨부파일 UUID
	// 리턴값 : 과제제출 첨부파일의 정보를 가져옴
	int deleteReportSubmitFile(String reportSubmitFileUUID); 
}
