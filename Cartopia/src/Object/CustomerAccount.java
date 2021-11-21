package Object;

import java.util.Vector;

public class CustomerAccount extends Account {

	private double coin;
	private License license;

	public CustomerAccount(License license, String username, String password, String email) {
		super(username, password, email);
		this.license = license;
		coin = 0;
	}

	public void withdrawal(double amount) {
		coin -= amount;
	}
	
	public void deposit(double amount) {
		coin += amount;
	}

	public double getCoin() {
		return this.coin;
	}

	public License getLicense() {
		return this.license;
	}

	public void setLicense(License license) {
		this.license = license;
	}

	public Vector<Car> getOwnedCar() {
		Vector<Car> carlist = new Vector<Car>();
		for(Car itr : Car.carList){
			if(itr.getAccount() == this){
			carlist.add(itr);
			}		
		}
		return carlist;
	}

}