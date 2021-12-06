package Object;

import java.util.*;


public class License {

	public static Vector<License> applicationQueue = new Vector<>();

	private Vector<CarType> carTypeList = new Vector<>();
	private Date expireDate;

	public License(Vector<CarType> carTypeList, Date expireDate) {
		this.carTypeList = carTypeList;
		this.expireDate = expireDate;
	}

	public boolean isValidLicense(Car car) {
		Date today = new Date();
		for (CarType itr: this.carTypeList){
			if (car.getCarType().getCarType().equals(itr.getCarType() && expireDate.after(today))
				return true;
		}
		return false;
	}


}
