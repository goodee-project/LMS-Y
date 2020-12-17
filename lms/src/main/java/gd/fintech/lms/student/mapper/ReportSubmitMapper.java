package gd.fintech.lms.student.mapper;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.student.vo.ReportSubmit;

// 과제제출 mapper
@Mapper
public interface ReportSubmitMapper {
	// 과제제출 세부내용
	// 매개변수 : 과제제출 번호
	// 리턴값 : 과제제출의 모든 정보를 가져옴
	ReportSubmit selectReportSubmitDetail(int reportSubmitNo); 
	
	// 과제제출 입력
	// 매개변수 : 과제제출 정보(과제제출번호 ,과제번호, 계정 id, 입력날짜, 수정날짜, 제목, 내용)
	// 리턴값 : 행 추가
	int insertReportSubmit(ReportSubmit reportSubmit);
	
	// 과제제출 수정
	// 매개변수 : 과제제출 정보(수정날짜, 제목, 내용)
	// 리턴값 : 행 수정
	int updateReportSubmit(ReportSubmit reportSubmit);
}
