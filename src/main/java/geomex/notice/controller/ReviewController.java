package geomex.notice.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import geomex.notice.model.ReviewVo;
import geomex.notice.service.ReviewService;


@Controller
public class ReviewController {
	@Autowired
	private ReviewService reviewService;
	@Resource
	private Validator validator;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(this.validator);
	}

	@GetMapping("/reviewWrite.do")
	public String showReviewWritePage(Model model, @RequestParam int boardId) {
		model.addAttribute(boardId);
		return "reviewWrite";
	}

	@GetMapping("/reviewView.do")
	public String showReviewViewPage(Model model, @RequestParam int reviewId) {
		ReviewVo reviewVo = reviewService.selectReviewById(reviewId);
		model.addAttribute("review", reviewVo);
		return "reviewView";
	}
	@GetMapping("/reviewList.do")
	public String showReviewListPage(@RequestParam int boardId,Model model) {
		model.addAttribute("boardId",boardId);
		return "reviewList";
		
	}
	@PostMapping(value="reviewList/Select.do",produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public  Map<String, ArrayList<ReviewVo>> selectReviewList(@RequestParam("boardId") int boardId) {
		Map<String, ArrayList<ReviewVo>> response = new HashMap<>();
		ArrayList<ReviewVo> reviewList = reviewService.reviewListSelect(boardId);
		if (reviewList != null) {
			response.put("reviewList", reviewList);
			return response;
		} else {
			response.put("reviewList", null);
			return response;
		}
	}

	@PostMapping(value="review/Insert.do",produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public  Map<String, Object> insertReview(@Valid @RequestBody ReviewVo reviewVo,BindingResult result) {		
		Map<String, Object> response = new HashMap<>();
		ArrayList<String> errors=new ArrayList<String>();
		if (result.hasErrors()) {//유효성 검사를 통해서			
	        for (ObjectError error : result.getAllErrors()) {
	            	errors.add(error.getDefaultMessage());
	        }
	        response.put("error", errors);
			response.put("result", 0);
			return response;
		} else {
		
		int isSuccess = reviewService.reviewInsert(reviewVo);
		if (isSuccess == 1) {
			response.put("result", 1);
			response.put("message", "리뷰가 작성 되었습니다.");
			return response;
		} else {
			response.put("result", 0);
			response.put("message", "리뷰가 작성에 실패하였습니다.");
			return response;
		}
		}
	}

	@PostMapping(value="review/Update.do",consumes = MediaType.APPLICATION_JSON_VALUE ,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Map<String, Integer> updateReview(@RequestBody ReviewVo reviewVo) {
		Map<String, Integer> response = new HashMap<>();
		int isSuccess = reviewService.reviewUpdate(reviewVo);
		if (isSuccess == 1) {
			response.put("result", 1);
			return response;
		} else {
			response.put("result", 0);
			return response;
		}
	}

	@PostMapping("review/Delete.do")
	@ResponseBody
	public  Map<String, Integer> deleteReview(@RequestBody ReviewVo reviewVo) {
		Map<String, Integer> response = new HashMap<>();
		int isSuccess = reviewService.reviewDelete(reviewVo);
		if (isSuccess == 1) {
			response.put("result", 1);
			return response;
		} else {
			response.put("result", 0);
			return response;
		}
	}
}
