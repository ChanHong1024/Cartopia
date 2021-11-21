package Object;

import java.util.Vector;
import java.util.Date;

public abstract class Coupon {

	public static Vector<Coupon> couponList;
	private String status;
	private String name;
	private double discountPercentage;
	private Date expireDate;


	public Coupon(String status, String name, double discountPercentage, Date expireDate) {
		this.status = status;
		this.name = name;
		this.discountPercentage = discountPercentage;
		this.expireDate = expireDate;
	}


	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public void setDiscountPercentage(double discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

	public Date getExpireDate() {
		return this.expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public boolean isExpired(Date day) {
		return this.expireDate.before(day);
	}

	public double getDiscountPercentage(){
		return this.discountPercentage;
	}
	
	public void getDiscountPercentage(double rate){
		this.discountPercentage = rate;
	}

}