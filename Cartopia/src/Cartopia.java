import java.io.IOException;
import java.util.Scanner;
import java.sql.Timestamp;
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
			System.out.println("Login - l <username> <password>");
			System.out.println("Exit Cartopia - x");
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
					while(true){
						
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
								cs = CarStateAvaliable.getInstance();
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
								System.out.println("Command index: " + i);
								System.out.println("Car owner: " + result.get(i).getAccount().getUsername());
								System.out.println("Car name: " + result.get(i).getCarName());
								System.out.println("Car type: " + result.get(i).getCarType().getCarType());
								System.out.println("Car price (per day): " + result.get(i).getPricePerDay());
								System.out.println("=================================");
							}

							break;
						case 'o':
							break;
						case 'c':
							break;
						case 'l':
							continue logout;
						case 'x':
							System.out.println("Goodbye! " + username);
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
