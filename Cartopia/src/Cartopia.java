import java.io.IOException;
import java.util.Date;
import java.util.Scanner;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;
import Object.*;

public class Cartopia {
	public static void main(String[] args) throws IOException{
		Scanner s = new Scanner(System.in);
		String username;
		String password;
		char cmd;
		System.out.print("╔═══════════════════════════════════════════════════════════════════════════════════════════════╗\n"); 
		System.out.print("║                                                                                               ║\n");
		System.out.print("║	    _______      ____   ,---------.    ,-----.    .-------. .-./`)    ____		║\n");
		System.out.print("║	   /   __  \\   .'  __ `.\\          \\ .'  .-,  '.  \\  _(`)_ \\\\ .-.') .'  __ `.		║\n");
		System.out.print("║	  | ,_/  \\__) /   '  \\  \\`--.  ,---'/ ,-.|  \\ _ \\ | (_ o._)|/ `-' \\/   '  \\  \\		║\n");
		System.out.print("║	,-./  )       |___|  /  |   |   \\  ;  \\  '_ /  | :|  (_,_) / `-'`\"`|___|  /  |		║\n");
		System.out.print("║	\\  '_ '`)        _.-`   |   :_ _:  |  _`,/ \\ _/  ||   '-.-'  .---.    _.-`   |		║\n");
		System.out.print("║	 > (_)  )  __ .'   _    |   (_I_)  : (  '\\_/ \\   ;|   |      |   | .'   _    |		║\n");
		System.out.print("║	(  .  .-'_/  )|  _( )_  |  (_(=)_)  \\ `\"/  \\  ) / |   |      |   | |  _( )_  |		║\n");
		System.out.print("║	 `-'`-'     / \\ (_ o _) /   (_I_)    '. \\_/``\".'  /   )      |   | \\ (_ o _) /		║\n");
		System.out.print("║	   `._____.'   '.(_,_).'    '---'      '-----'    `---'      '---'  '.(_,_).'		║\n");
		System.out.print("║                                ©  Copyright  Group 11 Cartopia 2021                           ║\n");
		System.out.print("╚═══════════════════════════════════════════════════════════════════════════════════════════════╝\n");
		
		Account a1 = new CustomerAccount(null, "whchan1024", "abc", "whchan1024@gmail.com");
		Car c1 = new Car(a1, CarTypeSupercar.getInstance(), "McLaren 720S", 1000);
		Car c2 = new Car(a1, CarTypePrivateCar.getInstance(), "Model S", 500);
		
		
		logout : while(true){
			System.out.println("l - Login | format: l <username> <password>");
			System.out.println("x - Exit Cartopia");
			cmd = (char)s.next().charAt(0);
			if(cmd == 'l') {
				
				if(!s.hasNext()) {
					System.out.println("Wrong number of parameters! please try again.");
				}
				
				username = s.next();
				password = s.next();
				
				System.out.println(username + " " + password);
				
				if(Account.login(username, password)) {
					Long datetime = System.currentTimeMillis();
					Timestamp timestamp = new Timestamp(datetime);
					System.out.println("Welcome, " + username + "! Time now is : " + timestamp + ".");
					menu: while(true){
						
						System.out.println("s - Serach Car");
						System.out.println("o - Order Management");
						System.out.println("c - Car Management");
						System.out.println("l - Logout");
						System.out.println("x - Exit Cartopia");
						System.out.println("Please enter you cmd:");
						
						
						cmd = (char)s.next().charAt(0);
						//System.out.println("Your cmd is " + cmd);
						
						switch(cmd) {
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

							switch(temp){
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
							System.out.println(ct.getCarType());

							System.out.println("Do you know the name of cars? (No = n, Yes = y)");
							String carName;
							cmd = s.next().charAt(0);
							if(cmd == 'y'){
								carName = s.next();
							}else{
								carName = null;
							}

							System.out.println("Do you want to search cars including unavailable cars? (No = n, Yes = y)");

							cmd = s.next().charAt(0);
							if(cmd == 'y'){
								cs = null;
							}else{
								cs = CarStateAvailable.getInstance();
							}

							System.out.println("Do you want to search cars within a specific price range? (No = n, Yes = y <lowerbound> <upperbound> eg. y 0 1000)");
							cmd = s.next().charAt(0);
							if(cmd == 'y'){
							lowerBound = s.nextDouble();
							upperBound = s.nextDouble();
							}else{
								lowerBound = 0;
								upperBound = Double.POSITIVE_INFINITY;
							}


							Vector<Car> result = Car.SearchCar(null, carName, ct, cs, upperBound , lowerBound);

							for(int i = 0; i < result.size();i++){
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
							switch(cmd) {
							case 'o':
								System.out.println("please enter car search index of the car you want to rent.");
								int carSerachIndex = s.nextInt();
								
								Account lender = result.get(carSerachIndex).getAccount();
								Account renter = Account.getAccountByUsername(username);
								Date startDate = null;
						        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
								System.out.println("please enter start date you want to rent. (dd/MM/yyyy)");
						        String cinput = s.next();
						        if(null != cinput && cinput.trim().length() > 0){
						        	try {
										startDate = (Date) format.parse(cinput);
									} catch (ParseException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
						        }
								
								System.out.println("please enter days you want to rent.");
								int days = s.nextInt();								
								
								if(Order.isOrderDateVaild(result.get(carSerachIndex), startDate, days)) {
									new Order(renter, lender, days , startDate,  result.get(carSerachIndex));
									System.out.println("Pending the lender to accept the order.");
								} else {
									System.out.println("This car was reserved for another customer during this order date, try again with other order date.");
								}
								break;
							case 'x':
								break;
							default:
								System.out.println("wrong cmd.");
							}							

							break;
						case 'o':
							Vector<Order> o = Order.getOrderByRenter(username);
							for(Order itr: o){
								System.out.println(itr.toString());
							}
							System.out.println("o - Comfirm Order");
							System.out.println("f - Finish Order");
							System.out.println("c - Cancel Order");
							//TO-DO: implements o, f c
							break;
						case 'c':
							System.out.println("Your Car\n===============================\n");
							Account a = Account.getAccountByUsername(username);
							Vector<Car> c = Car.getCarByAccount(a);
							for(int i = 0; i < c.size(); i++){
								System.out.println("Car index - " + i);
								System.out.println(c.get(i).toString());
							}
							System.out.println("d - Discard car | format d <car index>");	
							cmd = s.next().charAt(0);
							switch(cmd){
								case 'd' : 
								int carIndex = s.nextInt();
								Car car = c.get(carIndex);
								Car.removeCar(car);
								System.out.println("Car removed!");
								break;
								default : System.out.println("wrong cmd.");
							}
							for(int i = 0; i < c.size(); i++){
								
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
					}
				}else {
						System.out.println("Wrong username or password! please try again.");
				}
			}else if(cmd == 'x') {
				System.out.println("Goodbye! ");
				System.exit(0);
			}else {
				System.out.println("wrong cmd.");
			}
		}


		
		
	}
	
	
}
