package gd.fintech.lms.manager.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import gd.fintech.lms.manager.vo.Classroom;



@Mapper
public interface ClassroomMapper {
	List<Classroom> selectClassroomList(); //강의실 목록
	int insertClassromm(Classroom classroom);   //강의실 입력
	int updateClassroom(Classroom classroom); //강의실 수정
	int deleteClassroom(int classroomNo);	//강의실 삭제

}
