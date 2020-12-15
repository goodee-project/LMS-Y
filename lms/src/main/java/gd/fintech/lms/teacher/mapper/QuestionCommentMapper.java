package gd.fintech.lms.teacher.mapper;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.teacher.vo.QuestionComment;
import gd.fintech.lms.teacher.vo.QuestionCommentFile;

@Mapper
public interface QuestionCommentMapper {
	// SELECT 매핑은 QuestionMapper쪽으로 위임함
	
	int insertQuestionComment(QuestionComment questionComment); // 질문에 대한 댓글 생성
	int updateQuestionComment(QuestionComment questionComment); // 질문에 대한 댓글 수정
}
