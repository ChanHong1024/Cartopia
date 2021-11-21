package Object;

public class CarStateAvaliable implements CarState {

	private static CarStateAvaliable instance = new CarStateAvaliable();

	public static CarStateAvaliable getInstance() {
		return instance;
	}

	public String getState() {
		return "Avaliable";
	}
	
}