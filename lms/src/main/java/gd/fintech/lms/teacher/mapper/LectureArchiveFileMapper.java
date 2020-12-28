package gd.fintech.lms.teacher.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.teacher.vo.LectureArchiveFile;

//자료실 첨부 파일 Mapper

@Mapper
public interface LectureArchiveFileMapper {
	//UUID를 이용해 파일 상세정보 출력
	//매개변수:자료실에 등록된 첨부파일 UUID
	//리턴값:파일 상세정보
	LectureArchiveFile selectLectureArchiveFileList(String lectureArchiveFileUUID);
	
	//자료실 첨부 입력(추가)
	//매개변수:set을 이용한 첨부파일 추가
	//리턴값:변경된 행 갯수
	int insertLectureArchiveFile(LectureArchiveFile lectureArchiveFile);
	
	//파일 삭제
	//매개변수:삭제할 자료실 첨부파일 UUID
	//리턴값:변경된 행 갯수
	int deleteLectureArchiveFile(String lectureArchiveFileUUID);
	
	//UUID에 해당하는 파일의 다운로드 횟수 1 증가
	//매개변수:자료실의 등록된 첨부파일UUID
	int updateLectureArchiveFileCountIncrease(String lectureArchiveFileUUID);
	
	
}
