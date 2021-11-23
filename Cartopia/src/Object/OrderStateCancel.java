package Object;

public class OrderStateCancel implements OrderState {

	private static OrderStateCancel instance = new OrderStateCancel();

	private OrderStateCancel(){}
	public static OrderStateCancel getInstance(){
		return instance;
	}

	public String getState(){
		return "Order Cancelled";
	}

}