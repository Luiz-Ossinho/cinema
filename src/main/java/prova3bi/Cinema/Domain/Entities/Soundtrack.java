package prova3bi.Cinema.Domain.Entities;

import prova3bi.Cinema.Data.Abstractions.IEnumColumn;

public enum Soundtrack implements IEnumColumn {
	Dublado(1, "Audio dublado"), Legendado(2, "Audio original com legenda"), Orignal(3, "Audio original");

	Soundtrack(int value, String description) {
		this.description = description;
		this.value = value;
	}

	private String description;
	private int value;

	public String description() {
		return description;
	}

	@Override
	public int valor() {
		return value;
	}
}
