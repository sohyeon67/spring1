package kr.or.ddit.free.dao;

import kr.or.ddit.vo.FreeVO;

public interface IFreeDAO {

	int insertFree(FreeVO freeVO);
	FreeVO selectFree(int freeNo);
	void incrementHit(int freeNo);
	int updateFree(FreeVO freeVO);
	int deleteFree(int freeNo);

}
