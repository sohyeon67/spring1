package kr.or.ddit.board.dao;

import kr.or.ddit.vo.BoardVO;

public interface IBoardDAO {

	public int insertBoard(BoardVO boardVO);
	public void incrementHit(int boNo);
	public BoardVO selectBoard(int boNo);

}
