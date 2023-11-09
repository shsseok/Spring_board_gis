package geomex.notice.controller;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import geomex.notice.model.UserVo;
import geomex.notice.service.UserService;
import geomex.notice.session.SessionConst;

@Controller
public class LoginController {
	
    @Autowired
    private UserService userService;

    @GetMapping("/login.do")
    public String showLoginPage() {
    	System.out.println("login페이지 매핑 됨");  
        return "login";
    }

    @PostMapping("/loginAction.do")
    @ResponseBody
    public Map<String, Integer> handleLogin(@RequestBody UserVo loginData, HttpSession session) {
    	
    	int isSuccess=0;
        Map<String, Integer> response = new HashMap<>();  
        isSuccess = userService.login(loginData);
        if(isSuccess == 1)
        {//로그인이 성공했다면
        	session.setAttribute(SessionConst.USER_ID, loginData.getUserId());
        	response.put("result", isSuccess);
        	return response;
        }
        response.put("result", isSuccess);
        return response;
    }
    
    @PostMapping("/logoutAction.do")
    @ResponseBody
    public Map<String, Object> handleLogout(HttpSession session) {
        Map<String, Object> response = new HashMap<>();
                   
        if (session != null) {
            session.invalidate(); 
            response.put("result", 1);  
        } 

        return response;
    }


}