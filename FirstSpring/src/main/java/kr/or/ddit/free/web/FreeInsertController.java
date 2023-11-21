package kr.or.ddit.free.web;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.free.service.IFreeService;
import kr.or.ddit.vo.FreeVO;

@Controller
@RequestMapping("/free")
public class FreeInsertController {
	
	@Inject
	private IFreeService freeService;
	
	// 등록 폼 페이지 띄우기
	@RequestMapping(value="/form.do", method = RequestMethod.GET)
	public String freeForm() {
		return "free/form";
	}
	
	// 등록 기능 수행
	@RequestMapping(value="/insert.do", method = RequestMethod.POST)
	public String freeInsert(FreeVO freeVO, Model model) {
		// FreeVO 변수명과 form의 name 값이 같아야 함
		
		String goPage = "";	// 페이지 정보를 담을 변수
		
		// 서버에서도 데이터에 대한 입력값 검증 필터를 걸어줌
		Map<String, Object> errors = new HashMap<String, Object>();
		if(StringUtils.isBlank(freeVO.getFreeTitle())) {
			errors.put("freeTitle", "제목을 입력해주세요!");
		}
		if(StringUtils.isBlank(freeVO.getFreeContent())) {
			errors.put("freeContent", "내용을 입력해주세요!");
		}
		
		if(errors.size() > 0) {	// 에러가 있을 때
			model.addAttribute("free", freeVO);		// 제목, 내용 다시 돌려줌
			model.addAttribute("errors", errors);	// 에러 정보 전달
			goPage = "/free/form";	// 포워드, 주소 창에는 /free/insert.do
		} else {	// 정상적인 데이터 입력시
			freeVO.setFreeWriter("a001");
			ServiceResult result = freeService.insertFree(freeVO);
			if(result.equals(ServiceResult.OK)) {
				goPage = "redirect:/free/detail.do?freeNo="+freeVO.getFreeNo();	// 주소창 변경
			} else {
				model.addAttribute("free", freeVO);
				model.addAttribute("message", "서버 에러, 다시 시도해주세요!");
				goPage = "/free/form";	// 포워드, 주소 창에는 /free/insert.do
			}
		}
		
		return goPage;
	}
}
