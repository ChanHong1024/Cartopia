package Object;
import java.util.Vector;
import java.util.Date;

public class AccountCoupon extends Coupon {

	private static Vector<Account> owners;

	public void addCouponOwner(Account account) {
		owners.add(account);
	}

	public void addCouponOwners(Vector<Account> accounts) {
		for(Account itr : accounts){
			owners.add(itr);
		}
	}

	public AccountCoupon(String status, String name, double discountRate, Date expireDate) {
		super(status, name, discountRate, expireDate);
	}

}