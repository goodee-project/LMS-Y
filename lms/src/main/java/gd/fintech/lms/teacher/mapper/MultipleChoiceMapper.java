package gd.fintech.lms.teacher.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.teacher.vo.MultipleChoice;

@Mapper
public interface MultipleChoiceMapper {
	// TODO selectMultipleChoiceList 메서드의 XML 매핑 시 resultMap을 이용하여 객관식 보기 목록도 함께 매핑할 것
	List<MultipleChoice> selectMultipleChoiceList(int lectureNo); // 해당 강의에서 출제한 시험의 객관식 문제 및 보기들을 출력 (정답 제외)
	MultipleChoice selectMultipleChoiceOne(int multipleChoiceNo); // 객관식 문제 고유번호에 해당하는 문제의 정보를 출력 (정답 포함)
	
	// TODO MultipleChoiceService에 객관식 문제 생성 메서드 작성 시 객관식 보기들도 생성할 것
	int insertMultipleChoice(MultipleChoice multipleChoice); // 객관식 문제 생성
	int updateMultipleChoice(MultipleChoice multipleChoice); // 객관식 문제 수정
	int deleteMultipleChoice(int multipleChoiceNo); // 객관식 문제 삭제
}
