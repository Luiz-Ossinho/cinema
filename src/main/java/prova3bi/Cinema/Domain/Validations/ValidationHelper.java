package prova3bi.Cinema.Domain.Validations;

import java.time.LocalDateTime;

public class ValidationHelper {
	public static boolean Test(String str) {
		if (isNullOrEmpty(str))
			return false;
		return true;
	}
	
	public static boolean Test(LocalDateTime DH) {
		if (DH == LocalDateTime.MIN || DH == LocalDateTime.MAX || DH == null)
			return false;
		return true;
	}

	public static boolean isNullOrEmpty(String str) {
		if (str == null || str.isEmpty())
			return true;
		return false;
	}
}
