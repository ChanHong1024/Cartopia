package Object;

import java.util.Vector;
import java.util.Date;

public abstract class Coupon {

	public static Vector<Coupon> couponList = new Vector<>();
	private String name;
	private double discountPercentage;
	private Date expireDate;

	public Coupon(String name, double discountPercentage, Date expireDate) {
		this.name = name;
		this.discountPercentage = discountPercentage;
		this.expireDate = expireDate;
		Coupon.couponList.add(this);
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

	public double getDiscountPercentage() {
		return this.discountPercentage;
	}

	public void getDiscountPercentage(double rate) {
		this.discountPercentage = rate;
	}

	public static Coupon searchCouponByName(String name) {
		for (Coupon coupon : couponList) {
			if (coupon.getName().equals(name))
				return coupon;
		}
		return null;
	}

	public String toString() {
		return "Coupon Name : " + name + "\n Discount Percentage : " + discountPercentage + "\n Expire Date : "
				+ expireDate;
	}

}