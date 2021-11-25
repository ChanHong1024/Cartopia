package Object;

import java.util.Vector;
import java.util.Date;

public class AccountCoupon extends Coupon {

	private static Vector<Account> owners = new Vector<Account>();

	public void addCouponOwner(Account account) {
		owners.add(account);
	}

	public void addCouponOwners(Vector<Account> accounts) {
		for (Account itr : accounts) {
			owners.add(itr);
		}
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

}