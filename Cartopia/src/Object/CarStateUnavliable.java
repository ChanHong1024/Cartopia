package Object;

public class CarStateUnavliable implements CarState {

	private static CarStateUnavliable instance = new CarStateUnavliable();

	public static CarStateUnavliable getInstance() {
		return instance;
	}

	public String getState() {
		return "Unavaliable";
	}

}