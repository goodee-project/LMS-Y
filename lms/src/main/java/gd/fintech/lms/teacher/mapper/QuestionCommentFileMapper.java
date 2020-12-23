package gd.fintech.lms.teacher.mapper;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.teacher.vo.QuestionCommentFile;

// 질문게시판 댓글에 포함된 첨부파일들을 관리하는 테이블의 매퍼

@Mapper
public interface QuestionCommentFileMapper {
	// SELECT 매핑은 QuestionMapper쪽으로 위임함
	
	// UUID를 이용해 파일 상세정보 출력
	// 매개변수: 질문게시판 댓글에 등록된 첨부파일 UUID
	// 리턴값: 파일 상세정보
	QuestionCommentFile selectQuestionCommentFileDetail(String questionCommentFileUUID);

	// UUID에 해당되는 파일의 다운로드 횟수 1 증가
	// 매개변수: 질문게시판 댓글에 등록된 첨부파일 UUID
	int updateQuestionCommentFileCountIncrease(String questionCommentFileUUID);
	
	// 질문에 대한 댓글의 첨부파일 생성
	// 매개변수: 첨부파일 객체, setter를 사용해 추가할 정보 questionCommentFileUUID, questionCommentFileOriginal, questionCommentNo, questionCommentFileSize, questionCommentFileType을 넣을 것
	// 리턴값: 변경된 행 갯수
	int insertQuestionCommentFile(QuestionCommentFile questionCommentFile);
	
	// 질문에 대한 댓글의 첨부파일 삭제
	// 매개변수: 삭제할 첨부파일의 UUID
	// 리턴값: 변경된 행 갯수
	int deleteQuestionCommentFile(int questionCommentFileUUID);
}
