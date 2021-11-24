package Object;

public class CarTypePrivateCar implements CarType {

	private double rate = 1;
	private static CarTypePrivateCar instance = new CarTypePrivateCar();

	public static CarTypePrivateCar getInstance() {
		return instance;
	}

	public String getCarType() {
		return "PrivateCar";
	}

	public double getPlatformChargeRate() {
		return rate;
	}

	public void setPlatformChargeRate(double rate) {
		this.rate = rate;
	}

}