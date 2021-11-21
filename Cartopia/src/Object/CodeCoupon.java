package Object;

import java.util.Date;

public class CodeCoupon extends Coupon {

	private String code;

	public CodeCoupon(String status, String name, double discountRate, String code, Date expireDate) {
		super(status, name, discountRate, expireDate);
		this.code = code;
	}

	public String getCode(){
		return code;
	}

}