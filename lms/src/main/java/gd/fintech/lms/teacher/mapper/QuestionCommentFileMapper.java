package gd.fintech.lms.teacher.mapper;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.teacher.vo.QuestionCommentFile;

// 질문게시판 댓글에 포함된 첨부파일들을 관리하는 테이블의 매퍼

@Mapper
public interface QuestionCommentFileMapper {
	// SELECT 매핑은 QuestionMapper쪽으로 위임함
	
	// 질문에 대한 댓글의 첨부파일 생성
	// 매개변수: 첨부파일 객체, setter를 사용해 추가할 정보 questionCommentFileUUID, questionCommentFileOriginal, questionCommentNo, questionCommentFileSize, questionCommentFileType을 넣을 것
	// 리턴값: 변경된 행 갯수
	int insertQuestionCommentFile(QuestionCommentFile questionCommentFile);
	
	// 질문에 대한 댓글의 첨부파일 삭제
	// 매개변수: 삭제할 첨부파일의 UUID
	// 리턴값: 변경된 행 갯수
	int deleteQuestionCommentFile(int questionCommentFileUUID);
}
