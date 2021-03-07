package prova3bi.Cinema.Domain.Entidades;

public enum TipoSala {
	DBox(1), XD(2), Prime(3);

	TipoSala(int value) {
		this.value = value;
	}

	private int value;

	public int getValue() {
		return value;
	}

}
