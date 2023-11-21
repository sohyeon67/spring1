package kr.or.ddit.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.board.dao.IBoardDAO;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PaginationInfoVO;

@Service
public class BoardServiceImpl implements IBoardService {

	@Inject
	private IBoardDAO boardDao;
	
	@Override
	public ServiceResult insertBoard(BoardVO boardVO) {
		ServiceResult result = null;
		int status = boardDao.insertBoard(boardVO);
		if(status > 0) {	// 성공
			result = ServiceResult.OK;
		} else {			// 실패
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public BoardVO selectBoard(int boNo) {
		// 조회수 증가
		boardDao.incrementHit(boNo);
		return boardDao.selectBoard(boNo);
	}

	@Override
	public ServiceResult updateBoard(BoardVO boardVO) {
		ServiceResult result = null;
		int status = boardDao.updateBoard(boardVO);
		if(status > 0) {	// 수정 성공
			result = ServiceResult.OK;
		} else {	// 수정 실패
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public ServiceResult deleteBoard(int boNo) {
		ServiceResult result = null;
		int status = boardDao.deleteBoard(boNo);
		if(status > 0) {	// 삭제 성공
			result = ServiceResult.OK;
		} else {	// 삭제 실패
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public List<BoardVO> selectBoardList_() {
		return boardDao.selectBoardList_();
	}

	@Override
	public int selectBoardCount(PaginationInfoVO<BoardVO> pagingVO) {
		return boardDao.selectBoardCount(pagingVO);
	}

	@Override
	public List<BoardVO> selectBoardList(PaginationInfoVO<BoardVO> pagingVO) {
		return boardDao.selectBoardList(pagingVO);
	}

}
