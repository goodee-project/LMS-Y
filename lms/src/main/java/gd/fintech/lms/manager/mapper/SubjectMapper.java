package gd.fintech.lms.manager.mapper;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.manager.vo.Subject;

// 과목 정보 Mapper
@Mapper
public interface SubjectMapper {
	// 과목 정보를 테이블에 입력
	int insertSubject(Subject subject);
	
	// 과목 정보를 테이블에 수정
	int updateSubject(Subject subject);
}
