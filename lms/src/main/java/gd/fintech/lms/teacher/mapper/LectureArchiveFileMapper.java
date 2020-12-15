package gd.fintech.lms.teacher.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.teacher.vo.LectureArchiveFile;

//자료실 첨부 파일 Mapper

@Mapper
public interface LectureArchiveFileMapper {
	//자료실 첨부 파일 목록
	List<String> selectLectureArchiveFileList(int lectureArchiveNo);
	
	//자료실 첨부 입력(추가)
	int insertLectureArchiveFile(LectureArchiveFile lectureArchiveFile);
	
	//파일 삭제
	int deleteLectureArchiveFile(String lectureArchiveFileUUID);
	
	//파일 첨부된 자료실 삭제
	int deleteLectureArchiveFileOne(int lectureArchiveNo);
}
