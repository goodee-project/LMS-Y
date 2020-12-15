package gd.fintech.lms.student.mapper;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.student.vo.ReportSubmitFile;

// 과제제출 첨부파일 mapper
@Mapper
public interface ReportSubmitFileMapper {
	ReportSubmitFile selectReportSubmitFile(int reportSubmitNo); // 과제제출 첨부파일 출력

	int insertReportSubmitFile(ReportSubmitFile reportSubmitFile); // 과제제출 첨부파일 입력
	int deleteReportSubmitFile(String reportSubmitFileUUID); // 과제제출 첨부파일 삭제
}
