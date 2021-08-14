package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	HttpSession session;
	// <context-name>/main�� ���� Controller
	@RequestMapping("/main")
	public String mainController(HttpServletRequest request) {
		session = request.getSession(false);
		
		if(session==null || session.getAttribute("MemberInfo")==null) {
			System.out.println("���� index");
			return "index";
		} else {
			System.out.println(session.getId());
			System.out.println(session.isNew());
			if (!session.isNew()) {
				System.out.println("���� ls");
				return "login/loginSucces";
		}
			else {
				System.out.println("���� index");
				return "index";
			}
		}
		
		
	}
}
