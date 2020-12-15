package gd.fintech.lms.teacher.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.teacher.vo.LectureNotice;

//강좌별 공지사항 Mapper
@Mapper
public interface LectureNoticeMapper {
	List<LectureNotice> selectLectrueNoticeListByPage(Map<String,Integer>Map);//강좌별 공지사항 목록 및 페이징
	LectureNotice selectLectureNoticeOne(int lectureNoticeNo);//강좌별 공지사항 상세보기
	int insertLetureNotice(LectureNotice lectureNotice);//강좌별 공지사항 입력
	int upldateLetureNotice(LectureNotice lectureNotice);//강좌별 공지사항 수정
	int deleteLetureNotice(int lectureNoticeNo);//강좌별 공지사항 삭제
	int selectLectureNoticeCount();//강좌별 공지사항 카운트
}
