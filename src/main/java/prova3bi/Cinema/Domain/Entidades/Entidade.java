package prova3bi.Cinema.Domain.Entidades;

public abstract class Entidade {
	private int Id;

	protected Entidade(int id) {
		this.Id = id;
	}
	
	public abstract boolean isValid();

	public void setId(int id) {
		Id = id;
	}
	
	public int getId() {
		return Id;
	}
}
