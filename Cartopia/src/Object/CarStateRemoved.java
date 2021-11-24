package Object;

public class CarStateRemoved implements CarState {

	private static CarStateRemoved instance = new CarStateRemoved();

	public static CarStateRemoved getInstance() {
		return instance;
	}

	public String getState() {
		return "Removed";
	}

}