package prova3bi.Cinema.Domain.Entities;

import prova3bi.Cinema.Data.Abstractions.IEnumColumn;

public enum RoomType implements IEnumColumn {
	DBox(1), XD(2), Prime(3), Básica(4), d3(5);

	RoomType(int value) {
		this.value = value;
	}

	private int value;

	@Override
	public int valor() {
		return this.value;
	}
}
