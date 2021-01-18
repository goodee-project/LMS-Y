package gd.fintech.lms.admin.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.manager.vo.Manager;
import gd.fintech.lms.student.vo.Student;
import gd.fintech.lms.teacher.vo.Teacher;

// 휴면계정 관리 관련 Mapper

@Mapper
public interface DormantAccountMapper {
	// 운영자 휴면계정 목록을 페이징하여 출력
	// 매개변수: map에 저장된 페이징 변수 beginRow(해당 페이지), rowPerPage(페이지에 표시할 항목수)와 검색 변수 searchType(검색조건), searchKeyword(검색어)
	// 리턴값: 휴면계정 목록
	// 검색을 했다면 검색 결과에 따른 휴면계정 목록
	List<Manager> selectDormantAccountListByManager(Map<String, Object> map);
	
	// 강사 휴면계정 목록을 페이징하여 출력
	// 매개변수: map에 저장된 페이징 변수 beginRow(해당 페이지), rowPerPage(페이지에 표시할 항목수)와 검색 변수 searchType(검색조건), searchKeyword(검색어)
	// 리턴값: 휴면계정 목록
	// 검색을 했다면 검색 결과에 따른 휴면계정 목록
	List<Teacher> selectDormantAccountListByTeacher(Map<String, Object> map);
	
	// 학생 휴면계정 목록을 페이징하여 출력
	// 매개변수: map에 저장된 페이징 변수 beginRow(해당 페이지), rowPerPage(페이지에 표시할 항목수)와 검색 변수 searchType(검색조건), searchKeyword(검색어)
	// 리턴값: 휴면계정 목록
	// 검색을 했다면 검색 결과에 따른 휴면계정 목록
	List<Student> selectDormantAccountListByStudent(Map<String, Object> map);
	
	// 운영자 휴면계정 목록의 페이징을 위해 총 항목수를 출력
	// 매개변수: map에 저장된 검색 변수 searchType(검색조건), searchKeyword(검색어)
	// 리턴값: 운영자 휴면계정의 총 항목수
	// 검색을 했다면 검색 결과에 따른 휴면계정 총 항목수 출력
	int selectDormantAccountCountByManager(Map<String, Object> map);
	
	// 강사 휴면계정 목록의 페이징을 위해 총 항목수를 출력
	// 매개변수: map에 저장된 검색 변수 searchType(검색조건), searchKeyword(검색어)
	// 리턴값: 강사 휴면계정의 총 항목수
	// 검색을 했다면 검색 결과에 따른 휴면계정 총 항목수 출력
	int selectDormantAccountCountByTeacher(Map<String, Object> map);
	
	// 학생 휴면계정 목록의 페이징을 위해 총 항목수를 출력
	// 매개변수: map에 저장된 검색 변수 searchType(검색조건), searchKeyword(검색어)
	// 리턴값: 학생 휴면계정의 총 항목수
	// 검색을 했다면 검색 결과에 따른 휴면계정 총 항목수 출력
	int selectDormantAccountCountByStudent(Map<String, Object> map);
	
	// 계정 상태를 출력
	// 매개변수: accountId(아이디)
	// 리턴값: 계정 상태
	// 휴면계정인지 확인하기 위해 계정 상태 출력
	String selectAccountState(String accountId);
}
