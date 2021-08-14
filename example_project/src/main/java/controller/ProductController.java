package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import login.LoginRequest;
import product.ProductRequest;
import product.ProductService;

@Controller
public class ProductController {
	@Autowired
	ProductService productService;
	
	@PostMapping("/productRegist")
	public String productRegistRequest(ProductRequest productRequest,MultipartFile[] upload, HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession(false);
		// 로그인 정보가 담겨있는 session Attribute 가져와서 id 정보를 productRequest에 삽입 (등록자 정보 동기화) 
		String id = ((LoginRequest)session.getAttribute("MemberInfo")).getId();
		productRequest.setManager_id(id);
		
		String code = productService.getCode(productRequest);
		
		System.out.println("상품 등록 정보 - \n상품명 : "+productRequest.getProduct_name()+
				"\n카테고리 코드 : " + code + "\n상품가격 : "+productRequest.getProduct_price() + "\n상품내용 : " +
				productRequest.getProduct_value() + "\n등록자 : " + productRequest.getManager_id());
		
		// 상품 등록하는 메소드
		productService.registProduct(productRequest,upload,request,id);
		System.out.println(productService.getProductId(productRequest));
		return "product/productSuccess";
	}
	
	@RequestMapping("/productTest")
	public String testtest() {
		return "product/productTest";
	}
}
