package gd.fintech.lms.manager.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.manager.vo.Subject;

// 과목 정보를 입력, 수정하는 Mapper

@Mapper
public interface SubjectMapper {	
	// 과목 정보의 일부를 페이징하여 리스트로 출력
	// 매개변수: Map.put()을 사용하여 페이징 변수 beginRow(해당 페이지), rowPerPage(해당 페이지에서 표시할 항목수)
	// 리턴값: 과목 정보 리스트
	List<Subject> selectSubjectList(Map<String, Integer> map);
	
	// 과목 정보 리스트의 페이징을 위해 전체 항목수 출력
	// 매개변수: 없음
	// 리턴값: 전체 항목 수
	int selectSubjectCount();
	
	// 과목 정보를 출력
	// 매개변수: 과목 고유번호
	// 리턴값: 과목 고유번호에 해당하는 과목 정보
	Subject selectSubjectDetail(int SubjectNo);
	
	// 과목 정보를 입력
	// 매개변수: 과목 정보
	// 리턴값: 변경된 행의 갯수
	int insertSubject(Subject subject);
		
	// 과목 정보를 수정
	// 매개변수: 과목 정보
	// 리턴값: 변경된 행의 갯수
	int updateSubject(Subject subject);
}
