import java.io.IOException;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;
import Object.*;

public class Cartopia {
	public static void main(String[] args) throws IOException, ParseException {
		Scanner s = new Scanner(System.in);
		String username;
		String password;
		char cmd;
		System.out.print(
				"╔═══════════════════════════════════════════════════════════════════════════════════════════════╗\n");
		System.out.print(
				"║                                                                                               ║\n");
		System.out.print("║	    _______      ____   ,---------.    ,-----.    .-------. .-./`)    ____		║\n");
		System.out.print(
				"║	   /   __  \\   .'  __ `.\\          \\ .'  .-,  '.  \\  _(`)_ \\\\ .-.') .'  __ `.		║\n");
		System.out.print(
				"║	  | ,_/  \\__) /   '  \\  \\`--.  ,---'/ ,-.|  \\ _ \\ | (_ o._)|/ `-' \\/   '  \\  \\		║\n");
		System.out.print(
				"║	,-./  )       |___|  /  |   |   \\  ;  \\  '_ /  | :|  (_,_) / `-'`\"`|___|  /  |		║\n");
		System.out.print(
				"║	\\  '_ '`)        _.-`   |   :_ _:  |  _`,/ \\ _/  ||   '-.-'  .---.    _.-`   |		║\n");
		System.out.print(
				"║	 > (_)  )  __ .'   _    |   (_I_)  : (  '\\_/ \\   ;|   |      |   | .'   _    |		║\n");
		System.out.print(
				"║	(  .  .-'_/  )|  _( )_  |  (_(=)_)  \\ `\"/  \\  ) / |   |      |   | |  _( )_  |		║\n");
		System.out.print(
				"║	 `-'`-'     / \\ (_ o _) /   (_I_)    '. \\_/``\".'  /   )      |   | \\ (_ o _) /		║\n");
		System.out.print("║	   `._____.'   '.(_,_).'    '---'      '-----'    `---'      '---'  '.(_,_).'		║\n");
		System.out.print(
				"║                                ©  Copyright  Group 11 Cartopia 2021                           ║\n");
		System.out.print(
				"╚═══════════════════════════════════════════════════════════════════════════════════════════════╝\n");
		Account admin = new AdminAccount("admin", "a", "whchan1024@gmail.com");
		Account a1 = new CustomerAccount(null, "hong", "a", "whchan1024@gmail.com");
		Car c1 = new Car(a1, CarTypeSupercar.getInstance(), "McLaren 720S", 1000);
		Car c2 = new Car(a1, CarTypePrivateCar.getInstance(), "Model X", 1500);
		Account a2 = new CustomerAccount(null, "andrew", "a", "andrew2048@gmail.com");
		Car c3 = new Car(a2, CarTypeSupercar.getInstance(), "BMW 7 Series", 800);
		Car c4 = new Car(a2, CarTypePrivateCar.getInstance(), "Model S", 500);
		Account a3 = new CustomerAccount(null, "nigel", "a", "nigel4096@gmail.com");

		String date_string = "25-11-2022";
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");      
		Date expireDate = formatter.parse(date_string); 
		
		AccountCoupon coupon1 = new AccountCoupon("Newer Welcome gift.", 0.75, expireDate);
		coupon1.addCouponOwner(a1);
		AccountCoupon coupon2 = new AccountCoupon("Newer Welcome gift 2.", 0.75, expireDate);
		coupon1.addCouponOwner(a1);
		CodeCoupon coupon3 = new CodeCoupon("Newer Welcome gift 3.", 0.75, "WELCOME" , expireDate);

		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	
		logout: while (true) {
			System.out.println("l - Login | format: l <username> <password>");
			System.out.println("x - Exit Cartopia");
			cmd = (char) s.next().charAt(0);
			if (cmd == 'l') {

				if (!s.hasNext()) {
					System.out.println("Wrong number of parameters! please try again.");
				}

				username = s.next();
				password = s.next();

				System.out.println(username + " " + password);

				if (Account.login(username, password)) {
					Long datetime = System.currentTimeMillis();
					Timestamp timestamp = new Timestamp(datetime);
					System.out.println("\nWelcome, " + username + "! Time now is : " + timestamp + ".");

					adminMenu: while (Account.getAccountByUsername(username).getClass().toString()
							.equals("class Object.AdminAccount")) {
						try {

						Account adminAC = Account.getAccountByUsername(username);
						System.out.println("l - List Coupon");
						System.out.println("a - Add Coupon");
						System.out.println("r - Remove Coupon");
						System.out.println("c - Change Platform Charge Rate");
						System.out.println("x - Exit And Logout");

						cmd = (char) s.next().charAt(0);
						switch (cmd) {
						case 'l':
							if (Coupon.couponList != null && !Coupon.couponList.isEmpty()) {
								for (Coupon coupon : Coupon.couponList) {
									System.out.println(coupon);
								}
							} else {
								System.out.println("No Coupon Found");
							}
							break;
						case 'a':
							System.out.println("Choose coupon to add:");
							System.out.println("a - Account Coupon | format: a <Coupon Name> <Discount Rate>");
							System.out
									.println("c - Code Coupon | format: c <Coupon Name> <Coupon Code> <Discount Rate>");
							System.out.println("x - go back to admin menu.");
							String couponName = "";
							String couponCode = "";
							Double discountRate = 1.0;

							Date couponExpireDate = null;
							String couponExpireDateInput = "";

							cmd = (char) s.next().charAt(0);
							switch (cmd) {
							case 'a':
								couponName = s.next();
								discountRate = s.nextDouble();
								System.out.println("please enter the exprie date. (dd/MM/yyyy)");
								couponExpireDateInput = s.next();
								if (null != couponExpireDateInput && couponExpireDateInput.trim().length() > 0) {
									try {
										couponExpireDate = (Date) format.parse(couponExpireDateInput);
									} catch (ParseException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
								AccountCoupon accountCoupon = new AccountCoupon(couponName, discountRate,
										couponExpireDate);
								accountCoupon.addCouponOwners(Account.accountList);
								System.out.println("Coupon Successfully Added");
								System.out.println("The Coupon Successfully Added");
								break;
							case 'c':
								couponName = s.next();
								couponCode = s.next();
								discountRate = s.nextDouble();
								System.out.println("please enter the exprie date. (dd/MM/yyyy)");
								couponExpireDateInput = s.next();
								if (null != couponExpireDateInput && couponExpireDateInput.trim().length() > 0) {
									try {
										couponExpireDate = (Date) format.parse(couponExpireDateInput);
									} catch (ParseException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
								new CodeCoupon(couponName, discountRate, couponCode, couponExpireDate);
								System.out.println("Coupon Successfully Added");
								break;
							case 'x':
								continue adminMenu;
							default:
								System.out.println("wrong cmd.");
							}
							break;
						case 'r':
							for (Coupon c : Coupon.couponList) {
								System.out.println(c);
							}
							System.out.println("Please Enter Coupon Name To Remove");
							s.nextLine();
							couponName = s.nextLine();
							Coupon couponSearchResult = Coupon.searchCouponByName(couponName);
							if (couponSearchResult == null) {
								System.out.println("Wrong Input Name");
							} else {
								AdminAccount.removeCoupon(couponSearchResult);
								System.out.println("Coupon Remove");
							}
							break;
						case 'c':
							System.out.println("What is your car type?");
							System.out.println("0 - Private Car");
							System.out.println("1 - Light Goods Vechicle");
							System.out.println("2 - Motorcycle");
							System.out.println("3 - Supercar");
							int ctn = s.nextInt();
							CarType _ct = null;

							switch (ctn) {
							case 0:
								_ct = CarTypePrivateCar.getInstance();
								break;
							case 1:
								_ct = CarTypeLightGoodsVehicle.getInstance();
								break;
							case 2:
								_ct = CarTypeMotorCycle.getInstance();
								break;
							case 3:
								_ct = CarTypeSupercar.getInstance();
								break;
							default:
								System.out.println("Please Select A valid Car Type");
								continue;
							}

							System.out.println("What is the changing rate? (default is 1)");
							Double chargeRate = s.nextDouble();
							_ct.setPlatformChargeRate(chargeRate);
							System.out.println(
									_ct.getCarType() + "has changed the charge rate to " + _ct.getPlatformChargeRate());
							break;
						case 'x':
							continue logout;
						default:
							System.out.println("wrong cmd.");
						}
						} catch (InputMismatchException i) {
							System.out.println("wrong cmd.");
							continue adminMenu;
						}
					}

					menu: while (true) {
						try{
						System.out.println("\n\nMain Menu *\\(^_^)/*\n=================================");
						System.out.println("s - Serach Car & Make order");
						System.out.println("o - Order Management");
						System.out.println("c - Car Management");
						System.out.println("a - Account Management");
						System.out.println("l - Logout");
						System.out.println("x - Exit Cartopia");
						System.out.println("\nPlease enter you cmd:");

						cmd = (char) s.next().charAt(0);
						// System.out.println("Your cmd is " + cmd);

						switch (cmd) {
						case 's':
							int temp;
							System.out.println("What type of car you are searching?");
							System.out.println("0 - All");
							System.out.println("1 - Private Car");
							System.out.println("2 - Light Goods Vechicle");
							System.out.println("3 - Motorcycle");
							System.out.println("4 - Supercar");
							temp = s.nextInt();
							CarType ct;
							CarState cs;
							double lowerBound, upperBound;

							switch (temp) {
							case 0:
								ct = null;
								break;
							case 1:
								ct = CarTypePrivateCar.getInstance();
								break;
							case 2:
								ct = CarTypeLightGoodsVehicle.getInstance();
								break;
							case 3:
								ct = CarTypeMotorCycle.getInstance();
								break;
							case 4:
								ct = CarTypeSupercar.getInstance();
								break;
							default:
								ct = null;
							}
							// System.out.println(ct.getCarType());l

							System.out.println("Do you know the name of cars? (No = n, Yes = y)");
							String carName;
							cmd = s.next().charAt(0);
							if (cmd == 'y') {
								carName = s.next();
							} else {
								carName = null;
							}

							System.out.println(
									"Do you want to search cars including unavailable cars? (No = n, Yes = y)");

							cmd = s.next().charAt(0);
							if (cmd == 'y') {
								cs = null;
							} else {
								cs = CarStateAvailable.getInstance();
							}

							System.out.println(
									"Do you want to search cars within a specific price range? (No = n, Yes = y <lowerbound> <upperbound> eg. y 0 1000)");
							cmd = s.next().charAt(0);
							if (cmd == 'y') {
								lowerBound = s.nextDouble();
								upperBound = s.nextDouble();
							} else {
								lowerBound = 0;
								upperBound = Double.POSITIVE_INFINITY;
							}

							Vector<Car> result = Car.SearchCar(null, carName, ct, cs, upperBound, lowerBound);

							for (int i = 0; i < result.size(); i++) {
								System.out.println("=================================");
								System.out.println("Car search index: " + i);
								System.out.println("Car owner: " + result.get(i).getAccount().getUsername());
								System.out.println("Car name: " + result.get(i).getCarName());
								System.out.println("Car type: " + result.get(i).getCarType().getCarType());
								System.out.println("Car price (per day): " + result.get(i).getPricePerDay());
								System.out.println("=================================");
							}

							System.out.println("Further action:");
							System.out.println("o - make order");
							System.out.println("x - go back to main menu.");
							cmd = s.next().charAt(0);
							switch (cmd) {
							case 'o':
								System.out.println("please enter car search index of the car you want to rent.");
								int carSerachIndex = s.nextInt();

								CustomerAccount lender = (CustomerAccount) result.get(carSerachIndex).getAccount();
								CustomerAccount renter = (CustomerAccount) Account.getAccountByUsername(username);
								Date startDate = null;
								while (true) {
									System.out.println("please enter start date you want to rent. (dd/MM/yyyy)");
									String cinput = s.next();
									if (null != cinput && cinput.trim().length() > 0) {
										try {
											Date todayDatePlusTwo = new Date(
													java.util.Calendar.getInstance().getTime().getTime()
															+ 86400000 * 2); // 86400000 = 1 day
											startDate = (Date) format.parse(cinput);
											if (startDate.before(todayDatePlusTwo)) {
												System.out.println(
														"You can only book a car three days in advance, try again?");
												continue;
											}
											break;
										} catch (ParseException e) {
											System.out.println("data format wrong, please try again.");
											continue;
										}
									}
								}

								System.out.println("please enter days you want to rent.");
								int days = s.nextInt();
								System.out.println("Would you like to use coupon in this order?(No = n, Yes = y)");
								
								cmd = s.next().charAt(0);

								Coupon cp = null;

								if(cmd == 'y'){


									coupon: while(true){
										System.out.println("What type of coupon you want to use?");
										System.out.println("0 - Account Coupon");
										System.out.println("1 - Code Coupon");
										System.out.println("x - Go Back");
										cmd = s.next().charAt(0);
										switch(cmd){
											case '0': 
												Vector<AccountCoupon> ac = AccountCoupon.getAccountCoupons(Account.getAccountByUsername(username));
												System.out.println("Your coupon");
												System.out.println("=================================");
												for(int i = 0;i < ac.size();i++){
													System.out.println("Coupon index: " + i + " - "+ ac.get(i).getName() + ", Discount: " +  ac.get(i).getDiscountPercentage() + "Expire date:" + ac.get(i).getExpireDate().toString());
												}
												System.out.println("Please input the coupon index you want to use:");
												temp = s.nextInt();
												System.out.println(temp + " _ " + ac.size());
												if(temp >= ac.size() || temp < 0 ){
													System.out.println("Please input the correct coupon index!!");
												}else{
													System.out.println("Coupon has been added to order!");
													cp = ac.get(temp);
													break coupon;
												}									
												break;
											case '1': 
												System.out.println("Please input the coupon index you want to use:");
												s.nextLine();
												String code = s.nextLine();
												CodeCoupon cc = CodeCoupon.getCodeCoupons(code);
												if(cc == null){
													System.out.println("This code is invalid.");
												}else{
													System.out.println("Coupon has been added to order!");
													cp = cc;
													break coupon;
												}
												break;
											case 'x':
												break coupon;
										}
									}
								}

								if (Order.isOrderDateVaild(result.get(carSerachIndex), startDate, days)) {
									Order o;
									if(cp != null){
										o = new Order(renter, lender, days, startDate, result.get(carSerachIndex));
									}else{
										o = new Order(renter, lender, days, startDate, result.get(carSerachIndex), cp);
									}
									if (o.getRentPrice() > renter.getCoin()) {
										System.out.println("Not enough coin to create order, order cancelled.");
										Order.cancelOrder(o);
									} else {
										System.out.println("Pending the lender to accept the order.");
									}

								} else {
									System.out.println(
											"This car was reserved for another customer during this order date, try again with other order date.");
								}
								break;
							case 'x':
								break;
							default:
								System.out.println("wrong cmd.");
							}

							break;
						case 'o':
							Vector<Order> ro = Order.getOrderByRenter(username);
							Vector<Order> lo = Order.getOrderByLender(username);
							Vector<Order> co = Order.getCanceledOrderByUser(username);

							if (ro.size() > 0) {
								System.out.println("Rent Order");
								for (Order itr : ro) {
									if (itr.getState().equals(OrderStateOrderConfirmed.getInstance())
											|| itr.getState().equals(OrderStatePendingForApprove.getInstance()))
										System.out.println(itr.toString());
								}
							}

							if (lo.size() > 0) {
								System.out.println("Lend Order");
								for (Order itr : lo) {
									if (itr.getState().equals(OrderStatePendingForApprove.getInstance())|| itr
												.getState().equals(OrderStateOrderConfirmed.getInstance()))
										System.out.println(itr.toString());
								}
							}

							if (co.size() > 0) {
								System.out.println("Cenceled Order");
								for (Order itr : co) {
									if (itr.getState().equals(OrderStatePendingForApprove.getInstance()))
										System.out.println(itr.toString());
								}
							}
							if (ro.size() > 0 && lo.size() > 0 && co.size() > 0) {
								System.out.println("No Order Found");
								System.out.println("Return To Main Meun");
								System.out.println("");
							} else {
								order: while (true) {
									System.out.println("l - List Order");
									if (lo.size() > 0) {
										System.out.println("o - Comfirm Order | format: o <Order ID>");
									}
									if (ro.size() > 0) {
										System.out.println("f - Finish Order | format: f <Order ID>");
									}
									System.out.println("c - Cancel Order | format: c <Order ID>");
									System.out.println("x - go back to main menu.");
									cmd = s.next().charAt(0);
									int orderID = 0;
									Order orderToAction = null;
									if (cmd != 'l' && cmd != 'x') {
										orderID = s.nextInt();
										orderToAction = Order.findOrderByOrderID(orderID);
										if (orderToAction == null) {
											System.out.println("Order not Found");
											continue order;
										}
									}
									switch (cmd) {
									case 'l':
										if (ro.size() > 0) {
											System.out.println("Rent Order");
											for (Order itr : ro) {
												if (itr.getState().equals(OrderStateOrderConfirmed.getInstance()) || itr
														.getState().equals(OrderStatePendingForApprove.getInstance()))
													System.out.println(itr.toString());
											}
										}

										if (lo.size() > 0) {
											System.out.println("Lend Order");
											for (Order itr : lo) {
												if (itr.getState().equals(OrderStatePendingForApprove.getInstance())|| itr
												.getState().equals(OrderStateOrderConfirmed.getInstance()))
													System.out.println(itr.toString());
											}
										}

										if (co.size() > 0) {
											System.out.println("Cenceled Order");
											for (Order itr : co) {
												if (itr.getState().equals(OrderStatePendingForApprove.getInstance()))
													System.out.println(itr.toString());
											}
										}
										System.out.println("");
										break;
									case 'o':
										if (orderToAction.isOrderConfirmable(username)) {
											Order.comfirmOrder(orderToAction, Account.getAccountByUsername(username));
											System.out.println("Order " + orderToAction.getOrderID() + " is Confirmed.");
										} else {
											System.out.println("Order cannot be Confirm");
										}
										break;
									case 'f':
										if (orderToAction.isOrderFinishable(username)) {
											int score = 0;
											while (!(score >= 1 && score <= 5)) {
												System.out.println("Please give a score for this order(1-5)");
												score = s.nextInt();
												if (!(score >= 1 && score <= 5)) {
													System.out.println("please give a valid socre");
												}
											}

											System.out.println("Please give a comment for this order");
											s.nextLine();
											String comment = s.nextLine();

											Rating rate = new Rating(score, comment);
											Order.finishOrder(orderToAction, Account.getAccountByUsername(username),
													rate);
											System.out.println("Order " + orderToAction.getOrderID() + " is Finished.");
										} else {
											System.out.println("Order cannot be finish");
										}
										break;
									case 'c':
										if (orderToAction.isOrderCancelable(username)) {
											System.out.println("Order " + orderToAction.getOrderID() + " is Canceled.");
										} else {
											System.out.println("Order aleady finished.");
										}
										break;
									case 'x':
										continue menu;
									default:
										System.out.println("wrong cmd.");
									}

								}

							}
							break;
						case 'c':
							System.out.println("Your Car\n===============================\n");
							Account a = Account.getAccountByUsername(username);
							Vector<Car> c = Car.getCarByAccount(a);
							for (int i = 0; i < c.size(); i++) {
								System.out.println("Car index - " + i);
								System.out.println(c.get(i).toString());
							}

							if (c.isEmpty()) {
								System.out.println("404 not found - Why not try to lent a car?\n\n");
							}

							System.out.println("l - Lent a car");
							System.out.println("d - Discard a car | format d <car index>");
							System.out.println("x - go back to main menu.");
							cmd = s.next().charAt(0);
							switch (cmd) {
							case 'l':
								Account buildOwner = Account.getAccountByUsername(username);
								System.out.println("What is your car type?");
								System.out.println("0 - Private Car");
								System.out.println("1 - Light Goods Vechicle");
								System.out.println("2 - Motorcycle");
								System.out.println("3 - Supercar");
								CarType buildCarType;
								while (true) {
									temp = s.nextInt();
									switch (temp) {
									case 0:
										buildCarType = CarTypePrivateCar.getInstance();
										break;
									case 1:
										buildCarType = CarTypeLightGoodsVehicle.getInstance();
										break;
									case 2:
										buildCarType = CarTypeMotorCycle.getInstance();
										break;
									case 3:
										buildCarType = CarTypeSupercar.getInstance();
										break;
									default:
										System.out.println("wrong car type, please try again!");
										continue;
									}
									break;
								}

								System.out.println("What is your car name?");
								s.nextLine();
								String buildCarName = s.nextLine();
								System.out.println("What the price of the car per day you want?");
								double pricePerDay = s.nextDouble();
								new Car(buildOwner, buildCarType, buildCarName, pricePerDay);
								break;
							case 'd':
								int carIndex = s.nextInt();
								Car car = c.get(carIndex);
								Car.removeCar(car);
								System.out.println("Car removed!");
								break;
							case 'x':
								continue menu;
							default:
								System.out.println("wrong cmd.");
							}
							break;
						case 'a':
							Account ac = Account.getAccountByUsername(username);
							System.out.println(ac);
							account: while (true) {
								System.out.println("Account Menu\n=================================\n");
								System.out.println("v - view Account balance");
								System.out.println("d - deposit | d <amount>");
								System.out.println("w - withdrawal | d <amount>");
								System.out.println("x - go back to main menu.");
								cmd = s.next().charAt(0);
								switch (cmd) {
								case 'v':
									System.out.println("Total Amount: " + ((CustomerAccount) ac).getCoin());
									continue account;
								case 'd':
									Double depositAmount = s.nextDouble();
									((CustomerAccount) ac).deposit(depositAmount);
									System.out.println("Total Amount: " + ((CustomerAccount) ac).getCoin());
									continue account;
								case 'w':
									Double withdrawalAmount = s.nextDouble();
									if (((CustomerAccount) ac).getCoin() - withdrawalAmount >= 0) {
										((CustomerAccount) ac).withdrawal(withdrawalAmount);
										System.out.println("Total Amount: " + ((CustomerAccount) ac).getCoin());
									} else {
										System.out.println("Not enough money to withdrawal.(。_。)...");
									}
									continue account;
								case 'x':
									continue menu;
								default:
									System.out.println("wrong cmd.");
								}
								break;
							}
							break;
						case 'l':
							continue logout;
						case 'x':
							System.out.println("Goodbyel ! " + username);
							System.exit(0);
							break;
						default:
							System.out.println("wrong cmd.");
						}
						} catch (InputMismatchException i) {
							System.out.println("wrong cmd.");
							continue menu;
						}
					}
				} else {
					System.out.println("Wrong username or password! please try again.");
				}
			} else if (cmd == 'x') {
				System.out.println("Goodbye! ");
				System.exit(0);
			} else {
				System.out.println("wrong cmd.");
			}
		}

	}	
	



}
