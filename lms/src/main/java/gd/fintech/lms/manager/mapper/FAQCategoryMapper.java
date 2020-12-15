package gd.fintech.lms.manager.mapper;

import java.util.List;


import org.apache.ibatis.annotations.Mapper;
import gd.fintech.lms.manager.vo.FAQCategory;



@Mapper
public interface FAQCategoryMapper {
	
	List<FAQCategory> selectFAQCategoryList(); //FAQ 카테고리 
	

}
