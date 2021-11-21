package Object;

public class CarStateUnavailable implements CarState {

	private static CarStateUnavailable instance = new CarStateUnavailable();

	public static CarStateUnavailable getInstance() {
		return instance;
	}

	public String getState() {
		return "Unavaliable";
	}

}