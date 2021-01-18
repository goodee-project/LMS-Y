package gd.fintech.lms.manager.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.manager.vo.Classroom;
import gd.fintech.lms.manager.vo.Lecture;
import gd.fintech.lms.manager.vo.Subject;
import gd.fintech.lms.manager.vo.Textbook;
import gd.fintech.lms.student.vo.ClassRegistration;
import gd.fintech.lms.teacher.vo.Teacher;

// 운영자의 강좌에 대한 메퍼 인터페이스

@Mapper
public interface LectureManagerMapper {
	// 강좌 리스트
	// 매개변수: Map
	// 리턴값: 강좌의 목록
	List<Lecture> selectLectureListByPage(Map<String, Object> map);
	
	// 강좌에서의 강의실 리스트
	// 리턴값: 강의실 목록
	List<Classroom> selectLectureClassroomList();
	
	// 강좌에서의 교재 리스트
	// 리턴값: 교재목록
	List<Textbook> selectLectureTextbookList();
	
	// 강좌에서의 과목 리스트
	// 리턴값: 과목 목록
	List<Subject> selectLectureSubjectList();
	
	// 강좌에서의 강사 리스트
	// 리턴값: 강사 목록
	List<Teacher> selectLectureTeacherList();
	
	// 강좌에서의 강사이름 검색
	// 매개변수: 강사의 계정id
	// 리턴값: 강사의 계정id에 해당 하는 강사이름
	List<String> selectTeacherName(String accountId);
	
	// 강좌 개수
	// 리턴값: 행의 총 개수
	int selectLectureCount();
	
	// 강좌 입력
	// 매개변수: 강좌의 정보
	// 리턴값:  행의 추가
	int insertLecture(Lecture lecture);
	
	// 강좌 삭제
	// 매개변수: 강좌의 번호
	// 리턴값:  행의 삭제
	int deleteLecture(int lectureNo);
	
	// 수강 대기중인 리스트
	// 매개변수: 강좌no
	//  리턴값: 강좌 no에 해당하는 수강신청한 학생 목록
	List<ClassRegistration> selectlectureStudentList(int lectureNo);
	
	// 상태가 수강증으로 바꾸는 쿼리
	// 매개변수 : 학생id  강좌no
	//  리턴값 : 수강중으로 상태 변경
	int  updatelectureStudentCk(ClassRegistration classRegistration);
	
	// 상태가 거절로 바꾸는 쿼리
	// 매개변수 : 학생id  강좌no
	// 리턴값: 거절로 상태 변경
	int  updatelectureStudentReject(ClassRegistration classRegistration);
	
	
	
	// 강좌 수정
	// 매개변수: 강좌의 정보
	// 리턴값: 행의 수정
	int updateLecture(Lecture lecture);
	
	// 강사 이름을 변경 하기전에 확인
	// 매개변수: 강사 id 와 강사이름
	// 리턴값: 강사id 에 해당하는 강사 id,강사 이름
	String selectTeacherNameCk (String accountId, String teacherName);
	
	// 강좌 번호를 이용한 강좌 상세보기
	// 매개변수: 강좌의 번호
	// 리턴값: 행의 상세정보
	Lecture selectLectureDetail(int lectureNo);
	
	// accountId 를 이용한 강좌 리스트
	// 매개변수: accountId
	// 리턴값: accountId에 해당하는 강좌 리스트
	List<Lecture> selectTeacherLectureDetail(String accountId);
}
