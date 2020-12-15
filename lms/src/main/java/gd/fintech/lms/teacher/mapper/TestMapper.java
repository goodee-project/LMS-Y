package gd.fintech.lms.teacher.mapper;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.teacher.vo.Test;

@Mapper
public interface TestMapper {
	Test selectTestOne(int lectureNo); // 해당 강의의 시험을 출력 (문제 목록 및 보기 목록은 MultipleChoiceMapper에게 위임)
	
	int insertTest(Test test); // 시험 생성
	int updateTest(Test test); // 시험 수정
}
