package gd.fintech.lms.teacher.vo;

import java.util.List;

import lombok.Data;


//강좌 자료실 VO
//강좌별 자료실 게시판으로 사용함

@Data
public class LectureArchive {
	//강좌 자료실 고유번호
	private int lectureArchiveNo;
	
	//강좌 고유번호
	private int lectureNo;
	
	//계정 아이디(강사)
	private String accountId;
	
	//작성자 이름(강사)
	private String lectureArchiveWriter;
	
	//강좌 자료실 제목
	private String lectureArchiveTitle;
	
	//강좌 자료실 내용
	private String lectureArchiveContent;
	
	//강좌 자료실 정보 생성 일시
	private String lectureArchiveCreateDate;
	
	//강좌 자료실 정보 업데이트 일시
	private String lectureArchiveUpdateDate;
	
	//강좌 자료실 조회수
	private int lectureArchiveCount;
	
	//Multipart를 위한 프로퍼티 추가
	private List<LectureArchiveFile> lectureArchiveFileList;
}
