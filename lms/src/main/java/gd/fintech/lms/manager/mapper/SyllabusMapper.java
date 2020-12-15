package gd.fintech.lms.manager.mapper;

import org.apache.ibatis.annotations.Mapper;

// 강의계획서 Mapper
@Mapper
public interface SyllabusMapper {
	// 강사가 강의계획서를 입력
	int insertSyllabus();
	
	// 강사가 강의계획서를 수정
	int updateSyllabus();
	
	// 강사가 강의계획서를 승인(강사가 운영자에게 승인요청)
	int insertSyllabusTeacherSign();
	
	// 운영자가 강의계획서를 승인
	int insertSyllabusManagerSign();
	
	// 강사가 강의계획 수정 시 승인 전부 삭제
	int deleteAllSyllabusSign();
}
