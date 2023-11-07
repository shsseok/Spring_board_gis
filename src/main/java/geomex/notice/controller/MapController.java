package geomex.notice.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MapController {
	 	@GetMapping("/map.do")
	    public String showMapPage(HttpServletRequest request) {	    	 
	        return "map";
	 	}
}
