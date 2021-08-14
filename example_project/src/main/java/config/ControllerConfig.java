package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import controller.JoinController;
import controller.LoginController;
import controller.MainController;
import controller.MyPageController;
import controller.ProductController;
import join.RegisterService;
import login.LoginService;
import mypage.MyPageService;

@Configuration
public class ControllerConfig {
	
	@Autowired
	private RegisterService registerService;
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private MyPageService myPageService;

	@Bean
	public MainController mainController() {
		return new MainController();
	}
	
	@Bean 
	public LoginController loginController() {
		LoginController loginController = new LoginController();
		loginController.setLoginService(loginService);
		return loginController;
	}

	@Bean
	public JoinController joinController() {
		JoinController joinController = new JoinController();
		joinController.setRegisterService(registerService);
		return joinController;
	}
	
	@Bean
	public MyPageController myPageController() {
		return new MyPageController(myPageService);
	}
	
	@Bean
	public ProductController productController() {
		return new ProductController();
	}
}
