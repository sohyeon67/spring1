package kr.or.ddit.board.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.board.dao.IBoardDAO;
import kr.or.ddit.vo.BoardVO;

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

}
