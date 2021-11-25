package Object;

public class OrderStateOrderComplete implements OrderState {

	private static OrderStateOrderComplete instance = new OrderStateOrderComplete();

	private OrderStateOrderComplete(){}
	public static OrderStateOrderComplete getInstance(){
		return instance;
	}

	public String getState(){
		return "Order Completed";
	}

}