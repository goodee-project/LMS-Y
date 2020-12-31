package gd.fintech.lms.teacher.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gd.fintech.lms.dto.MultipleChoiceForm;
import gd.fintech.lms.teacher.mapper.MultipleChoiceExampleMapper;
import gd.fintech.lms.teacher.mapper.MultipleChoiceMapper;
import gd.fintech.lms.teacher.mapper.TestMapper;
import gd.fintech.lms.teacher.vo.MultipleChoice;
import gd.fintech.lms.teacher.vo.MultipleChoiceExample;
import gd.fintech.lms.teacher.vo.Test;

// 시험을 관리하는 서비스

@Service
@Transactional
public class TestService {
	// 디버깅 메세지 출력을 위한 로거
	Logger logger = LoggerFactory.getLogger(ReportService.class);
	
	// 시험 정보 관리를 위한 매퍼
	@Autowired private TestMapper testMapper;
	// 시험의 객관식 문제 관리를 위한 매퍼
	@Autowired private MultipleChoiceMapper multipleChoiceMapper;
	// 시험의 객관식 보기 관리를 위한 매퍼
	@Autowired private MultipleChoiceExampleMapper multipleChoiceExampleMapper;
	
	// 해당 강좌에 등록된 시험의 상세 정보를 출력
	// 매개변수: 강좌의 고유번호
	// 리턴값: 시험의 상세 정보
	public Test getTestDetail(int lectureNo) {
		return testMapper.selectTestDetail(lectureNo);
	}

	// 시험 정보 생성
	// 매개변수: 시험 정보 객체, setter를 사용해 추가할 정보 lectureNo, testStartDate, testEndDate, testContent를 넣을 것
	public void createTest(Test test) {
		logger.debug("test = "+test.toString());
		
		testMapper.insertTest(test);
	}

	// 시험 정보 수정
	// 매개변수: 시험 정보 객체, setter를 사용해 변경할 행 고유번호 lectureNo, 변경할 정보 testStartDate, testEndDate, testContent를 넣을 것
	public void modifyTest(Test test) {
		logger.debug("test = "+test.toString());
		
		testMapper.updateTest(test);
	}

	// 해당 시험의 객관식 문제 및 보기 리스트 출력 (정답 제외)
	// 매개변수: 강좌 고유번호
	// 리턴값: (시험의 정답 제외 및 객관식 보기를 포함한 객관식 문제 리스트+시험이 수정 가능한지 여부)를 출력하는 Map
	public Map<String, Object> getMultipleChoiceList(int lectureNo) {
		List<MultipleChoice> list = multipleChoiceMapper.selectMultipleChoiceList(lectureNo);
		
		// 시험 출제 기간인지 파악하기 위한 Test 객체
		Test test = testMapper.selectTestDetail(lectureNo);
		boolean isEditable = true; // 시험 평가 기간인지 확인하는 용도의 변수
		
		// 시험 평가 기간 내에 속해있는지 확인하기 위해 Date타입으로 현재 날짜와 test에 기입된 날짜를 변환
		// 변환 도중 예외 발생 시 스택트레이싱 후 Transactional 애노테이션에게 예외 발생을 알림
		try {
			SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
			
			Date currentDate = Calendar.getInstance().getTime();
			
			
			if (fmt.parse(test.getTestStartDate()).getTime() < currentDate.getTime()) {
				// 시험 평가 기간이거나 지났을 경우 시험 문제는 수정할수 없도록 플래그 변경
				isEditable = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("list", list);
		map.put("isEditable", isEditable);
		
		return map;
	}

	// 해당 시험의 단일 객관식 문제 상세 정보 출력 (정답 포함)
	// 매개변수: 객관식 문제 고유번호
	// 리턴값: 시험의 객관식 보기를 제외하고, 정답을 포함한 객관식 문제의 상세 정보
	public MultipleChoice getMultipleChoiceDetail(int multipleChoiceNo) {
		return multipleChoiceMapper.selectMultipleChoiceDetail(multipleChoiceNo);
	}

	// 객관식 문제 생성
	// 매개변수: 객관식 문제 객체, setter를 사용해 추가할 정보 lectureNo, multipleChoiceId, multipleChoiceQuestion, multipleChoiceAnswer, multipleChoiceScore를 넣을 것
	public void createMultipleChoice(MultipleChoiceForm multipleChoiceForm) {
		logger.debug("multipleChoiceForm = "+multipleChoiceForm.toString());
		
		MultipleChoice multipleChoice = new MultipleChoice();
		multipleChoice.setLectureNo(multipleChoiceForm.getLectureNo());
		multipleChoice.setMultipleChoiceId(multipleChoiceMapper.selectMultipleChoiceCount(multipleChoiceForm.getLectureNo())+1);
		multipleChoice.setMultipleChoiceQuestion(multipleChoiceForm.getMultipleChoiceQuestion());
		multipleChoice.setMultipleChoiceAnswer(multipleChoiceForm.getMultipleChoiceAnswer());
		multipleChoice.setMultipleChoiceScore(multipleChoiceForm.getMultipleChoiceScore());
		multipleChoiceMapper.insertMultipleChoice(multipleChoice);
		
		int id = 1;
		for (String content : multipleChoiceForm.getMultipleChoiceExampleList()) {
			MultipleChoiceExample multipleChoiceExample = new MultipleChoiceExample();
			multipleChoiceExample.setMultipleChoiceNo(multipleChoice.getMultipleChoiceNo());
			multipleChoiceExample.setMultipleChoiceExampleId(""+id);
			multipleChoiceExample.setMultipleChoiceExampleContent(content);
			multipleChoiceExampleMapper.insertMultipleChoiceExample(multipleChoiceExample);
			
			id += 1;
		}
	}

	// 객관식 문제 수정
	// 매개변수: 객관식 문제 객체, setter를 사용해 변경할 행 고유번호 multipleChoiceNo, 변경할 정보 multipleChoiceId, multipleChoiceQuestion, multipleChoiceAnswer, multipleChoiceScore를 넣을 것
	public void modifyMultipleChoice(MultipleChoiceForm multipleChoiceForm) {
		logger.debug("multipleChoiceForm = "+multipleChoiceForm.toString());
		
		MultipleChoice multipleChoice = new MultipleChoice();
		multipleChoice.setMultipleChoiceNo(multipleChoiceForm.getMultipleChoiceNo());
		multipleChoice.setMultipleChoiceQuestion(multipleChoiceForm.getMultipleChoiceQuestion());
		multipleChoice.setMultipleChoiceAnswer(multipleChoiceForm.getMultipleChoiceAnswer());
		multipleChoice.setMultipleChoiceScore(multipleChoiceForm.getMultipleChoiceScore());
		multipleChoiceMapper.updateMultipleChoice(multipleChoice);
		
		int id = 1;
		for (String content : multipleChoiceForm.getMultipleChoiceExampleList()) {
			MultipleChoiceExample multipleChoiceExample = new MultipleChoiceExample();
			multipleChoiceExample.setMultipleChoiceNo(multipleChoice.getMultipleChoiceNo());
			multipleChoiceExample.setMultipleChoiceExampleId(""+id);
			multipleChoiceExample.setMultipleChoiceExampleContent(content);
			multipleChoiceExampleMapper.updateMultipleChoiceExample(multipleChoiceExample);
			
			id += 1;
		}
	}

	// 객관식 문제 삭제
	// 매개변수: 삭제할 객관식 문제의 고유번호
	public void removeMultipleChoice(int multipleChoiceNo) {
		MultipleChoice multipleChoice = multipleChoiceMapper.selectMultipleChoiceDetail(multipleChoiceNo);
		logger.debug("multipleChoice = "+multipleChoice);
		
		int originId = multipleChoice.getMultipleChoiceId();
		
		multipleChoiceExampleMapper.deleteMultipleChoiceExampleByMultipleChoiceNo(multipleChoiceNo);
		multipleChoiceMapper.deleteMultipleChoice(multipleChoiceNo);
		
		multipleChoiceMapper.updateMultipleChoiceIdSubstractBelow(originId);
	}
	
	// 객관식 보기 생성
	// 매개변수: 객관식 보기 객체, setter를 사용해 추가할 정보 multipleChoiceNo, multipleChoiceExampleId, multipleChoiceExampleContent를 넣을 것
	public void createMultipleChoiceExample(MultipleChoiceExample multipleChoiceExample) {
		logger.debug("multipleChoiceExample = "+multipleChoiceExample.toString());
		
		multipleChoiceExampleMapper.insertMultipleChoiceExample(multipleChoiceExample);
	}

	// 객관식 보기 수정
	// 매개변수: 객관식 보기 객체, setter를 사용해 변경할 행 고유번호 multipleChoiceExampleNo, 변경할 정보 multipleChoiceExampleId, multipleChoiceExampleContent를 넣을 것
	public void modifyMultipleChoiceExample(MultipleChoiceExample multipleChoiceExample) {
		logger.debug("multipleChoiceExample = "+multipleChoiceExample.toString());
		
		multipleChoiceExampleMapper.updateMultipleChoiceExample(multipleChoiceExample);
	}
}
