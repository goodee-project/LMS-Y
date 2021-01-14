package gd.fintech.lms.student.mapper;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.student.vo.StudentQuestionCommentFile;


@Mapper
public interface StudentQuestionCommentFileMapper {
	
	// UUID를 이용해 파일 상세정보 출력
	// 매개변수: 질문게시판 댓글에 등록된 첨부파일 UUID
	// 리턴값: 파일 상세정보
	StudentQuestionCommentFile selectQuestionCommentFileDetail(String questionCommentFileUUID);

	// UUID에 해당되는 파일의 다운로드 횟수 1 증가
	// 매개변수: 질문게시판 댓글에 등록된 첨부파일 UUID
	int updateQuestionCommentFileCountIncrease(String questionCommentFileUUID);
}
