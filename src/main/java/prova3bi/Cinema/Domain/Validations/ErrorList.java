package prova3bi.Cinema.Domain.Validations;

import java.util.ArrayList;
import java.util.List;

public class ErrorList {
	private List<Error> errors = new ArrayList<Error>();
	
	public String GetErrorLabel(String fieldName) {
		var error = errors.stream().filter(e -> e.fieldName.equals(fieldName)).findFirst();
		if(error.isEmpty())
			return "";
		return error.get().message;
	}
	
	public ErrorList add(Error error) {
		errors.add(error);
		return this;
	}
	
	public ErrorList addAll(ErrorList errorList) {
		errors.addAll(errorList.errors);
		return this;
	}
	
	public int size() {
		return errors.size();
	}
	
	public boolean isEmpty() {
		return errors.isEmpty();
	}
}
