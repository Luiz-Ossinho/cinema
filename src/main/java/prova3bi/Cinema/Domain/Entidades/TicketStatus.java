package prova3bi.Cinema.Domain.Entidades;

public enum TicketStatus {
	Pendente(false), Pago(true);

	TicketStatus(boolean value) {
		this.value = value;
	}

	private boolean value;

	public boolean getValue() {
		return value;
	}

}
