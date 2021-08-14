package controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import db.MemberDAO;
import join.RegisterRequest;
import join.RegisterService;

@Controller
public class JoinController {
	
	private RegisterService registerService;
	
	public void setRegisterService(RegisterService registerService) {
		this.registerService = registerService;
	}

	
	@RequestMapping("/join")
	public String join() {
		return "join/join";
	}
	
	@PostMapping("/register")
	public String register(RegisterRequest rr,HttpServletRequest request ,HttpServletResponse response) throws Exception {
		registerService.processRegister(rr, response);
		return "index";
	}
}
