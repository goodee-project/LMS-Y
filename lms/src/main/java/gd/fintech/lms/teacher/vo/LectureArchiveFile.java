package gd.fintech.lms.teacher.vo;

import lombok.Data;

//강좌 자료실 첨부파일 VO
@Data
public class LectureArchiveFile {
	private String lectureArchiveFileUuid; //강좌 자료실 첨부파일 UUID
	private String lectureArchiveFileOriginal; //강좌 자료실 첨부파일 원본 이름
	private int lectureArchiveNo;//강좌 자료실 고유번호
	private int lectureArchiveFileSize;//첨부파일 크기
	private String lectureArchiveFileType;//첨부파일 타입
	private int lectureArchiveFileCount;//첨부파일 다운로드 횟수
	private String lectureArchiveFileCreateDate;//첨부파일 생성 일시
	
}
