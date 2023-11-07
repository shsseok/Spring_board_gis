package geomex.notice.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import geomex.notice.mapper.ReviewMapper;
import geomex.notice.model.BoardVo;
import geomex.notice.model.ReviewVo;

@Service
public class ReviewService {
	@Autowired
	private ReviewMapper reviewMapper;
	@Autowired 
	private UserService userService;
	public int reviewInsert(ReviewVo reviewvo) {
		return reviewMapper.insertReview(reviewvo);
	}

	public int reviewUpdate(ReviewVo reviewVo) {
		int isSuceess = 0;
		if(userCheck(reviewVo))			
		{
			isSuceess=reviewMapper.updateReview(reviewVo);
			return isSuceess;
		}
		else
		{
			return isSuceess;
		}
		
	}

	public int reviewDelete(ReviewVo reviewVo) {
		int isSuceess = 0;
		int reviewId=reviewVo.getReviewId();
		if(userCheck(reviewVo))			
		{
			isSuceess=reviewMapper.deleteReview(reviewId);
			return isSuceess;
		}
		else
		{
			return isSuceess;
		}		 
	}
	public ArrayList<ReviewVo> reviewListSelect(int boardId)
	{
		return reviewMapper.selectReviewList(boardId);
	}
	
	public ReviewVo selectReviewById(int reviewId)
	{
		return reviewMapper.selectReviewById(reviewId);
	}
	public boolean userCheck(ReviewVo reviewVo)
	{
		String userId=userService.getUserId(reviewVo.getWriterId());
		if(userId!=null && userId.equals(reviewVo.getWriterId()))
		{
			return true;
		} 
		else
		{
			return false;
		}		
	}
}
