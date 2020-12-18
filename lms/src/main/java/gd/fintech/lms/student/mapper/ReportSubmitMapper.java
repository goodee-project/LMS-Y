package gd.fintech.lms.student.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.student.vo.ReportSubmit;
import gd.fintech.lms.teacher.vo.Report;

// 과제제출 mapper

@Mapper
public interface ReportSubmitMapper {
	// 과제제출할 과제리스트 출력
	// 매개변수 : 과제제출할 학생의 계정 id
	// 리턴값 : 강좌 1개에 관련된 과제 리스트 
	List<Report> selectReportList(String accountId);
	
	// 과제제출 세부내용
	// 매개변수 : 과제 번호, 계정 id
	// 리턴값 : 과제제출의 모든 정보를 가져옴
	Report selectReportSubmitDetail(Map <String, Object> map); 
	
	// 과제제출 입력
	// 매개변수 : 과제제출 정보(과제제출번호, 과제번호, 계정 id, 입력날짜, 수정날짜, 제목, 내용)
	// 리턴값 : 행 추가
	int insertReportSubmit(ReportSubmit reportSubmit);
	
	// 과제제출 수정
	// 매개변수 : 과제제출 정보(수정날짜, 제목, 내용)
	// 리턴값 : 행 수정
	int updateReportSubmit(ReportSubmit reportSubmit);
}
