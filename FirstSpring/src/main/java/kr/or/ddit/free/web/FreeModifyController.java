package kr.or.ddit.free.web;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.free.service.IFreeService;
import kr.or.ddit.vo.FreeVO;

@Controller
@RequestMapping("/free")
public class FreeModifyController {
	
	@Inject
	private IFreeService freeService;

	// 상세보기 -> 수정 눌렀을 때
	@RequestMapping(value="/update.do", method = RequestMethod.GET)
	public String freeUpdateForm(int freeNo, Model model) {
		FreeVO freeVO = freeService.selectFree(freeNo);
		model.addAttribute("free", freeVO);	// 내용들 가져오기
		model.addAttribute("status", "u");	// 현재 기능은 수정
		return "free/form";
	}
	
	// 수정 기능 수행부분
	@RequestMapping(value="/update.do", method = RequestMethod.POST)
	public String freeUpdate(FreeVO freeVO, Model model) {
		String goPage = "";
		
		ServiceResult result = freeService.updateFree(freeVO);
		if(result.equals(ServiceResult.OK)) {
			goPage = "redirect:/free/detail.do?freeNo="+freeVO.getFreeNo();
		} else {
			model.addAttribute("free", freeVO);
			model.addAttribute("status", "u");
			goPage = "free/form";
		}
		
		return goPage;
	}
	
	// 상세보기 -> 삭제 눌렀을 때
	@RequestMapping(value="/delete.do", method = RequestMethod.POST)
	public String deleteFree(int freeNo, Model model) {
		String goPage = "";
		
		ServiceResult result = freeService.deleteFree(freeNo);
		if(result.equals(ServiceResult.OK)) {
			goPage = "redirect:/free/list.do";
		} else {
			goPage = "redirect:/free/detail.do?freeNo="+freeNo;
		}
		
		return goPage;
	}
}
