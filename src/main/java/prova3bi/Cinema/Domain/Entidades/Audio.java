package prova3bi.Cinema.Domain.Entidades;

public enum Audio {
	Dublado("Audio dublado"), Legendado("Audio original com legenda"), Orignal("Audio original");

	Audio(String value) {
		this.value = value;
	}

	private String value;

	public String getValue() {
		return value;
	}
}
