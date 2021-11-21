package Object;

public class CarTypeMotorCycle implements CarType {

	private double rate;
	
	private static CarTypeMotorCycle instance = new CarTypeMotorCycle();

	public static CarTypeMotorCycle getInstance() {
		return instance;
	}

	public String getCarType() {
		return "MotorCycle";
	}

	public double getPlatformChargeRate() {
		return rate;
	}

	public void setPlatformChargeRate(double rate) {
		this.rate = rate;
	}

}