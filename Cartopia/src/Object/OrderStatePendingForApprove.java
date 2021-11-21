package Object;

public class OrderStatePendingForApprove implements OrderState {

	private static OrderStatePendingForApprove instance = new OrderStatePendingForApprove();

	private OrderStatePendingForApprove(){}
	public static OrderStatePendingForApprove getInstance(){
		return instance;
	}

	public String getState(){
		return "Pending For Approve";
	}

}