package gd.fintech.lms.student.mapper;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.student.vo.ReportSubmitFile;

// 과제제출 첨부파일 mapper

@Mapper
public interface ReportSubmitFileMapper {
	// 과제제출 첨부파일 입력
	// 매개변수 : 과제제출 첨부파일 정보(UUID, 파일 원래 이름, 과제제출 번호, 사이즈, 타입, 입력날짜)
	// 리턴값 : 과제제출 첨부파일의 정보 입력
	int insertReportSubmitFile(ReportSubmitFile reportSubmitFile);
	
	// 과제제출 첨부파일 삭제
	// 매개변수 : 과제제출 첨부파일 UUID
	// 리턴값 : 과제제출 첨부파일의 정보 삭제
	int deleteReportSubmitFile(String reportSubmitFileUUID);
}
