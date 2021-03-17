package prova3bi.Cinema.Domain.Entidades;

public abstract class Entidade {
	private int Id;

	protected Entidade(int id) {
		this.Id = id;
	}

	public abstract boolean isValid();

	// USE ESSE METODO APENAS NA CAMADA DE SERVICOS
	// PARA SETAR O ID DE ENTIDADES ANINHADAS CRIADAS NA HORA
	// A ALTERNATIVA PODERIA DEIXAR MAIS LENTO
	public void setId(int id) {
		Id = id;
	}

	public int getId() {
		return Id;
	}
}
