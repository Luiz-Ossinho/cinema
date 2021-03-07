package prova3bi.Cinema.Data.Abstractions;

public enum QueryComand {
	Select("SELECT", "FROM"), Insert("INSERT","INTO");

	private String[] all;
	private boolean hasSuffix = false;

	QueryComand(String comando, String sufixoComando) {
		all = new String[] { comando, sufixoComando };
		if (sufixoComando == null || sufixoComando.trim().isEmpty())
			hasSuffix = false;
		else
			hasSuffix = true;
	}

	public String[] getAll() {
		return all;
	}

	public String getCommand() {
		return all[0];
	}

	public String getSuffix() {
		return all[1];
	}

	public boolean hasSuffix() {
		return hasSuffix;
	}
}