package geomex.notice.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import geomex.notice.model.CctvVo;
import geomex.notice.model.CoordinateVo;
import geomex.notice.model.GeometryVo;
import geomex.notice.model.ReviewVo;
import geomex.notice.service.CctvService;

@RestController

public class CctvController {

    @Autowired
    private CctvService cctvService;
   
   /* @PostMapping("/countIncctv.do")
    public ResponseEntity<Map<String, Integer>> getCCTVCountInBuffer(@RequestBody CoordinateVo coordinate) {
    	System.out.println(coordinate.getCoordinate());
    	int count = cctvService.getCCTVCountInBuffer(coordinate);
        Map<String, Integer> response = new HashMap<>();
        response.put("count", count);
        return ResponseEntity.ok(response);
    }*/
    @PostMapping(value="emd/cctvInEmd.do",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public  ArrayList<CctvVo> emdCctvInEmd(@RequestBody GeometryVo geometry)
    {
    	ArrayList<CctvVo> cctvList=cctvService.getCctvsInEmd(geometry);
    	
    	return cctvList;
    }

}


