package gd.fintech.lms.teacher.mapper;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.teacher.vo.MultipleChoiceExample;

// 객관식 문제에 포함된 객관식 보기를 관리하는 테이블의 매퍼

@Mapper
public interface MultipleChoiceExampleMapper {
	// 객관식 보기만을 SELECT하는 기능은 필요없을 것으로 추정되며, MultipleChoiceMapper를 이용해 객관식 문제와 함께 불러올 것
	
	// 객관식 보기 생성
	// 매개변수: 객관식 보기 객체, setter를 사용해 추가할 정보 multipleChoiceNo, multipleChoiceExampleId, multipleChoiceExampleContent를 넣을 것
	// 리턴값: 변경된 행 갯수
	int insertMultipleChoiceExample(MultipleChoiceExample multipleChoiceExample);
	
	// 객관식 보기 수정
	// 매개변수: 객관식 보기 객체, setter를 사용해 변경할 행을 얻기 위한 정보 multipleChoiceNo 및 multipleChoiceExampleId, 변경할 정보 multipleChoiceExampleContent를 넣을 것
	// 리턴값: 변경된 행 갯수
	int updateMultipleChoiceExample(MultipleChoiceExample multipleChoiceExample);
	
	// 객관식 문제 고유번호를 이용해 객관식 보기 삭제 (객관식 문제 삭제 시 보기 일괄삭제용)
	// 매개변수: 삭제할 객관식 보기를 가지고 있는 객관식 문제 고유번호
	// 리턴값: 변경된 행 갯수
	int deleteMultipleChoiceExampleByMultipleChoiceNo(int multipleChoiceNo);
}
