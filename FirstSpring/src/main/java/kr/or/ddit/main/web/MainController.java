package kr.or.ddit.main.web;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.main.service.IMainService;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.FreeVO;
import kr.or.ddit.vo.NoticeVO;

@Controller
public class MainController {
	
	@Inject
	private IMainService mainService;

	@RequestMapping(value={"/","/main.do"}, method = RequestMethod.GET)
	public String main(Model model) {
		// 일반게시판 최신글 5개 목록 가져오기
		List<BoardVO> boardList = mainService.selectBoardList();
		
		// 공지사항게시판 최신글 5개 목록 가져오기
		List<NoticeVO> noticeList = mainService.selectNoticeList();
		
		// 자유게시판 최신글 5개 목록 가져오기
		List<FreeVO> freeList = mainService.selectFreeList();
		
		
		// 일반게시판, 공지사항게시판, 자유게시판 총 게시글 수
		Map<String, String> allCount = mainService.selectAllCount();
		
		model.addAttribute("boardList", boardList);
		model.addAttribute("noticeList", noticeList);
		model.addAttribute("freeList", freeList);
		model.addAttribute("allCount", allCount);
		
		return "main";
	}
}
