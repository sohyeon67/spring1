package kr.or.ddit.free.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.free.dao.IFreeDAO;
import kr.or.ddit.vo.FreeVO;

@Service
public class FreeServiceImpl implements IFreeService {

	@Inject
	private IFreeDAO freeDao;
	
	@Override
	public ServiceResult insertFree(FreeVO freeVO) {
		ServiceResult result = null;
		int status = freeDao.insertFree(freeVO);
		if(status > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	// 상세보기 데이터 가져오기
	@Override
	public FreeVO selectFree(int freeNo) {
		// 조회수 증가
		freeDao.incrementHit(freeNo);
		return freeDao.selectFree(freeNo);
	}

	@Override
	public ServiceResult updateFree(FreeVO freeVO) {
		ServiceResult result = null;
		int status = freeDao.updateFree(freeVO);
		if(status > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public ServiceResult deleteFree(int freeNo) {
		ServiceResult result = null;
		int status = freeDao.deleteFree(freeNo);
		if(status > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

}
