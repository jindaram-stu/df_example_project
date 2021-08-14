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
		// �α��� ������ ����ִ� session Attribute �����ͼ� id ������ productRequest�� ���� (����� ���� ����ȭ) 
		String id = ((LoginRequest)session.getAttribute("MemberInfo")).getId();
		productRequest.setManager_id(id);
		
		String code = productService.getCode(productRequest);
		
		System.out.println("��ǰ ��� ���� - \n��ǰ�� : "+productRequest.getProduct_name()+
				"\nī�װ� �ڵ� : " + code + "\n��ǰ���� : "+productRequest.getProduct_price() + "\n��ǰ���� : " +
				productRequest.getProduct_value() + "\n����� : " + productRequest.getManager_id());
		
		// ��ǰ ����ϴ� �޼ҵ�
		productService.registProduct(productRequest,upload,request,id);
		System.out.println(productService.getProductId(productRequest));
		return "product/productSuccess";
	}
	
	@RequestMapping("/productTest")
	public String testtest() {
		return "product/productTest";
	}
}
