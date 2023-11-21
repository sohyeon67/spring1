package kr.or.ddit.board.web;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.vo.BoardVO;

@Controller
@RequestMapping("/board")
public class BoardModifyController {

	@Inject
	private IBoardService boardService;
	
	// 상세 페이지에서 수정 버튼을 눌렀을 때
	// 등록 폼, 수정 폼 둘다 /board/form.jsp 파일을 사용한다.
	@RequestMapping(value="/update.do", method = RequestMethod.GET)
	public String boardUpdateForm(int boNo, Model model) {
		BoardVO boardVO = boardService.selectBoard(boNo);
		model.addAttribute("board", boardVO);	// 수정 폼에 게시글 정보 전달
		model.addAttribute("status", "u");	// '현재 기능은 수정입니다'를 알리기 위한 flag
		return "board/form";	// form.jsp에서 등록과 수정을 구분한다.
	}
	
	// 수정 기능을 처리한다.
	@RequestMapping(value = "/update.do", method = RequestMethod.POST)
	public String boardUpdate(BoardVO boardVO, Model model) {
		String goPage = "";
		
		ServiceResult result = boardService.updateBoard(boardVO);
		if(result.equals(ServiceResult.OK)) {	// 수정 성공
			goPage = "redirect:/board/detail.do?boNo="+boardVO.getBoNo();
		} else {	// 수정 실패
			model.addAttribute("board", boardVO);
			model.addAttribute("status", "u");
			goPage = "board/form";	// 수정 실패하면 수정 폼을 보인다.
		}
		
		return goPage;
	}
	
	// 상세 페이지에서 삭제 버튼을 눌렀을 때
	@RequestMapping(value="/delete.do", method = RequestMethod.POST)
	public String deleteBoard(int boNo, Model model) {
		String goPage = "";
		
		ServiceResult result = boardService.deleteBoard(boNo);
		if(result.equals(ServiceResult.OK)) {	// 삭제 성공
			goPage = "redirect:/board/list.do";
		} else {	// 삭제 실패
			goPage = "redirect:/board/detail.do?boNo="+boNo;
		}
		
		return goPage;
	}
}
