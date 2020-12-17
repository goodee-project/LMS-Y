package gd.fintech.lms.teacher.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.teacher.vo.MultipleChoice;

// 강의별 시험에 포함된 객관식 문제를 관리하는 테이블의 매퍼

@Mapper
public interface MultipleChoiceMapper {
	// TODO MultipleChoiceService에 객관식 문제 생성 메서드 작성 시 객관식 보기들도 생성할 것
	
	// 해당 강의에서 출제한 시험의 객관식 문제 및 보기들을 출력 (정답 제외)
	// 매개변수: 시험이 등록된 강좌의 번호
	// 리턴값: 객관식 문제들의 리스트
	List<MultipleChoice> selectMultipleChoiceList(int lectureNo);
	
	// 객관식 문제 고유번호에 해당하는 문제의 정보를 출력 (정답 포함)
	// 매개변수: 객관식 문제 고유번호
	// 리턴값: 해당 객관식 문제 객체 하나
	MultipleChoice selectMultipleChoiceOne(int multipleChoiceNo);
	
	// 해당 강의에서 출제한 시험의 객관식 문제 갯수 출력
	// 매개변수: 시험이 등록된 강좌의 번호
	// 리턴값: 객관식 문제 갯수
	int selectMultipleChoiceCount(int lectureNo);
	
	// 객관식 문제 생성
	// 매개변수: 객관식 문제 객체, setter를 사용해 추가할 정보 lectureNo, multipleChoiceId, multipleChoiceQuestion, multipleChoiceAnswer, multipleChoiceScore를 넣을 것
	// 리턴값: 변경된 행 갯수
	int insertMultipleChoice(MultipleChoice multipleChoice);
	
	// 객관식 문제 수정
	// 매개변수: 객관식 문제 객체, setter를 사용해 변경할 행 고유번호 multipleChoiceNo, 변경할 정보 multipleChoiceId, multipleChoiceQuestion, multipleChoiceAnswer, multipleChoiceScore를 넣을 것
	// 리턴값: 변경된 행 갯수
	int updateMultipleChoice(MultipleChoice multipleChoice);
	
	// 객관식 문제 삭제
	// 매개변수: 삭제할 객관식 문제의 고유번호
	// 리턴값: 변경된 행 갯수
	int deleteMultipleChoice(int multipleChoiceNo);
}
