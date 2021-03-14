package prova3bi.Cinema.Domain.Entidades;

public enum TipoSala implements IEnumColumn {
	DBox(1), XD(2), Prime(3);

	TipoSala(int value) {
		this.value = value;
	}

	private int value;

	@Override
	public int valor() {
		return this.value;
	}
}
