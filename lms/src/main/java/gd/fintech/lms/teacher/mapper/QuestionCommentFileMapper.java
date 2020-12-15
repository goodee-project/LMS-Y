package gd.fintech.lms.teacher.mapper;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.teacher.vo.QuestionCommentFile;

@Mapper
public interface QuestionCommentFileMapper {
	// SELECT 매핑은 QuestionMapper쪽으로 위임함
	
	int insertQuestionCommentFile(QuestionCommentFile questionCommentFile); // 질문에 대한 댓글의 첨부파일 생성
	int deleteQuestionCommentFile(int questionCommentFileNo); // 질문에 대한 댓글의 첨부파일 삭제
	int deleteQuestionCommentFileByQuestionCommentNo(int questionCommentNo); // 댓글 고유번호를 이용해 질문에 대한 댓글의 첨부파일 삭제
}
