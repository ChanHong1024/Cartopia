package Object;

import java.util.Vector;
import java.util.Date;

public class AccountCoupon extends Coupon {

	private static Vector<Account> owners = new Vector<>();

	public void addCouponOwner(Account account) {
		owners.add(account);
	}

	public void addCouponOwners(Vector<Account> accounts) {
		for (Account itr : accounts) {
			owners.add(itr);
		}
	}

	public boolean isAccountOwnThis(Account a){
		return (owners.contains(a)) ? true : false;
	}

	public AccountCoupon(String name, double discountRate, Date expireDate) {
		super(name, discountRate, expireDate);
	}

	@Override
	public String toString() {
		String onwerNameList = "";
		for (Account ac : owners) {
			onwerNameList += ac.getUsername() + " ";
		}

		return super.toString() + "\n Type : Account Coupon" + "\n Owner : " + onwerNameList;
	}

	public static Vector<AccountCoupon> getAccountCoupons(Account a){
		Vector<AccountCoupon> result = new Vector<>();
		for(Coupon itr : Coupon.couponList){
			if(itr.getClass() == AccountCoupon.class){
				if(((AccountCoupon)itr).isAccountOwnThis(a)){
					result.add((AccountCoupon)itr);
				}
			}
		}
		return result;
	}

}