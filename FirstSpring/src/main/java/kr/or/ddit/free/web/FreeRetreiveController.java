package kr.or.ddit.free.web;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.free.service.IFreeService;
import kr.or.ddit.vo.FreeVO;
import kr.or.ddit.vo.PaginationInfoVO;

@Controller
@RequestMapping("/free")
public class FreeRetreiveController {
	
	@Inject
	private IFreeService freeService;

	@RequestMapping(value="/list.do")
	public String list(
			@RequestParam(name="page", required = false, defaultValue = "1") int currentPage,
			@RequestParam(required = false, defaultValue = "title") String searchType,
			@RequestParam(required = false) String searchWord,
			Model model) {
		
		// PaginationInfoVO 클래스를 활용한 목록 조회
		PaginationInfoVO<FreeVO> pagingVO = new PaginationInfoVO<FreeVO>();
		
		// 브라우저에서 검색한 유형, 검색 키워드를 이용하여 검색 처리
		// 검색 키워드가 있으면 검색을 한거고, 키워드가 없으면 검색을 하지 않음
		if(StringUtils.isNotBlank(searchWord)) {
			pagingVO.setSearchType(searchType);
			pagingVO.setSearchWord(searchWord);
			model.addAttribute("searchType", searchType);
			model.addAttribute("searchWord", searchWord);
		}
		
		pagingVO.setCurrentPage(currentPage);
		int totalRecord = freeService.selectFreeCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		List<FreeVO> dataList = freeService.selectFreeList(pagingVO);
		pagingVO.setDataList(dataList);
		
		model.addAttribute("pagingVO", pagingVO);
		
		return "free/list";
	}
	
	@RequestMapping(value="/detail.do", method = RequestMethod.GET)
	public String freeDetail(int freeNo, Model model) {
		FreeVO freeVO = freeService.selectFree(freeNo);
		model.addAttribute("free", freeVO);
		return "free/view";
	}
}
