package gd.fintech.lms.teacher.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gd.fintech.lms.account.mapper.AddressMapper;
import gd.fintech.lms.account.vo.Address;
import gd.fintech.lms.teacher.mapper.TeacherMapper;
import gd.fintech.lms.teacher.vo.Teacher;

//강사 자신의 정보 수정 및 상세보기 할 수 있는 서비스

@Service
@Transactional
public class TeacherService {
	//Logger
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//TeacherMapper 객체 주입
	@Autowired private TeacherMapper teacherMapper;
	//AddressMapper 객체 주입
	@Autowired private AddressMapper addressMapper;
	
	//강사 아이디를 조회하여 정보결과를 가져오는 메서드
	//매개변수:로그인뷰에 넣은 계정 ID
	//리턴값:로그인한 계정 ID에 값이 있는 ID에 관련된 정보를 반환
	public Teacher getTeacherOne(String accountId){
		Teacher teacher = teacherMapper.selectTeacherOne(accountId);
		return teacher;

	}
	
	//강사 아이디를 조회아여 정보를 수정하게 해주는 메서드
	//매개변수:로그인뷰에 넣은 계정 ID
	//리턴값:수정된 값
	public int getTeacherUpdate(Teacher teacher) {
		return teacherMapper.updateTeacherInfo(teacher);
	}
	
	//주소 목록 및 페이징을 보여주는 메서드
	//매개변수: 현재 페이지
	//리턴값: 현재 페이지 주소 목록
	public Map<String,Object> getAddressListByPage(int currentPage){
		//현재 페이지 표시할 데이터 수
		int rowPerPage = 10;
		//시작 페이지
		int beginRow = (currentPage-1)*rowPerPage;
		//전체 페이지 개수
		int addressCount = addressMapper.selectAddressCount();
		//마지막페이지
		int lastPage = addressCount/rowPerPage;
		//10 미만의 개수의 데이터가 있는 페이지 표시
		if(addressCount%rowPerPage !=0) {
			lastPage +=1;
		}
		//전체 페이지가 0개이면 현재 페이지도 0으로 표시
		if(lastPage == 0) {
			currentPage = 0;
		}
			
		Map<String,Object> paramMap = new HashMap<>();
		
		paramMap.put("rowPerPage", rowPerPage);
		paramMap.put("beginRow", beginRow);
		
		List<Address> addressList = addressMapper.selectAddressByPage(paramMap);
		logger.debug(addressList+"<--- addressList");
		
		Map<String,Object>map = new HashMap<String,Object>();
		map.put("lastPage", lastPage);
		map.put("addressList", addressList);	
		return map;
	}

	//주소를 검색하게 해주는 메서드
	//매개변수:우편번호 및 현재 페이지
	//리턴값:주소 조회
	/*public Map<String, Object> getAddressByZipCode(Map<String, Object>map){
		//현재 페이지에 표시할 데이터 수
		int rowPerPage = 10;
		//시작 페이지
		int beginRow = (currentPage-1)*rowPerPage;
		//전쳎
		int addressCount = addressMapper.selectAddressCount();
		//마지막페이지
		int lastPage = addressCount/rowPerPage;
		//10 미만의 개수의 데이터가 있는 페이지 표시
		if(addressCount%rowPerPage !=0) {
			lastPage +=1;
		}
		//전체 페이지가 0개이면 현재 페이지도 0으로 표시
		if(lastPage == 0) {
			currentPage = 0;
		}*/
}
