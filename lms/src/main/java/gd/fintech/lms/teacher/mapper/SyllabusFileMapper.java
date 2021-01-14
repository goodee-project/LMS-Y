package gd.fintech.lms.teacher.mapper;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.teacher.vo.SyllabusFile;

// 강의계획서 첨부파일 Mapper

@Mapper
public interface SyllabusFileMapper {
	// 강의계획서 첨부파일 정보를 출력
	// 매개변수: syllabusFileUUID(강의계획서 첨부파일 UUID)
	// 리턴값: 강의계획서 첨부파일 UUID에 해당하는 파일 정보
	SyllabusFile selectSyllabusFileList(String syllabusFileUUID);
	
	// 강의계획서 첨부파일을 등록
	// 매개변수: syllabusFile(강의계획서 첨부파일)
	// 리턴값: 변경된 행의 갯수
	int insertSyllabusFile(SyllabusFile syllabusFile);
	
	// 강의계획서 첨부파일을 삭제
	// 매개변수: syllabusFileUUID(강의계획서 첨부파일 UUID)
	// 리턴값: 변경된 행의 갯수
	int deleteSyllabusFile(String syllabusFileUUID);
	
	// 강의계획서 첨부파일 다운로드 시 다운로드 횟수를 1 증가
	// 매개변수: syllabusFileUUID(강의계획서 첨부파일 UUID)
	// 리턴값: 변경된 행의 갯수
	int updateSyllabusFileCountIncrease(String syllabusFileUUID);
}
