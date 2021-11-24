<<<<<<< HEAD
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

=======
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

>>>>>>> 8fccc0dc326f9d8dec9432d61a92fc85b51e8e77
}