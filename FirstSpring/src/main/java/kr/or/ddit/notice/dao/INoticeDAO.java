package kr.or.ddit.notice.dao;

import java.util.List;

import kr.or.ddit.vo.NoticeVO;
import kr.or.ddit.vo.PaginationInfoVO;

public interface INoticeDAO {

	int insertNotice(NoticeVO noticeVO);
	NoticeVO selectNotice(int noNo);
	int deleteNotice(int noticeNo);
	int updateNotice(NoticeVO noticeVO);
	void incrementHit(int noticeNo);
	int selectNoticeCount(PaginationInfoVO<NoticeVO> pagingVO);
	List<NoticeVO> selectNoticeList(PaginationInfoVO<NoticeVO> pagingVO);

}
