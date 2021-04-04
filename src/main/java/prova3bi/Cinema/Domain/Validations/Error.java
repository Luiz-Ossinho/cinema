package prova3bi.Cinema.Domain.Validations;

public class Error {
	public String fieldName;
	public String message;
	public Error(String fieldName, String message) {
		this.fieldName = fieldName;
		this.message = message;
	}
	public Error(String message) {
		this.message = message;
	}
}
