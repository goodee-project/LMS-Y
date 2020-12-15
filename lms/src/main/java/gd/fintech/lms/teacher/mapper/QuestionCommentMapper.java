package gd.fintech.lms.teacher.mapper;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.teacher.vo.QuestionComment;

// 질문게시판 질문글의 댓글들을 관리하는 테이블의 매퍼

@Mapper
public interface QuestionCommentMapper {
	// SELECT 매핑은 QuestionMapper쪽으로 위임함
	
	// 질문에 대한 댓글 생성
	// 매개변수: 댓글 객체, setter를 사용해 추가할 정보 questionNo, accountId, questionCommentContext를 넣을 것
	// 리턴값: 변경된 행 갯수
	int insertQuestionComment(QuestionComment questionComment);
	
	// 질문에 대한 댓글 수정
	// 매개변수: 댓글 객체, setter를 사용해 변경할 행 고유번호 questionCommentNo, 변경할 정보 questionCommentContext를 넣을 것
	// 리턴값: 변경된 행 갯수
	int updateQuestionComment(QuestionComment questionComment);
}
