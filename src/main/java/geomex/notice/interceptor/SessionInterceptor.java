package geomex.notice.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class SessionInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HttpSession session = request.getSession(false);
		
		boolean isLoggedIn = (session != null && session.getAttribute("userId") != null);// 로그인 되어져있다면

		String loginURI = request.getContextPath() + "/login.do"; // 로그인 페이지의 URI
		String loaginActionURI = request.getContextPath() + "/loginAction.do";
		String registerURI = request.getContextPath() + "/register.do"; // 회원강비 페이지의 URI
		String RegisterActionURI = request.getContextPath() + "/registerAction.do";
		boolean isLoginRequest = request.getRequestURI().equals(loginURI); // 로그인 페이지 요청이 들어온다면?
		boolean isLoginServiceRequest = request.getRequestURI().equals(loaginActionURI);// 로그인 서비스에 요청이 들어온다면?
		boolean isRegisterRequest = request.getRequestURI().equals(registerURI);// 회원가입 페이지 요청이 들어온다면?
		boolean isRegisterServiceRequest =request.getRequestURI().equals(RegisterActionURI);// 회원가입 서비스 요청이 들어온다면?
		
		if(isLoggedIn || isLoginRequest || isRegisterRequest || isRegisterServiceRequest || isLoginServiceRequest)
		{
			return true; //컨트롤러의 다음 요청이 실행됨
		}
		else {
			response.sendRedirect(loginURI);
			return false; 
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

}
