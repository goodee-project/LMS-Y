package gd.fintech.lms.student.mapper;



import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.student.vo.AnswerSheet;
import gd.fintech.lms.student.vo.MultipleChoice;

// 학생이 내는 답안지 

@Mapper
public interface AnswerSheetMapper {
	// 시험문제 리스트 출력(정답제외)
	// 매개변수: 답안을 제출할 시험의 강좌번호
	// 리턴값: 강좌번호에 해당되는 시험문제 리스트
	List<MultipleChoice> selectMultipleChoiceList(int lectureNo);
	
	// 학생이 낸 답지 상세보기
	// 매개변수: Map.put()을 사용해 답안이 제출된 시험의 강좌번호 lectureNo, 학생의 ID accountId를 넣을 것
	// 리턴값: ((제출한 답안지+답안지에 해당되는 문제)가 합쳐진 Map)의 List
	// List(HashMap(answerSheet=답안지, multipleChoice=답안지에 해당되는 문제)) 구조를 가짐
	List<Map<String, Object>> selectAnswerSheetList(Map<String, Object> map);
	
	// 학생이 낼 답안지 입력
	// 매개변수: 입력구문에 필요한 answersheet컬럼
	// 리턴값: 학생이 낸 답지
	int insertAnswerSheet(AnswerSheet answerSheet);
}
