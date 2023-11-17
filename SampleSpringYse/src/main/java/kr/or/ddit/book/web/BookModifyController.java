package kr.or.ddit.book.web;

import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.book.service.IBookService;

@Controller
@RequestMapping("/book")
public class BookModifyController {

	@Inject
	private IBookService service;
	
	@RequestMapping(value="/update.do", method = RequestMethod.GET)
	public ModelAndView updateForm(@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();
		
		Map<String, Object> detailMap = service.selectBook(map);
		
		mav.addObject("book", detailMap);
		mav.setViewName("book/update");
		
		return mav;
	}
	
	@RequestMapping(value="/update.do", method = RequestMethod.POST)
	public ModelAndView update(@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();
		
		boolean result = service.updateBook(map);
		if(result) {
			// 업데이트가 정상적으로 데이터 갱신되었을 때 확인을 위해 상세페이지로 이동합니다.
			String bookId = map.get("bookId").toString();
			mav.setViewName("redirect:/book/detail.do?bookId="+bookId);
		} else {
			// 갱신이 되지 않았을 때 GET 메소드로 redirect하는 방법도 있지만, 상세보기 화면을 바로 보여줄 수도 있습니다.
			mav = updateForm(map);
		}
		
		return mav;
	}
	
	@RequestMapping(value="/delete.do", method = RequestMethod.POST)
	public ModelAndView delete(@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();
		
		// 삭제가 성공했는지 확인한다.
		boolean result = service.removeBook(map);
		if(result) {
			// 삭제가 성공했으면 상세 페이지가 없으므로 목록으로 redirect 한다.
			mav.setViewName("redirect:/book/list.do");
		} else {
			// 삭제가 실패했으면 다시 상세 페이지로 이동한다.
			String bookId = map.get("bookId").toString();
			mav.setViewName("redirect:/book/detail.do?bookId="+bookId);
		}
		
		return mav;
	}
}
