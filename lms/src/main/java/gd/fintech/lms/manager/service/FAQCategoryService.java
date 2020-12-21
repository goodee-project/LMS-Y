package gd.fintech.lms.manager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gd.fintech.lms.manager.mapper.FAQCategoryMapper;
import gd.fintech.lms.manager.vo.FAQCategory;

//FAQCategory 를 관리하는 서비스 

@Service
@Transactional
public class FAQCategoryService {
	
		// FAQ Category에 대한 매퍼
		@Autowired private FAQCategoryMapper faqCategoryMapper;
		
		
		// FAQ의 카테고리의 목록을 출력하는 서비스
		// 리턴값: FAQ의 카테고리의 리스트 
		public List<FAQCategory>getFAQCategoryLisqt(){
			 return faqCategoryMapper.selectFAQCategoryList();
		}
		
		// FAQ의 카테고리를 입력하는 서비스 
		// 매개변수: FAQCateogry 에 대한 정보
		// 리턴값: 입력받은 정보가 추가된 행의 개수
		public int createFAQCategory(FAQCategory faqCategory) {
			return faqCategoryMapper.insertFAQCategory(faqCategory);
		}
		
		// FAQ의 카테고리를 수정하는 서비스 
		// 매개변수: FAQCategory에 대한 정보 
		// 리턴값: 선택한 행을 입력받은 정보로 수정
		public int modifyFAQCategory(FAQCategory faqCategory){
			return faqCategoryMapper.updateFAQCategory(faqCategory);
		}
}
