package prova3bi.Cinema.Domain.Entities;

import prova3bi.Cinema.Data.Abstractions.IEnumColumn;

public enum PermissionLevel implements IEnumColumn {
	Admin(1), Atendente(2);

	PermissionLevel(int value) {
		this.value = value;
	}

	private int value;

	public int valor() {
		return this.value;
	}
}
