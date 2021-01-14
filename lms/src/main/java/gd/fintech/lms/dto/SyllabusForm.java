package gd.fintech.lms.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

// 강의계획서 DTO

@Data
public class SyllabusForm {
	// 강의계획서 고유번호(강좌 고유번호와 동일)
	private int lectureNo;
	
	// 강의계획서 내용
	private String syllabusContent;
		
	// 강의계획서 첨부파일 목록
	private List<MultipartFile> syllabusFileList;
}
