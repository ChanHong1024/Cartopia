package Object;

public class CarTypeSupercar implements CarType {

	private double rate = 1;
	private static CarTypeSupercar instance = new CarTypeSupercar();

	public static CarTypeSupercar getInstance() {
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