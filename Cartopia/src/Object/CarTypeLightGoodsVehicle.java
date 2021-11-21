package Object;

public class CarTypeLightGoodsVehicle implements CarType {

	private double rate;
	private static CarTypeLightGoodsVehicle instance = new CarTypeLightGoodsVehicle();

	public static CarTypeLightGoodsVehicle getInstance() {
		return instance;
	}

	public String getCarType() {
		return "LightGoodsVehicle";
	}

	public double getPlatformChargeRate() {
		return rate;
	}

	public void setPlatformChargeRate(double rate) {
		this.rate = rate;
	}

}