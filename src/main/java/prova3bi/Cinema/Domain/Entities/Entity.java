package prova3bi.Cinema.Domain.Entities;

import prova3bi.Cinema.Domain.Validations.ErrorList;

public abstract class Entity {
	private int Id;

	protected Entity(int id) {
		this.Id = id;
	}

	public abstract ErrorList isValid();

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
