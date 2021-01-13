package gd.fintech.lms.student.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gd.fintech.lms.student.mapper.AnswerSheetMapper;
import gd.fintech.lms.student.vo.AnswerSheet;
import gd.fintech.lms.student.vo.MultipleChoice;

// 학생의 시험 및 답안지 제출 서비스

@Service
@Transactional
public class AnswerSheetService {
	// 학생이 시험을 치르는 행위 및 시험 답안지를 제출하는 행위를 관리하는 매퍼
	@Autowired AnswerSheetMapper answerSheetMapper;
	
	// 객관식 문제 목록 출력
	// 매개변수: 선택한 답안지 보기 +session
	// 리턴값: 학생의 답안지 상세보기
	public List<MultipleChoice> getMultipleChoiceList(int lectureNo) {
		return answerSheetMapper.selectMultipleChoiceList(lectureNo);
	}
	
	// 학생이 낼 답안지 입력
	// 매개변수: 답안지 Vo +session
	// 리턴값: 입력할 답안지의 양식
	public void createAnswerSheet(int lectureNo, Map<String, String[]> paramMap, HttpSession session) {
		for (Map.Entry<String, String[]> entry : paramMap.entrySet()) {
			String key = entry.getKey();
			for (String value : entry.getValue()) {
				if (key.startsWith("multipleChoiceNo-")) {
					AnswerSheet answerSheet = new AnswerSheet();
					answerSheet.setAccountId((String)session.getAttribute("accountId"));
					answerSheet.setMultipleChoiceNo(Integer.parseInt(key.replace("multipleChoiceNo-", "")));
					answerSheet.setAnswerSelect(value);
					
					answerSheetMapper.insertAnswerSheet(answerSheet);
				}
			}
		}
	}
	
	// 학생의 답안지 상세보기(+점수)
	// 매개변수: 선택한 답안지 보기 +session
	// 리턴값: 학생의 답안지 상세보기
	public Map<String, Object> getAnswerSheetList(int lectureNo, HttpSession session) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("lectureNo", lectureNo);
		paramMap.put("accountId", session.getAttribute("accountId"));
		List<Map<String, Object>> mapList = answerSheetMapper.selectAnswerSheetList(paramMap);
		// 가져온 리스트는 다음과 같은 형태를 가짐
		// List(HashMap(answerSheet=답안지, multipleChoice=답안지에 해당되는 문제)) 구조를 가짐
		
		// 합계 점수를 같이 출력할 수 있도록 계산함
		int userTotal = 0; // 답안지의 합계 점수(총점)를 기록함
		int multipleChoiceTotal = 0; // 문제 배점의 합산을 구함
		for (Map<String, Object> map : mapList) {
			AnswerSheet answerSheet = (AnswerSheet)map.get("answerSheet");
			MultipleChoice multipleChoice = (MultipleChoice)map.get("multipleChoice");
			
			userTotal += answerSheet.getAnswerScore();
			multipleChoiceTotal += multipleChoice.getMultipleChoiceScore();
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("mapList", mapList);
		map.put("userTotal", userTotal);
		map.put("multipleChoiceTotal", multipleChoiceTotal);
		
		return map;
		//TODO 학생의 답지 점수 출력
	}
}
