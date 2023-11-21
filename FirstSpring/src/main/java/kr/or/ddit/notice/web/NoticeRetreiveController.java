package kr.or.ddit.notice.web;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.notice.service.INoticeService;
import kr.or.ddit.vo.NoticeVO;
import kr.or.ddit.vo.PaginationInfoVO;

@Controller
@RequestMapping("/notice")
public class NoticeRetreiveController {
	
	@Inject
	private INoticeService noticeService;

	@RequestMapping(value="/list.do")
	public String noticeList(
			@RequestParam(name="page", required = false, defaultValue = "1") int currentPage,
			@RequestParam(required = false, defaultValue = "title") String searchType,
			@RequestParam(required = false) String searchWord,
			Model model) {
		
		// PaginationInfoVO 클래스를 활용한 목록 조회
		PaginationInfoVO<NoticeVO> pagingVO = new PaginationInfoVO<NoticeVO>();
		
		// 브라우저에서 검색한 유형, 검색 키워드를 이용하여 검색 처리
		// 검색 키워드가 있으면 검색을 한거고, 키워드가 없으면 검색을 하지 않음
		if(StringUtils.isNotBlank(searchWord)) {
			pagingVO.setSearchType(searchType);
			pagingVO.setSearchWord(searchWord);
			model.addAttribute("searchType", searchType);
			model.addAttribute("searchWord", searchWord);
		}
		
		pagingVO.setCurrentPage(currentPage);
		int totalRecord = noticeService.selectNoticeCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		List<NoticeVO> dataList = noticeService.selectNoticeList(pagingVO);
		pagingVO.setDataList(dataList);
		
		model.addAttribute("pagingVO", pagingVO);
		
		return "notice/list";
	}
	
	@RequestMapping(value="/detail.do", method = RequestMethod.GET)
	public String noticeDetail(int noticeNo, Model model) {
		// insert 이후 전달된 파라미터 noticeNo는 쿼리스트링으로 전달한 파라미터 이름이다. 동일해야 함!!!!!
		// 만약 select해서 나온 컬럼명이 notice_no라면 alias에서 설정한 mapUnderscoreToCamelCase에 의해 VO의 noticeNo와 연결시켜줌.
		// 따라서 VO의 변수 이름이 noNo(완전 다른 이름)이거나 notice_no로 설정하면 안됨.
		NoticeVO noticeVO = noticeService.selectNotice(noticeNo);
		
		// 뷰에서 noticeVO에 있는 변수 이름으로 접근한다. ex) notice.noticeNo
		model.addAttribute("notice", noticeVO);
		return "notice/view";
	}
}
