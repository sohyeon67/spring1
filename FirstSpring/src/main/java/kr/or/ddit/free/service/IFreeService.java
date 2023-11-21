package kr.or.ddit.free.service;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.vo.FreeVO;

public interface IFreeService {

	ServiceResult insertFree(FreeVO freeVO);
	FreeVO selectFree(int freeNo);
	ServiceResult updateFree(FreeVO freeVO);
	ServiceResult deleteFree(int freeNo);

}
