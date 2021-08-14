package product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequest {
	private String product_name;
	private String categoryCode;
	private String category1;
	private String category2;
	private int product_price;
	private String product_value;
	private String manager_id;
}
