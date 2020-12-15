package gd.fintech.lms.teacher.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.teacher.vo.Report;

@Mapper
public interface ReportMapper {
	// 매개변수 map엔 beginRow 및 rowPerPage를 넣을 것
	List<Report> selectReportList(Map<String, Object> map); // 과제 리스트 출력 (페이징 기법 사용)
	
	int insertReport(Report report); // 과제 생성
	int updateReport(Report report); // 과제 수정
}
