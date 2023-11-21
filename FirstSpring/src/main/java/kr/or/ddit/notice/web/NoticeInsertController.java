package kr.or.ddit.notice.web;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.notice.service.INoticeService;
import kr.or.ddit.vo.NoticeVO;

@Controller
@RequestMapping("/notice")
public class NoticeInsertController {
	
	@Inject
	private INoticeService noticeService;
	
	// 등록 폼 페이지 띄우기
	@RequestMapping(value="/form.do", method = RequestMethod.GET)
	public String noticeForm() {
		return "notice/form";
	}
	
	// 등록 기능 수행
	@RequestMapping(value="/insert.do", method = RequestMethod.POST)
	public String noticeInsert(NoticeVO noticeVO, Model model) {
		// NoticeVO 변수 이름과 form에서 넘겨주는 name값이 같아야 한다!
		
		String goPage = "";	// 페이지 정보를 담을 변수
		
		// 서버에서도 데이터에 대한 입력값 검증에 대한 필터를 걸어준다.
		Map<String, Object> errors = new HashMap<String, Object>();
		if(StringUtils.isBlank(noticeVO.getNoticeTitle())) {
			errors.put("noticeTitle", "제목을 입력해주세요!");
		}
		if(StringUtils.isBlank(noticeVO.getNoticeContent())) {
			errors.put("noticeContent", "내용을 입력해주세요!");
		}
		
		if(errors.size() > 0) {	// 에러가 있을 때
			model.addAttribute("notice", noticeVO);	// 제목, 내용 다시 돌려줌
			model.addAttribute("errors", errors);	// 에러 정보 전달
			goPage = "/notice/form";	// 포워드 방식, 주소창에는 /notice/insert.do
		} else {	// 정상적인 데이터 입력시
			noticeVO.setNoticeWriter("a001");
			ServiceResult result = noticeService.insertNotice(noticeVO);
			if(result.equals(ServiceResult.OK)) {
				goPage = "redirect:/notice/detail.do?noticeNo="+noticeVO.getNoticeNo();	// 주소창 변경을 위해 redirect
			} else {
				model.addAttribute("notice", noticeVO);
				model.addAttribute("message", "서버 에러, 다시 시도해주세요!");
				goPage = "/notice/form";	// 포워드 방식, 주소창은 /notice/insert.do
			}
		}
		
		return goPage;
	}
}
