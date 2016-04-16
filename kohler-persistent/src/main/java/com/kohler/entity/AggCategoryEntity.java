package com.kohler.entity;

/**
 * Class Function Description Product
 * 
 * @author ys
 * @Date 2014年10月8日
 */
public class AggCategoryEntity extends CategoryEntity {
	
	private static final long serialVersionUID = 6042065009897319111L;
	
	private String productName;
	private String productId;
	
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
}