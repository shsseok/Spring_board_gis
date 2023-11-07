package geomex.notice.mapper;

import java.util.ArrayList;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import geomex.notice.model.BoardVo;
import geomex.notice.model.ReviewVo;

@Mapper
public interface ReviewMapper {
	int insertReview(ReviewVo reviewvo);
	int deleteReview(int reviewId);
	int updateReview(ReviewVo reviewvo);
	ArrayList<ReviewVo> selectReviewList(int boardId);
	ReviewVo selectReviewById(int reviewId);
	
}
