package geomex.notice.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import geomex.notice.mapper.CCTVMapper;
import geomex.notice.model.CctvVo;
import geomex.notice.model.CoordinateVo;
import geomex.notice.model.GeometryVo;

@Service
public class CctvService {

	@Autowired
	private CCTVMapper cctvMapper;

	public ArrayList<CctvVo> getCctvsInEmd(GeometryVo geometry) {
		return cctvMapper.selectCctvInEmd(geometry);
	}

/*	public int getCCTVCountInBuffer(CoordinateVo coordinate) {
		int count = cctvMapper.selectCCTVCountInBuffer(coordinate);		
		return count;
	}*/
}
