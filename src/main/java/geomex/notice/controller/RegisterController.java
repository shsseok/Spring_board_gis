package geomex.notice.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;
import org.springframework.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import geomex.notice.model.UserVo;
import geomex.notice.service.UserService;

@Controller
public class RegisterController {

	@Autowired
	private UserService userService; // 가정: UserService 클래스가 있고, 회원 가입 메서드를 제공한다.

	@GetMapping("/register.do")
	public String showRegisterPage() {
		System.out.println("register페이지 매핑 됨");

		return "/register";
	}

	@Resource
	private Validator validator;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(this.validator);
	}

	@PostMapping(value="/registerAction.do",consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> handleRegistration(@Valid @RequestBody UserVo uservo, BindingResult result) {

		Map<String, Object> response = new HashMap<>();
		ArrayList<String> errors=new ArrayList<String>();
		if (result.hasErrors()) {//유효성 검사를 통해서			
	        for (ObjectError error : result.getAllErrors()) {
	            	errors.add(error.getDefaultMessage());
	        }
	        response.put("error", errors);
			response.put("result", 0);
		} else {
			boolean isRegistered = userService.register(uservo);

			if (isRegistered) {
				response.put("result", 1); // 성공
				response.put("message","회원가입에 성공하셨습니다.");
			} else {
				response.put("result", 0); // 실패 --> 아이디가 중복될 때
				response.put("message", "이미 존재하는 아이디 입니다.");
			}

		}
		return response;
	}

}
