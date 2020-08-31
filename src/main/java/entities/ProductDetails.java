package entities;

public class ProductDetails {

	private static String total_products;
	private static String Total_thipping;
	private static String total_bill;
	private static String productName;
	private static String product_quantity;

	public static String getTotal_products() {
		return total_products;
	}

	public static void setTotal_products(String total_products) {
		ProductDetails.total_products = total_products;
	}

	public static String getTotal_thipping() {
		return Total_thipping;
	}

	public static void setTotal_thipping(String total_thipping) {
		Total_thipping = total_thipping;
	}

	public static String getTotal_bill() {
		return total_bill;
	}

	public static void setTotal_bill(String total_bill) {
		ProductDetails.total_bill = total_bill;
	}

	public static String getProductName() {
		return productName;
	}

	public static void setProductName(String productName) {
		ProductDetails.productName = productName;
	}

	public static String getProduct_quantity() {
		return product_quantity;
	}

	public static void setProduct_quantity(String product_quantity) {
		ProductDetails.product_quantity = product_quantity;
	}

}
