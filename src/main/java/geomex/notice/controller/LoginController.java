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
import geomex.notice.model.UserVo;
import geomex.notice.service.UserService;

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
    	System.out.println(loginData.getUserId());
        Map<String, Integer> response = new HashMap<>();  
        int result = userService.login(loginData, session);
        response.put("result", result);
        return response;
    }
    
    @PostMapping("/logoutAction.do")
    @ResponseBody
    public Map<String, Integer> handleLogout(HttpSession session) {
        userService.logout(session);
        Map<String, Integer> response = new HashMap<>();
        response.put("result", -1);  // 로그아웃 성공을 나타내는 코드
        return response;
    }

}
