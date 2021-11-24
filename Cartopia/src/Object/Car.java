package Object;

import java.util.Vector;

public class Car {

	public static Vector<Car> carList  = new Vector<>();
	private String carName;
	private double pricePerDay;
	private CarType carType;
	private CarState carState;
	private Account account;

	public Car(Account ac, CarType carType, String carName, double pricePerDay) {
		this.account = ac;
		this.carType = carType;
		this.carName = carName;
		this.carState = CarStateAvailable.getInstance();
		this.pricePerDay = pricePerDay;
		
		carList.add(this);
	}

	public static boolean removeCar(Car car,Account ac){
		for(Car itr : carList){
			if(itr == car && itr.getAccount().equals(ac)){
				carList.remove(car);
				return true;
			}
		}
		return false;
	}

	public static boolean removeCar(Car car){
		for(Car itr : carList){
			if(itr == car){
				carList.remove(car);
				return true;
			}
		}
		return false;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public double getPricePerDay() {
		return this.pricePerDay;
	}

	public void setPricePerDay(double pricePerDay) {
		this.pricePerDay = pricePerDay;
	}

	public void setCarType(CarType carType) {
		this.carType = carType;
	}

	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public double getRentPrice(int day) {
		return day * pricePerDay;
	}

	public CarType getCarType() {
		return carType;
	}

	public CarState getCarState() {
		return this.carState;
	}

	public void setCarState(CarState carState) {
		this.carState = carState;
	}

	public String getCarName() {
		return this.carName;
	}

	@Override
	public String toString() {
		return "Car name: " + getCarName() + "\n" +
			"Price Per Day: " + getPricePerDay() + "\n" +
			"Car type: " + getCarType().getCarType() + "\n" +
			"Car state: " + getCarState().getState() + "\n" +
			"owner: " + getAccount().getUsername() + "\n";
	}

	public static Vector<Car> getCarByAccount(Account a){
		Vector<Car> resultList = new Vector<>();
		for(Car itr : carList){	
			if(itr.getAccount() == a){
				resultList.add(itr);
			}
		}
		return resultList;
	}


	// pass null if not filtering that properties
	public static Vector<Car> SearchCar(Account owner, String carName, CarType carType, CarState carState, double priceUpperBound, double priceLowerBound){
		Vector<Car> resultList = new Vector<>();
		for(Car itr : carList){	
			if((owner == null || itr.getAccount() == owner) && 
				(carName == null || itr.getCarName().indexOf(carName) != -1)  &&
				(carType == null || itr.getCarType() == carType) &&
				(carState == null || itr.getCarState() == carState) &&
				(itr.getPricePerDay() <=  priceUpperBound) &&
				(itr.getPricePerDay() >= priceLowerBound) 
			){
				resultList.add(itr);
			}
		}
		return resultList;
	}


	
	/*
	public static Vector<Car> getAvaibleCarList(){
		Vector<Car> resultList = new Vector<>();
		for(Car itr : carList){
			if(itr.getCarState().getState().equals("Avaliable")){
				resultList.add(itr);
			}
		}
		return resultList;
	}
	*/



}