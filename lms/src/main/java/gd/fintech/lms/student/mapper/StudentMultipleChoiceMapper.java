package gd.fintech.lms.student.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.student.vo.MultipleChoice;

@Mapper
public interface StudentMultipleChoiceMapper {
	
	//강좌별 시험 리스트
	//매개변수:강좌 번호,현재 페이지
	//리턴값: 강좌별 객관식 문제 리스트
	List<MultipleChoice> selectStudentMultipleListByPage(Map<String,Object>map);
	
	//강좌별 시험 리스트 갯수
	//매개변수:
	//리턴값: 강좌별 객관식 문제 갯수
	int selectMultipleCount(int lecturNo);
}
