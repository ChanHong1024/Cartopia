package Object;

import java.util.Date;

public class CodeCoupon extends Coupon {

	private String code;

	public CodeCoupon( String name, double discountRate, String code, Date expireDate) {
		super(name, discountRate, expireDate);
		this.code = code;
	}

	public String getCode(){
		return code;
	}

	@Override
	public String toString(){
		return super.toString() + "\n Type : Code Coupon" + "\n Code : " + code;
	}

	public static CodeCoupon getCodeCoupons(String code){
		for(Coupon itr : Coupon.couponList){
			if(itr.getClass() == CodeCoupon.class){
				if(((CodeCoupon)itr).getCode().equals(code)){
					return (CodeCoupon)itr;
				}
			}
		}
		return null;
	}


}