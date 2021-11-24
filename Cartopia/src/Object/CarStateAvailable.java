package Object;

public class CarStateAvailable implements CarState {

	private static CarStateAvailable instance = new CarStateAvailable();

	public static CarStateAvailable getInstance() {
		return instance;
	}

	public String getState() {
		return "Avaliable";
	}
	
}