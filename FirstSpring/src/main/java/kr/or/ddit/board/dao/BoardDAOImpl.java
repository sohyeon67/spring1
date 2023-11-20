package kr.or.ddit.board.dao;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.BoardVO;

@Repository
public class BoardDAOImpl implements IBoardDAO {

	@Inject
	private SqlSessionTemplate sqlSession;
	
	@Override
	public int insertBoard(BoardVO boardVO) {
		return sqlSession.insert("Board.insertBoard", boardVO);
	}

	@Override
	public void incrementHit(int boNo) {
		sqlSession.update("Board.incrementHit", boNo);
	}

	@Override
	public BoardVO selectBoard(int boNo) {
		return sqlSession.selectOne("Board.selectBoard", boNo);
	}

}
