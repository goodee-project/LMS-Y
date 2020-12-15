package gd.fintech.lms.teacher.mapper;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.teacher.vo.MultipleChoiceExample;

@Mapper
public interface MultipleChoiceExampleMapper {
	// 객관식 보기만을 SELECT하는 기능은 필요없을 것으로 추정되며, MultipleChoiceMapper를 이용해 객관식 문제와 함께 불러올 것
	
	int insertMultipleChoiceExample(MultipleChoiceExample multipleChoiceExample); // 객관식 보기 생성
	int updateMultipleChoiceExample(MultipleChoiceExample multipleChoiceExample); // 객관식 보기 수정
	int deleteMultipleChoiceExample(int multipleChoiceExampleNo); // 객관식 보기 삭제
	int deleteMultipleChoiceExampleByMultipleChoiceNo(int multipleChoiceNo); // 객관식 문제 고유번호를 이용해 객관식 보기 삭제
}
