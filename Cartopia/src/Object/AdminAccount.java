package Object;

public class AdminAccount extends Account {

	public AdminAccount(String username, String password, String email) {
		super(username, password, email);
	}

	public void addCoupon(Coupon coupon) {
		Coupon.couponList.add(coupon);
	}

	public static void removeCoupon(Coupon coupon) {
		Coupon.couponList.remove(coupon);
	}

	public void setPlatformChargeRate(CarType carType, int rate) {
		carType.setPlatformChargeRate(rate);
	}

}