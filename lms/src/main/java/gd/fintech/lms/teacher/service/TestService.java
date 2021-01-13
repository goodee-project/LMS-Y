package gd.fintech.lms.teacher.service;

import java.text.ParseException;
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
	
	// 해당 강좌에 등록된 시험의 상세 정보 및 객관식 문제와 보기 및 정답 출력
	// 매개변수: 강좌의 고유번호
	// 리턴값: 시험의 상세 정보와 객관식 문제, 보기
	public Map<String, Object> getTestDetailWithMultipleChoice(int lectureNo) {
		Map<String, Object> map = this.getMultipleChoiceList(lectureNo);
		map.put("test", testMapper.selectTestDetail(lectureNo));
		
		return map;
	}
	
	// 해당 강좌에 등록된 시험의 상세 정보에서 날짜 포맷을 yyyy-MM-dd(시분초 제외)로 변경 후 출력 
	// 매개변수: 강좌의 고유번호
	// 리턴값: 시험의 상세 정보
	public Test getTestDetailWithDateFormatting(int lectureNo) {
		Test test = testMapper.selectTestDetail(lectureNo);
		
		// HTML이 읽을 수 있는 날짜데이터로 변경하기 위해 hh:MM:ss.SSS(시분초 및 밀리초) 부분을 없앰
		test.setTestStartDate(test.getTestStartDate().replaceAll("\\s*\\d+:\\d+:\\d+\\.\\d+$", ""));
		test.setTestEndDate(test.getTestEndDate().replaceAll("\\s*\\d+:\\d+:\\d+\\.\\d+$", ""));
		
		return test;
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

	// 해당 시험의 객관식 문제 및 보기 리스트 출력 (정답 포함)
	// 매개변수: 강좌 고유번호
	// 리턴값: (시험의 정답 제외 및 객관식 보기를 포함한 객관식 문제 리스트+시험이 수정 가능한지 여부)를 출력하는 Map
	public Map<String, Object> getMultipleChoiceList(int lectureNo) {
		List<MultipleChoice> list = multipleChoiceMapper.selectMultipleChoiceList(lectureNo);
		
		// 시험 출제 기간인지 파악하기 위한 Test 객체
		Test test = testMapper.selectTestDetail(lectureNo);
		boolean isEditable = true; // 시험 평가 기간인지 확인하는 용도의 변수
		
		// getTestDetailWithDateFormatting() 메서드로 불러올 때 시험이 생성되지 않았다면 건너뛰고 빈 맵을 반환함
		if (test != null) {
			// 시험 평가 기간 내에 속해있는지 확인하기 위해 Date타입으로 현재 날짜와 test에 기입된 날짜를 변환
			// 변환 도중 예외 발생 시 스택트레이싱 후 Transactional 애노테이션에게 예외 발생을 알림
			try {
				SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
				
				Date currentDate = Calendar.getInstance().getTime();
				
				if (fmt.parse(test.getTestStartDate()).getTime() < currentDate.getTime()) {
					// 시험 평가 기간이거나 지났을 경우 시험 문제는 수정할수 없도록 플래그 변경
					isEditable = false;
				}
			} catch (ParseException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
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
		
		// DTO를 VO로 변환 후 객관식 문제 추가
		MultipleChoice multipleChoice = new MultipleChoice();
		multipleChoice.setLectureNo(multipleChoiceForm.getLectureNo());
		multipleChoice.setMultipleChoiceId(multipleChoiceMapper.selectMultipleChoiceCount(multipleChoiceForm.getLectureNo())+1);
		multipleChoice.setMultipleChoiceQuestion(multipleChoiceForm.getMultipleChoiceQuestion());
		multipleChoice.setMultipleChoiceAnswer(multipleChoiceForm.getMultipleChoiceAnswer());
		multipleChoice.setMultipleChoiceScore(multipleChoiceForm.getMultipleChoiceScore());
		multipleChoiceMapper.insertMultipleChoice(multipleChoice);
		
		// 1번부터 5번까지의 보기를 받아와서 각 객관식 보기 번호를 기입하고, 위에서 생성한 객관식 문제에 보기 등록
		int id = 1;
		for (String content : multipleChoiceForm.getMultipleChoiceExampleList()) {
			MultipleChoiceExample multipleChoiceExample = new MultipleChoiceExample();
			multipleChoiceExample.setMultipleChoiceNo(multipleChoice.getMultipleChoiceNo());
			multipleChoiceExample.setMultipleChoiceExampleId(""+id);
			multipleChoiceExample.setMultipleChoiceExampleContent(content);
			multipleChoiceExampleMapper.insertMultipleChoiceExample(multipleChoiceExample);
			
			id += 1;
		}
		
		// 작성된 문제 갯수를 기반으로 각 문제의 배점 도출
		multipleChoiceMapper.updateMultipleChoiceScore(multipleChoiceForm.getLectureNo());
	}

	// 객관식 문제 수정
	// 매개변수: 객관식 문제 객체, setter를 사용해 변경할 행 고유번호 multipleChoiceNo, 변경할 정보 multipleChoiceId, multipleChoiceQuestion, multipleChoiceAnswer, multipleChoiceScore를 넣을 것
	public void modifyMultipleChoice(MultipleChoiceForm multipleChoiceForm) {
		logger.debug("multipleChoiceForm = "+multipleChoiceForm.toString());

		// DTO를 VO로 변환 후 객관식 문제 수정
		MultipleChoice multipleChoice = new MultipleChoice();
		multipleChoice.setMultipleChoiceNo(multipleChoiceForm.getMultipleChoiceNo());
		multipleChoice.setMultipleChoiceQuestion(multipleChoiceForm.getMultipleChoiceQuestion());
		multipleChoice.setMultipleChoiceAnswer(multipleChoiceForm.getMultipleChoiceAnswer());
		multipleChoice.setMultipleChoiceScore(multipleChoiceForm.getMultipleChoiceScore());
		multipleChoiceMapper.updateMultipleChoice(multipleChoice);

		// 1번부터 5번까지의 보기를 받아와서 각 객관식 보기 번호를 기입하고, 해당 보기 번호를 통해 보기 내용 수정
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
		logger.debug("multipleChoice = "+multipleChoice); // 삭제되는 객관식 문제 정보 출력
		
		// 삭제 후 밀려버린 문제번호를 당겨오기 위한 기준점
		// 예를 들어 3번 문제를 삭제하면 1번, 2번, 4번, 5번, 6번... 처럼 3번이 비기에 이후 번호를 한칸씩 당겨와야함
		int originId = multipleChoice.getMultipleChoiceId();
		
		// 객관식 문제와 외래키로 연결된 객관식 보기부터 삭제 후, 객관식 문제 삭제
		multipleChoiceExampleMapper.deleteMultipleChoiceExampleByMultipleChoiceNo(multipleChoiceNo);
		multipleChoiceMapper.deleteMultipleChoice(multipleChoiceNo);
		
		// 위에 언급했던 문제번호 밀림 현상을 해결해줌
		multipleChoiceMapper.updateMultipleChoiceIdSubstractBelow(originId);
		
		// 작성된 문제 갯수를 기반으로 각 문제의 배점 도출
		multipleChoiceMapper.updateMultipleChoiceScore(multipleChoice.getLectureNo());
	}
}
