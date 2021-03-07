package prova3bi.Cinema.Domain;

import java.time.LocalDateTime;

public class Validator {
	public static boolean isValid(String str) {
		if (isNullOrEmpty(str))
			return false;
		return true;
	}
	
	public static boolean isValid(LocalDateTime DH) {
		if (DH == LocalDateTime.MIN || DH == LocalDateTime.MAX || DH == null)
			return false;
		return true;
	}

	public static boolean isLatitude(double value) {
		if (value < -90d || value > 90d)
			return false;
		return true;
	}
	
	public static boolean isLongitude(double value) {
		if (value < -180d || value > 180d)
			return false;
		return true;
	}
	
//	public static boolean isValid(String str) {
//		if (isNullOrEmpty(str))
//			return false;
//		return true;
//	}
	
	public static boolean isNullOrEmpty(String str) {
		if (str == null || str.isEmpty())
			return true;
		return false;
	}
}
