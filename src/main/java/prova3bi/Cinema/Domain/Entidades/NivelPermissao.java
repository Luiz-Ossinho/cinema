package prova3bi.Cinema.Domain.Entidades;

public enum NivelPermissao implements IEnumColumn {
	Admin(1), Atendente(2);

	NivelPermissao(int valor) {
		this.valor = valor;
	}

	private int valor;

	public int valor() {
		return this.valor;
	}
}
