package Object;

public class OrderStateOrderConfirmed implements OrderState {

	private static OrderStateOrderConfirmed instance = new OrderStateOrderConfirmed();

	private OrderStateOrderConfirmed(){}
	public static OrderStateOrderConfirmed getInstance(){
		return instance;
	}

	public String getState(){
		return "Order Confirmed";
	}

}