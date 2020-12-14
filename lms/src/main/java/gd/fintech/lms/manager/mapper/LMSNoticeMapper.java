package gd.fintech.lms.manager.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.manager.vo.LMSNotice;

// 	LMS 공지사항 mapper
@Mapper
public interface LMSNoticeMapper {
	List<LMSNotice> selectLMSNoticeListByPage(int beginRow, int rowPerPage); // 공지사항 리스트 페이지
	LMSNotice selectLMSNoticeDetail(int lmsNoticeNo); // 공지사항 상세보기
	
	int insertLMSNotice(LMSNotice lmsNotice); // 공지사항 입력
	int updateLMSNotice(LMSNotice lmsNotice); // 공지사항 수정
	int deleteLMSNotice(int lmsNoticeNo); // 공지사항 삭제
}
