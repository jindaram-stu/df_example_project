package product;

import java.io.File;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import db.MemberDAO;
import db.ProductDAO;
import db.ProductDAO;

@Service("product")
public class ProductService {
	
	
	private ProductDAO productDao;
	
	public ProductService (ProductDAO productDao) {
		this.productDao = productDao;
	}
	
	// codedata.xml ������ �ҷ��ͼ� productRequest�� �ִ� ī�װ��� ī�װ� �ڵ�� ����
	public String getCode(ProductRequest productRequest) throws Exception {
		String cate1 = productRequest.getCategory1();
		String cate2 = productRequest.getCategory2();
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = factory.newDocumentBuilder();
		Document document = documentBuilder.parse("classpath:codedata/categorycode.xml");
		
		Element root = document.getDocumentElement();
		
		System.out.println(root + " �� " + root.getAttribute("name"));
		
		NodeList productList = root.getElementsByTagName("product");
		
		for (int i=0; i<productList.getLength(); i++) {
			
			Node productNode = productList.item(i);
			
			if (productNode.getNodeType() == Node.ELEMENT_NODE) {
				Element productEle = (Element)productNode;
				
				//big
				NodeList bigList = productEle.getElementsByTagName("big");
				Element bigEle = (Element)bigList.item(0);
				Node big = bigEle.getFirstChild();
				
				//small
				NodeList smallList = productEle.getElementsByTagName("small");
				Element smallEle = (Element)smallList.item(0);
				Node small = smallEle.getFirstChild();
				
				if (big.getNodeValue().equals(cate1) && small.getNodeValue().equals(cate2)) {
					NodeList cateList = productEle.getElementsByTagName("code");
					Element codeEle = (Element)cateList.item(0);
					Node code = codeEle.getFirstChild();
					return code.getNodeValue();
				}
			}
		}
		return "badCode";
	}

	// ��ǰ �̹����� �����ϴ� method
	public Boolean saveProductImage(MultipartFile[] upload,HttpServletRequest request,String id,int productId) {
		
		
		int imgNum = 1;
		// ����� ID
		String registId = id;
		String saveDir = 
				request.getSession().getServletContext().getRealPath("/resource/img");
		System.out.println(saveDir);
		File dir = new File(saveDir);
		if(!dir.exists()) {
			dir.mkdir();
		}
		// �����̸� : ��¥_��ǰ��ȣ_�����_��ȣ(������ ������ �ø����).Ȯ����
		for (MultipartFile f : upload) {
			if (!f.isEmpty()) {
				
				// ���� �̸� ����
				String orifileName = f.getOriginalFilename();
				// Ȯ���� ����
				String ext = orifileName.substring(orifileName.lastIndexOf("."));
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				// ����Ǵ� ������ �̸�
				String reName = sdf.format(System.currentTimeMillis()) + "_" + productId + "_" + id + "_" + imgNum + ext;
				
				
				try {
				// �����ϴ� transferTo �޼ҵ�
				f.transferTo(new File(saveDir + "/" + reName));
				String pro_img_id = productId + "_" + imgNum;
				// db�� �����ϱ� ���� vo ����
				ProductImageRequest productImageRequest = new ProductImageRequest(pro_img_id,productId,reName,"resource/img/"+reName,f.getSize());
				System.out.println(productImageRequest.getPro_img_id());
				productDao.insertProductImageByBatis(productImageRequest);
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
			}
			imgNum++;
		}
		
		return true;	
	}

	public void registProduct(ProductRequest productRequest,MultipartFile[] upload,HttpServletRequest request,String id) throws Exception {
		String code = getCode(productRequest);
		productRequest.setCategoryCode(code);
		productDao.insertProductByBatis(productRequest);
		saveProductImage(upload,request,id,productDao.getProductIdByBatis(productRequest));
	}
	
	public int getProductId(ProductRequest productRequest) {
		return productDao.getProductIdByBatis(productRequest);
	}
	
}