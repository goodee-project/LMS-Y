package gd.fintech.lms.student.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gd.fintech.lms.student.mapper.AnswerSheetMapper;
import gd.fintech.lms.student.vo.AnswerSheet;

@Service
@Transactional
public class AnswerSheetService {
	@Autowired AnswerSheetMapper answerSheetMapper;
	
	//학생이 낼 답안지 입력
	//매개변수:답안지 Vo +session
	//리턴값:입력할 답안지의 양식
	public AnswerSheet addAnswerSheet(AnswerSheet answerSheet) {
		return answerSheetMapper.insertAnswerSheet(answerSheet);
	}
	
	//학생의 답안지 상세보기(+점수)
	//매개변수:선택한 답안지 보기 +session
	//리턴값:학생의 답안지 상세보기
	public AnswerSheet getAnswerSheetOne(String accountId) {
		return answerSheetMapper.selectAnswerSheetOne(accountId);
		//TODO 학생의 답지 점수 출력
	}
}
