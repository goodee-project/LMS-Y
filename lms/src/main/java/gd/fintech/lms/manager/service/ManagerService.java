package gd.fintech.lms.manager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gd.fintech.lms.manager.mapper.TextbookMapper;
import gd.fintech.lms.manager.vo.Textbook;

// 운영자가 하는 업무를 위한 서비스

@Service
public class ManagerService {
	@Autowired TextbookMapper textbookMapper;
	
	// 운영자가 교재 정보를 등록하는 서비스 메소드
	// 매개변수: textbook
	// 리턴값: 
	public int addTextbook(Textbook textbook) {
		return textbookMapper.insertTextbook(textbook);
	}
}
