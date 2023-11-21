package kr.or.ddit.free.web;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.free.service.IFreeService;
import kr.or.ddit.vo.FreeVO;

@Controller
@RequestMapping("/free")
public class FreeRetreiveController {
	
	@Inject
	private IFreeService freeService;

	@RequestMapping(value="/list.do", method = RequestMethod.GET)
	public String list() {
		return "free/list";
	}
	
	@RequestMapping(value="/detail.do", method = RequestMethod.GET)
	public String freeDetail(int freeNo, Model model) {
		FreeVO freeVO = freeService.selectFree(freeNo);
		model.addAttribute("free", freeVO);
		return "free/view";
	}
}
