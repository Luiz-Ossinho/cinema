package prova3bi.Cinema.Domain.Entidades;

public enum TicketStatus implements IEnumColumn {
	Pendente(1), Quitado(2);

	TicketStatus(int value) {
		this.value = value;
	}

	private int value;

	@Override
	public int valor() {
		return value;
	}

}
