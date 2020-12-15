package gd.fintech.lms.manager.mapper;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.manager.vo.Syllabus;

// 강의계획서를 작성 및 수정하기, 강의계획서 승인을 하는 Mapper

@Mapper
public interface SyllabusMapper {
	// 매개변수: 강의 계획서 정보
	// 리턴값: 변경된 행의 갯수
	int insertSyllabus(Syllabus syllabus);
	
	// 강사가 강의 계획서를 수정
	// 매개변수: 강의 계획서 정보
	// 리턴값: 변경된 행의 갯수
	int updateSyllabus(Syllabus syllabus);
	
	// 강사가 강의 계획서를 승인(강사가 운영자에게 승인요청)
	// 매개변수: 승인할 강의계획서 고유번호
	// 리턴값: 변경된 행의 갯수
	int insertSyllabusTeacherSign(int syllabusNo);
	
	// 운영자가 강의계획서를 승인
	// 매개변수: 승인할 강의 계획서 고유번호
	// 리턴값: 변경된 행의 갯수
	int insertSyllabusManagerSign(int syllabusNo);
	
	// 강사가 강의 계획서 수정 시 승인을 전부 취소
	// 매개변수: 승인을 취소할 강의 계획서 고유번호
	// 리턴값: 변경된 행의 갯수
	int updateCancleSyllabusSign(int syllabusNo);
}
