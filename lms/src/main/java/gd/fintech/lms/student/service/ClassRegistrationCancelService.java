package gd.fintech.lms.student.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gd.fintech.lms.student.mapper.ClassRegistrationCancelMapper;

@Service
@Transactional
public class ClassRegistrationCancelService {
	@Autowired ClassRegistrationCancelMapper classRegistrationCancelMappper;
	
	//학생이 수강신청을 취소한 이유(사유) 입력
	//매개변수:취소 내용
	//리턴값:학생들이 취소한 이유
	public int addCancel(String cancelContent,int classRegistrationNo) {
		Map<String,Object>map = new HashMap<>();
		map.put("cancelContent",cancelContent);
		map.put("classRegistrationNo",classRegistrationNo);
		return classRegistrationCancelMappper.insertCancel(map);
	}
	
	//학생의 수강 취소
	//매개변수:학생 수강신청번호
	//리턴값:학생들의 수강신청 취소
	public int classRegistrationModify(int classRegistrationNo) {
		return  classRegistrationCancelMappper.updateClassRegistration(classRegistrationNo);
	}
	
}
