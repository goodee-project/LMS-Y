package gd.fintech.lms.teacher.vo;

import lombok.Data;

//강좌 공지사항 VO
@Data
public class LectureNotice {
	private int lectureNoticeNo;//강좌 공지사항 고유번호
	private int lectureNo;//강좌 고유번호
	private String lectureNoticeTitle;//강좌 공지사항 제목
	private String lectureNoticeContent;//강좌 공지사항 내용
	private String lectureNoticeCreateDate;//강좌 공지사항 정보 생성 일시
	private String lectureNoticeUpdateDate;//강좌 공지사항 정보 업데이트 일시
	private int lectureNoticeCount;//강좌 공지사항 조회수
}
