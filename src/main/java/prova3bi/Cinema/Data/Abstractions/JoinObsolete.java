package prova3bi.Cinema.Data.Abstractions;

public class JoinObsolete {

	public String condition;
	public String obj;
	public String Joiner;
	public String Joined;


	public JoinObsolete(String joined, String obj, String joiner, String FKName) {
		this.obj = obj;
		this.Joiner = joiner;
		this.Joined = joined;
		//this.condition = QueryHelper.Equals((Joiner + "." + FKName), (obj + "." + joined));
	}
	
	public JoinObsolete(Composite composite, String joiner) {
		this.obj = composite.obj;
		this.Joiner = joiner;
		this.Joined = composite.Joined;
		//this.condition = QueryHelper.Equals((Joiner + "." + composite.FKName), (obj + "." + composite.Joined));
	}

}
