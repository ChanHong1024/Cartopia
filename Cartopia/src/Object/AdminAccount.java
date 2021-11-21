package Object;

public class AdminAccount extends Account {

	public AdminAccount(String username, String password, String email) {
		super(username, password, email);
	}

	public void addCoupon(Coupon coupon) {
		Coupon.couponList.add(coupon);
	}

	public void removeCoupon(Coupon coupon) {
		for(Coupon itr : Coupon.couponList){
			if(coupon == itr){
				Coupon.couponList.remove(itr);
			}
		}
	}

	public void setPlatformChargeRate(CarType carType, int rate) {
		carType.setPlatformChargeRate(rate);
	}

}