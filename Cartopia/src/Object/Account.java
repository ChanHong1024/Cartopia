package Object;
import java.util.Vector;

public class Account {
	public static Vector<Account> accountList = new Vector<>();
	public static Account loginAccount;
	private String username;
	private String password;
	private String email;


	public static boolean login(String username, String password) {
		for (Account itr : accountList){
			if (itr.getUsername().equals(username) && itr.getPassword().equals(password))
				return true;
		}
		return false;
	}
	
	public static Account  getAccountByUsername(String username) {
		for (Account itr : accountList){
			if (itr.getUsername().equals(username))
				return itr;
		}	
		return null;
	}

	public Account(String username, String password, String email) {
		this.username = username;
		this.password = password;
		this.email = email;
		accountList.add(this);
	}

	public String getUsername() {
		return this.username;
	}


	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}


	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return this.email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	@Override
	public String toString() {
		return "Account Information: \n" +
			"username: " + getUsername() + "\n" +
			"email: " + getEmail() + "\n";
	}


}