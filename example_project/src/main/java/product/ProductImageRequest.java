package product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ProductImageRequest {
	private String pro_img_id;
	private int product_id;
	private String pro_img_name;
	private String pro_img_server;
	private long pro_filesize;
	
	public ProductImageRequest(String pro_img_id, int product_id,String pro_img_name, String pro_img_server, long pro_filesize) {
		this.pro_img_id = pro_img_id;
		this.product_id = product_id;
		this.pro_img_name = pro_img_name;
		this.pro_img_server = pro_img_server;
		this.pro_filesize = pro_filesize;
	}
}
