package prova3bi.Cinema.Domain.Entities;

import prova3bi.Cinema.Data.Abstractions.IEnumColumn;

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
