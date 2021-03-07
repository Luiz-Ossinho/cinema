package prova3bi.Cinema.Data.Abstractions;

public class Coluna {
	private String name;
	private String type;
	private DataType inputType;

	public Coluna(String name, String type, DataType inputType) {
		super();
		this.name = name;
		this.type = type;
		this.inputType = inputType;
	}

	public DataType Input() {
		return this.inputType;
	}

	public String Name() {
		return this.name;
	}

	public String Type() {
		return this.type;
	}

	@Override
	public String toString() {
		return this.name;
	}
}
