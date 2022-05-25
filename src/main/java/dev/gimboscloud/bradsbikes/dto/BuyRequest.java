package dev.gimboscloud.bradsbikes.dto;

public class BuyRequest {

	Integer productID;
	Integer motorbikeID;
	Integer quantity;

	public Integer getProductID() {
		return productID;
	}

	public void setProductID(Integer productID) {
		this.productID = productID;
	}

	public Integer getMotorbikeID() {
		return motorbikeID;
	}

	public void setMotorbikeID(Integer motorbikeID) {
		this.motorbikeID = motorbikeID;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
