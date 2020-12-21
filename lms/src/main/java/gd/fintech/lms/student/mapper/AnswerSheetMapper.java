package gd.fintech.lms.student.mapper;



import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.student.vo.AnswerSheet;
// 학생이 내는 답안지 
@Mapper
public interface AnswerSheetMapper {
	
	//학생이(내가) 낼 답안지 입력
	//매개변수:입력구문에 필요한 answerSheet컬럼
	//리턴값: 학생이낸 답지
	AnswerSheet insertAnswerSheet(AnswerSheet answerSheet);
	
	//학생(내가)이 낸 답지 상세보기
	//매개변수: 답안지의 번호
	//리턴값: 해당 답안지의 상세보기
	AnswerSheet selectAnswerSheetOne(String accountId);
	
}
