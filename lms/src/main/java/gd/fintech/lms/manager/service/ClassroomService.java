package gd.fintech.lms.manager.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gd.fintech.lms.manager.mapper.ClassroomMapper;
import gd.fintech.lms.manager.vo.Classroom;


// Classroom 을 관리하는 서비스 

@Service
@Transactional
public class ClassroomService {
	
	
	// Classroom 에 대한 메퍼
	@Autowired private ClassroomMapper classroomMapper;
	
	
	// 강의실의 리스트를 출력하는 서비스
	// 리턴값: 강의실의 리스트
	public List<Classroom> getClassroomList(){
		return classroomMapper.selectClassroomList();
	}
	
	// 강의실 추가하는 서비스 
	// 매개변수: 강의실의 정보 
	// 리턴값:  입력한 정보의 행 추가 
	public int createClassroom(Classroom classroom) {
		return classroomMapper.insertClassromm(classroom);
	}
	
	
	// 강의실 수정하는 서비스
	// 매개변수: 강의실의 정보 
	// 리턴값:  입력한 정보의 행 수정 
	public int modifyClassroom(Classroom classroom){
		return classroomMapper.updateClassroom(classroom);
	}
	
	
	// 강의실 삭제하는 서비스
	// 매개변수: 강의실 고유번호 
	// 리턴값: 강의실 고유번호에 해당하는 행 삭제   
  	public int removeClassroom(int classroomNo) {
		return classroomMapper.deleteClassroom(classroomNo);
	}
	
	
	// 강의실 상세정보 서비스
	// 매개변수: 강의실의 고유번호
	// 리턴값:  강의실 고유번호에 해당하는 상세정보
	public Classroom getClassroomDetail(int classroomNo) {
		return classroomMapper.classroomOne(classroomNo);
	}
}
