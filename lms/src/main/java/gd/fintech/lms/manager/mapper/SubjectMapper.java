package gd.fintech.lms.manager.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.manager.vo.Subject;

// 과목 정보를 입력, 수정하는 Mapper

@Mapper
public interface SubjectMapper {	
	// 과목 리스트를 페이징하여 출력
	// 매개변수: map에 저장된 페이징 변수 beginRow(해당 페이지), rowPerPage(페이지에 표시할 항목수)와 검색 변수 searchType(검색조건), searchKeyword(검색어)
	// 리턴값: 과목 정보 리스트
	// 검색을 했다면 검색 결과에 따른 과목 리스트 출력
	List<Subject> selectSubjectList(Map<String, Object> map);
	
	// 과목 리스트의 페이징을 위해 총 항목수를 출력
	// 매개변수:
	// #1. searchType(검색조건)
	// #2. searchKeyword(검색어)
	// 리턴값: 과목 정보의 총 항목수
	// 검색을 했다면 검색 결과에 따른 과목의 총 항목수 출력
	int selectSubjectCount(String searchType, String searchKeyword);
	
	// 과목 정보를 출력
	// 매개변수: SubjectNo(과목 고유번호)
	// 리턴값: 과목 고유번호에 해당하는 과목 정보
	Subject selectSubjectDetail(int SubjectNo);
	
	// 과목 정보를 입력
	// 매개변수: subject(과목 정보)
	// 리턴값: 변경된 행의 갯수
	int insertSubject(Subject subject);
		
	// 과목 정보를 수정
	// 매개변수: subject(과목 정보)
	// 리턴값: 변경된 행의 갯수
	int updateSubject(Subject subject);
}
