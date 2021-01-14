package gd.fintech.lms.manager.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

// 운영자가 보는 차트 데이터 Mapper

@Mapper
public interface ManagerChartMapper {
	// 강사와 학생의 인원수를 각각 출력
	// 매개변수: 없음
	// 리턴값: 강사와 학생의 인원수
	// 계정 상태가 활성화 상태인 계정만 출력
	Map<String, Object> selectTeacherAndStudentCount();
}
