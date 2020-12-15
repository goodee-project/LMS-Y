package gd.fintech.lms.student.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.student.vo.ReportSubmit;

// 과제제출 mapper
@Mapper
public interface ReportSubmitMapper {
	List<ReportSubmit> selectReportSubmitList(); // 과제제출 리스트 출력
	ReportSubmit selectReportSubmitDetail(int reportSubmitNo); // 과제제출 세부내용 
	
	int insertReportSubmit(ReportSubmit reportSubmit); // 과제제출 입력
	int updateReportSubmit(ReportSubmit reportSubmit); // 과제제출 수정
}
