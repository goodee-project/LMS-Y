package gd.fintech.lms.teacher.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.teacher.vo.LectureArchiveFile;

//자료실 첨부 파일 Mapper

@Mapper
public interface LectureArchiveFileMapper {
	//자료실 첨부 파일 목록
	//매개변수:강좌 자료실 고유변호
	//리턴값:강좌자료실 첨부파일 목록
	List<String> selectLectureArchiveFileList(int lectureArchiveNo);
	
	//자료실 첨부 입력(추가)
	//매개변수:set을 이용한 첨부파일 추가
	//리턴값:변경된 행 갯수
	int insertLectureArchiveFile(LectureArchiveFile lectureArchiveFile);
	
	//파일 삭제
	//매개변수:삭제할 자료실 첨부파일 UUID
	//리턴값:변경된 행 갯수
	int deleteLectureArchiveFile(String lectureArchiveFileUUID);
	
	//파일 첨부된 자료실 삭제
	//매개변수:삭제할 자료실 고유번호
	//리턴값:변경된 행 갯수
	int deleteLectureArchiveFileOne(int lectureArchiveNo);
}
