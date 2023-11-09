package geomex.notice.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.jms.Session;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import geomex.notice.model.BoardVo;
import geomex.notice.model.BookMarkVo;
import geomex.notice.model.FileVo;
import geomex.notice.page.PageManager;
import geomex.notice.service.BoardService;
import geomex.notice.service.FileService;
import geomex.notice.session.SessionConst;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	@Autowired
	private FileService fileService;
	@Resource(name = "validator")
	private Validator validator;
	@Autowired
	private PageManager pageManager;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(this.validator);
	}

	@GetMapping("/board.do")
	public String showBoardPage() {
		return "board";
	}

	@GetMapping(value = "/boardList/Select.do", produces = MediaType.APPLICATION_JSON_VALUE) // -> param 페이지 넘버가 넘어오기
																								// 때문에 방대한 데이터가 아님
	@ResponseBody
	public Map<String, Object> selectBoardList(@RequestParam int pageNo) {
		Map<String, Object> response = new HashMap<>();
		pageManager.setPageNo(pageNo);// 셋팅
		pageManager.setCountPerpage(8);
		pageManager.setBoardCount(boardService.getBoardCount());
		int limit = pageManager.getLimit();
		int offset = pageManager.getOffset();
		ArrayList<BoardVo> boardList = boardService.getBoardlist(offset, limit);
		response.put("boardList", boardList);
		response.put("pageManager", pageManager);
		return response;
	}

	@GetMapping("/boardWrite.do")
	public String showBoardWritePage(Model model) {

		if (!model.containsAttribute("msg")) {
			model.addAttribute("msg", "글쓰기 페이지 입니다.");
		}
		return "/boardWrite";
	}

	@PostMapping(value = "/insertBoardAction.do", consumes = "multipart/form-data") // ->multipart/form-data 이
																					// Contents-type으로만 받겠다는 의미
	public String insertBoardAction(@Valid @ModelAttribute BoardVo boardVo, BindingResult result,
			@RequestParam("boardFiles") List<MultipartFile> files, RedirectAttributes redirectAttributes)
			throws Exception {
		System.out.println("여기까지는 진행됨 큰 파일 업로드 하는 순간 롤백되어야함");
		if (result.hasErrors()) {

			redirectAttributes.addFlashAttribute("msg", result.getFieldError().getDefaultMessage());
			return "redirect:/boardWrite.do";
		} else {
			boolean success = boardService.insertBoardAndFiles(boardVo, files);
			if (success) {
				redirectAttributes.addFlashAttribute("msg", "글을 작성하였습니다.");
				return "redirect:/board.do";
			} else {
				System.out.println("실패" + success);
				redirectAttributes.addFlashAttribute("msg", "글쓰기에 실패하였습니다.");
				return "redirect:/boardWrite.do";
			}
		}

	}

	@GetMapping("/boardView.do")
	public String getBoardById(Model model, @RequestParam(defaultValue = "no") String viewSet,
			@RequestParam int boardId) {
		if (viewSet.equals("yes")) {
			boardService.viewsCount(boardId);
		}
		BoardVo board = boardService.getBoardById(boardId);
		ArrayList<FileVo> files = fileService.getFileById(boardId);
		model.addAttribute("board", board);
		model.addAttribute("files", files);
		return "boardView";
	}

	@PostMapping(value = "/board/Update.do", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Integer> updateBoard(@SessionAttribute(name = SessionConst.USER_ID) String userId,
			@RequestBody BoardVo boardVo) {
		Map<String, Integer> response = new HashMap<>();
		int isSuccess = 0;
		String writerId = boardService.getWriterByBoardId(boardVo.getBoardId());
		if (writerId != null && userId.equals(writerId)) {
			isSuccess = boardService.boardUpdate(boardVo);
			response.put("result", isSuccess);
			return response;
		}
		response.put("result", isSuccess);
		return response;

	}

	@PostMapping("/board/Delete.do")
	@ResponseBody
	public Map<String, Integer> deleteBoard(@SessionAttribute(name = SessionConst.USER_ID) String userId,
			@RequestParam int boardId) {
		Map<String, Integer> response = new HashMap<>();
		int isSuccess = 0;
		String writerId = boardService.getWriterByBoardId(boardId);
		if (writerId != null && userId.equals(writerId)) {
			isSuccess = boardService.boardDelete(boardId);
			response.put("result", isSuccess);
			return response;
		}
		response.put("result", isSuccess);
		return response;
	}

	@GetMapping("/boardList/SearchSelect.do")
	@ResponseBody
	public Map<String, Object> searchBoard(@RequestParam String searchType, @RequestParam int pageNo,
			@RequestParam String searchText) {

		Map<String, Object> response = new HashMap<>();
		pageManager.setPageNo(pageNo);
		pageManager.setCountPerpage(8);
		pageManager.setBoardCount(boardService.getSearchBoardCount(searchText, searchType));
		int limit = pageManager.getLimit();
		int offset = pageManager.getOffset();
		ArrayList<BoardVo> boardSearchList = boardService.getSearchBoardlist(offset, limit, searchText, searchType);
		response.put("boardList", boardSearchList);
		response.put("pageManager", pageManager);

		return response;
	}


}
