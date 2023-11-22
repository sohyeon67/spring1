package kr.or.ddit.free.dao;

import java.util.List;

import kr.or.ddit.vo.FreeVO;
import kr.or.ddit.vo.PaginationInfoVO;

public interface IFreeDAO {

	int insertFree(FreeVO freeVO);
	FreeVO selectFree(int freeNo);
	void incrementHit(int freeNo);
	int updateFree(FreeVO freeVO);
	int deleteFree(int freeNo);
	int selectFreeCount(PaginationInfoVO<FreeVO> pagingVO);
	List<FreeVO> selectFreeList(PaginationInfoVO<FreeVO> pagingVO);

}
