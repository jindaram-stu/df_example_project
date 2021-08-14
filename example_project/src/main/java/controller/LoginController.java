package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import login.LoginRequest;
import login.LoginService;
@Controller
public class LoginController {
	HttpSession session;
	private LoginService loginService;
	
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	@RequestMapping("/login") 
	public String loginPage() {
		return "login/login"; 
	}
	
	@PostMapping("/loginProcess")
	public String loginProcess(LoginRequest loginRequest,HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		if (loginService.processLogin(loginRequest, response)) { // 실패시
			return "index";
		} else { // 성공시
			session = request.getSession();
			session.setAttribute("MemberInfo",loginRequest);
			return "login/loginSucces";
		}
	
	}
	
	@RequestMapping("/logout")
	public String logout() {
		session.invalidate();
		return "index";
	}
	
	
}
