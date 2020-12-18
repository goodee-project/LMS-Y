package gd.fintech.lms.teacher.mapper;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.teacher.vo.Test;

// 강좌와 연결된 시험을 관리하는 테이블의 매퍼

@Mapper
public interface TestMapper {
	// 해당 강의의 시험을 출력 (문제 목록 및 보기 목록은 MultipleChoiceMapper에게 위임)
	// 매개변수: 가져올 시험을 생성한 강좌의 고유번호
	// 리턴값: 해당 시험 객체
	Test selectTestDetail(int lectureNo);
	
	// 시험 생성
	// 매개변수: 시험 객체, setter를 사용해 추가할 정보 lectureNo, testStartDate, testEndDate, testContent를 넣을 것
	// 리턴값: 변경된 행 갯수
	int insertTest(Test test);
	
	// 시험 수정
	// 매개변수: 시험 객체, setter를 사용해 변경할 행 고유번호 lectureNo, 변경할 정보 testStartDate, testEndDate, testContent를 넣을 것
	// 리턴값: 변경된 행 갯수
	int updateTest(Test test);
}
