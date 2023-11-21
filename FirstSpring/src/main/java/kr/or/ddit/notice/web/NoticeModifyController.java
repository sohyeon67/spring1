package kr.or.ddit.notice.web;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.notice.service.INoticeService;
import kr.or.ddit.vo.NoticeVO;

@Controller
@RequestMapping("/notice")
public class NoticeModifyController {

	@Inject
	private INoticeService noticeService;
	
	// 상세 페이지에서 수정 버튼을 눌렀을 때
	// 등록 폼, 수정 폼 둘다 /notice/form.jsp 파일을 사용한다.
	// 게시글 정보를 불러와 뿌리는 부분
	@RequestMapping(value="/update.do", method = RequestMethod.GET)
	public String noticeUpdateForm(int noticeNo, Model model) {
		NoticeVO noticeVO = noticeService.selectNotice(noticeNo);
		model.addAttribute("notice", noticeVO);	// 수정 폼에 게시글 정보 전달
		model.addAttribute("status", "u");	// '현재 기능은 수정입니다'를 알리기 위한 flag
		return "notice/form";	// form.jsp에서 등록과 수정을 구분한다.
	}
	
	// 수정 기능을 수행하는 부분
	@RequestMapping(value="/update.do", method = RequestMethod.POST)
	public String noticeUpdate(NoticeVO noticeVO, Model model) {
		String goPage = "";
		
		ServiceResult result = noticeService.updateNotice(noticeVO);
		if(result.equals(ServiceResult.OK)) {	// 수정 성공
			goPage = "redirect:/notice/detail.do?noticeNo="+noticeVO.getNoticeNo();
		} else {	// 수정 실패
			model.addAttribute("notice", noticeVO);
			model.addAttribute("status", "u");
			goPage = "notice/form";	// 수정 실패하면 수정 폼을 보인다.
		}
		
		return goPage;
	}
	
	// 상세 페이지에서 삭제 버튼을 눌렀을 때
	@RequestMapping(value="/delete.do", method = RequestMethod.POST)
	public String deleteNotice(int noticeNo, Model model) {
		String goPage = "";
		
		ServiceResult result = noticeService.deleteNotice(noticeNo);
		if(result.equals(ServiceResult.OK)) {	// 삭제 성공
			goPage = "redirect:/notice/list.do";
		} else {	// 삭제 실패
			goPage = "redirect:/notice/detail.do?noticeNo="+noticeNo;
		}
		
		return goPage;
	}
}
