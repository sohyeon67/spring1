package kr.or.ddit.main.web;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PaginationInfoVO;

@Controller
public class MainController {
	
	@Inject
	private IBoardService boardService;

	@RequestMapping(value={"/","/main.do"}, method = RequestMethod.GET)
	public String main(Model model) {
		PaginationInfoVO<BoardVO> pagingVO = new PaginationInfoVO<BoardVO>();
		int boardCount = boardService.selectBoardCount(pagingVO);
		model.addAttribute("boardCount", boardCount);
		pagingVO.setTotalRecord(boardCount);
		List<BoardVO> boardList = boardService.selectBoardList(pagingVO);
		model.addAttribute("boardList", boardList);
		
		return "main";
	}
}
