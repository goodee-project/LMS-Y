package gd.fintech.lms.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

//Multipart 처리를 위한 LectureArhiveForm VO

@Data
public class LectureArchiveForm {
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
	
	//MultipartList 처리를 위한 프로퍼티 추가
	private List<MultipartFile> lectureArchiveFileList;
}
