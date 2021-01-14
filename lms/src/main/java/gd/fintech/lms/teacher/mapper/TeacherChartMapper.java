package gd.fintech.lms.teacher.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.manager.vo.Lecture;

// 강사 차트 mapper

@Mapper
public interface TeacherChartMapper {
	// 계정에 해당되는 강좌 no, 강좌 이름 가져오기
	// 매개변수 : 계정ID
	// 리턴값 : 강좌에 대한 번호와 이름 리스트 출력
	public List<Lecture> selectLectureListByAccountId(String accountId);
	
	// 계정에 디폴트 강좌를 가져옴
	// 매개변수 : 계정ID
	// 리턴값 : 디폴트 강좌번호 
	public int selectDefaultLecture(String accountId);
	
	// 강좌별 과제 제출률
	// 매개변수 : 강좌번호
	// 리턴값 : 강사에게 해당된 강좌별 과제 제출률
	public List<Map<String, Object>> selectReportSubmitRateByLecture(int lectureNo);
	
	// 강좌별 평가 정답률
	// 매개변수 : 강좌번호
	// 리턴값 : 강좌에 해당되는 문제별 정답률
	public List<Map<String, Object>> selectTestAnswerRateByLecture(int lectureNo);
}
