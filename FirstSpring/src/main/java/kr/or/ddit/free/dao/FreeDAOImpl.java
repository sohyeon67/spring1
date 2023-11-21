package kr.or.ddit.free.dao;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import kr.or.ddit.vo.FreeVO;

@Repository
public class FreeDAOImpl implements IFreeDAO {
	
	@Inject
	private SqlSessionTemplate sqlSession;

	@Override
	public int insertFree(FreeVO freeVO) {
		return sqlSession.insert("Free.insertFree", freeVO);
	}

	@Override
	public FreeVO selectFree(int freeNo) {
		return sqlSession.selectOne("Free.selectFree", freeNo);
	}

	@Override
	public void incrementHit(int freeNo) {
		sqlSession.update("Free.incrementHit", freeNo);
	}

	@Override
	public int updateFree(FreeVO freeVO) {
		return sqlSession.update("Free.updateFree", freeVO);
	}

	@Override
	public int deleteFree(int freeNo) {
		return sqlSession.delete("Free.deleteFree", freeNo);
	}

}
