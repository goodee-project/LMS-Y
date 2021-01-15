package gd.fintech.lms.student.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gd.fintech.lms.manager.mapper.LectureManagerMapper;
import gd.fintech.lms.student.mapper.AnswerSheetMapper;
import gd.fintech.lms.student.vo.AnswerSheet;
import gd.fintech.lms.student.vo.MultipleChoice;
import gd.fintech.lms.teacher.mapper.MultipleChoiceMapper;
import gd.fintech.lms.teacher.mapper.TestMapper;
import gd.fintech.lms.teacher.vo.MultipleChoiceExample;
import gd.fintech.lms.teacher.vo.Test;

// 학생의 시험 및 답안지 제출 서비스

@Service
@Transactional
public class AnswerSheetService {
	// 학생이 시험을 치르는 행위 및 시험 답안지를 제출하는 행위를 관리하는 매퍼
	@Autowired AnswerSheetMapper answerSheetMapper;
	// 시험 제출 기간을 가져오기 위한 시험 매퍼
	@Autowired TestMapper testMapper;
	// 객관식 보기를 가져오기 위한 객관식 문제 매퍼
	@Autowired MultipleChoiceMapper multipleChoiceMapper;
	// 강좌명을 가져오기 위한 강좌 매퍼
	@Autowired LectureManagerMapper lectureManagerMapper;
	
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
			
			// 제출한 답안의 객관식 보기 및 정답의 객관식 보기를 들고 옴
			for (MultipleChoiceExample me : multipleChoiceMapper.selectMultipleChoiceDetail(answerSheet.getMultipleChoiceNo()).getMultipleChoiceExampleList()) {
				if (me.getMultipleChoiceExampleId().equals(answerSheet.getAnswerSelect())) {
					map.put("submitExample", me.getMultipleChoiceExampleContent());
				}
				if (me.getMultipleChoiceExampleId().equals(multipleChoice.getMultipleChoiceAnswer())) {
					map.put("answerExample", me.getMultipleChoiceExampleContent());
				}
			}
		}
		
		Test test = testMapper.selectTestDetail(lectureNo);
		boolean isTestable = true; // 시험 평가 기간인지 확인하는 용도의 변수
		
		// getTestDetailWithDateFormatting() 메서드로 불러올 때 시험이 생성되지 않았다면 건너뛰고 빈 맵을 반환함
		if (test != null) {
			// 시험 평가 기간 내에 속해있는지 확인하기 위해 Date타입으로 현재 날짜와 test에 기입된 날짜를 변환
			// 변환 도중 예외 발생 시 스택트레이싱 후 Transactional 애노테이션에게 예외 발생을 알림
			try {
				SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
				
				Date currentDate = Calendar.getInstance().getTime();
				
				if (fmt.parse(test.getTestStartDate()).getTime() > currentDate.getTime()
				 || fmt.parse(test.getTestEndDate()).getTime() < currentDate.getTime()) {
					// 시험 평가 기간이거나 지났을 경우 시험 문제는 수정할수 없도록 플래그 변경
					isTestable = false;
				}
			} catch (ParseException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		
		System.out.println(lectureManagerMapper.selectLectureDetail(lectureNo));
		
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("mapList", mapList);
		returnMap.put("userTotal", userTotal);
		returnMap.put("multipleChoiceTotal", multipleChoiceTotal);
		returnMap.put("isTestable", isTestable);
		returnMap.put("lectureName", lectureManagerMapper.selectLectureDetail(lectureNo).getLectureName());
		
		return returnMap;
		//TODO 학생의 답지 점수 출력
	}
}
