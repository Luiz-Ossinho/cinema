package prova3bi.Cinema.Data.Abstractions;

public class Composite {
	public String obj;
	public String Joined;
	public String FKName;

	public Composite(String joined, String obj, String FKName) {
		this.obj = obj;
		this.Joined = joined;
		this.FKName = FKName;
	}
}
