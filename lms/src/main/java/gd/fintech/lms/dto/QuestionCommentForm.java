package gd.fintech.lms.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class QuestionCommentForm {
	// 덧글 고유번호
	private int questionCommentNo;
	
	// 덧글을 등록한 특정 질문의 고유번호
	private int questionNo;
	
	// 덧글 내용
	private String questionCommentContent;
	
	// 해당 질문을 참조하고 있는 첨부파일 리스트
	private List<MultipartFile> questionCommentFileList;
}
