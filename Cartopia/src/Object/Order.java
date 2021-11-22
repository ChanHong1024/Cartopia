package Object;

import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

public class Order {

	public static Vector<Order> ordersList  = new Vector<>();
	private Account renter;
	private Account lender;
	private OrderState state;

	public OrderState getState() {
		return this.state;
	}

	public void setState(OrderState state) {
		this.state = state;
	}

	public Date getDate() {
		return this.date;
	}


	public Account getRenter() {
		return this.renter;
	}

	public void setRenter(Account renter) {
		this.renter = renter;
	}

	public Account getLender() {
		return this.lender;
	}

	public void setLender(Account lender) {
		this.lender = lender;
	}

	public Coupon getCoupon() {
		return this.coupon;
	}

	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}

	public Car getCar() {
		return this.car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public int getDays() {
		return this.days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public Double getOrderPrice() {
		if(coupon != null)
			return this.car.getPricePerDay() * days * (1 - coupon.getDiscountPercentage());
		else
			return this.car.getPricePerDay() * days;
	}

	private Rating rating;
	private Coupon coupon;
	private Car car;
	private Date date;
	private int days;

	public Order(Account renter, Account lender, Car car, int days, Date date, Coupon coupon) {
		this.renter = renter;
		this.lender = lender;
		this.car = car;
		this.coupon = coupon;
		this.date = date;
		this.days = days;
		this.state = OrderStatePendingForApprove.getInstance(); 
		Order.ordersList.add(this);
		
	}

	public Order(Account renter, Account lender, int days, Date date, Car car) {
		this.renter = renter;
		this.lender = lender;
		this.car = car;
		this.date = date;
		this.days = days;
		this.state = OrderStatePendingForApprove.getInstance(); 
		Order.ordersList.add(this);
	}


	public Rating getRating() {
		return this.rating;
	}

	public void setRating(Rating rating) {
		this.rating = rating;
	}

	public Date getStartDate() {
		return this.date;
	}

	public Date getEndDate() {
		Date dt = new Date(date.getTime() + (1000 * 60 * 60 * 24) * days);
		return dt;
	}	

	public void setDate(Date date) {
		this.date = date;
	}

	public double getPrice(){
		return (coupon == null) ? car.getRentPrice(days) : car.getRentPrice(days) * coupon.getDiscountPercentage();
	}

	@Override
	public String toString() {
		String coupon_str = (coupon != null ) ? coupon.toString() : "//";
		String rating_str = (rating != null ) ? rating.getComment() : "//";
		return 
		"Order Infomation\n----------------------------------\n"+
		"state: " + state.getState() + "\n" +
		"renter: " + renter.getUsername() + "\n" +
		"lender: " + lender.getUsername() + "\n" +
		"rating: " + rating_str + "\n" +
		"coupon: " + coupon_str + "\n" +
		"car: " + car.getCarName() + "\n" +
		"date: " + getStartDate().toString() + " - " + getEndDate().toString() + "\n" +
		"total days: " + getDays() + "\n" +
		"total price: " + getPrice()+ "\n=================================\n";
	}

	/////////
	
	public static Vector<Order> getOrderByRenter(String username){
		Vector<Order> result = new Vector<>();
		for(Order itr: ordersList){
			if(itr.getRenter().getUsername().equals(username))
				result.add(itr);
		}
		return result;
	}

	public static Vector<Order> getOrderByLender(String username){
		Vector<Order> result = new Vector<>();
		for(Order itr: ordersList){
			if(itr.getLender().getUsername().equals(username))
				result.add(itr);
		}
		return result;
	}


	public static int comfirmOrder(Order o, Account renter){
		if(o.getRenter() == renter){
			o.getCar().setCarState(CarStateUnavailable.getInstance());
			o.setState(OrderStateOrderConfirmed.getInstance());
			return 0;
		}else{
			//wrong renter value
			return -1;
		}
	}

	public static int finishOrder(Order o, Account lender, Rating rating){
		if(o.getLender() == lender){
			o.getCar().setCarState(CarStateAvailable.getInstance());
			o.setState(OrderStateOrderComplete.getInstance());
			o.setRating(rating);
			return 0;
		}else{
			//wrong renter value
			return -1;
		}
	}
	
	public static boolean isOrderDateVaild(Car c, Date d, int days) {
		Date startDate = d;
		Calendar cal = Calendar.getInstance(); 
		cal.setTime(startDate); 
		cal.add(Calendar.DATE, days);
		Date endDate = cal.getTime();
		for(Order itr : ordersList) {
			if(itr.getCar() == c) {
				Date startDate_itr = d;
				cal.setTime(startDate_itr); 
				cal.add(Calendar.DATE, itr.getDays());
				Date endDate_itr = cal.getTime();
				if(startDate.after(startDate_itr) && startDate.before(endDate_itr) || endDate.after(startDate_itr) && endDate.before(endDate_itr)) {
					return false;
				}
			}
		}
		return true;
	}


	

}