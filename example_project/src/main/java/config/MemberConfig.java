package config;



import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import db.MemberDAO;
import db.ProductDAO;
import join.RegisterRequest;
import join.RegisterService;
import login.LoginService;
import mypage.MyPageService;
import product.ProductService;

@Configuration
@EnableTransactionManagement
public class MemberConfig {
	
	
	@Bean
	public MemberDAO memberDao() {
		return new MemberDAO();
	}
	
	@Bean
	public ProductDAO productDao() {
		return new ProductDAO();
	}
	
	@Bean 
	public RegisterService registerService() {
		return new RegisterService(memberDao());	
	}
	
	@Bean 
	public LoginService loginService() {
		return new LoginService(memberDao());	
	}
	
	@Bean 
	public MyPageService myPageService() {
		return new MyPageService(memberDao());
	}
	
	@Bean ProductService productService() {
		return new ProductService(productDao());
		
	}
	
}
