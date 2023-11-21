package kr.or.ddit.vo;

import lombok.Data;

@Data
public class NoticeVO {
	// 데이터베이스 컬럼명 카멜 케이스
	private int noticeNo;			// 공지사항게시판 번호
	private String noticeTitle;		// 공지사항게시판 제목
	private String noticeWriter;	// 공지사항게시판 작성자
	private String noticeContent;	// 공지사항게시판 내용
	private String noticeDate;		// 공지사항게시판 작성일
	private int noticeHit;			// 공지사항게시판 조회수
}
