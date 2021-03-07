package prova3bi.Cinema.Domain.Entidades;

public class Filme extends Entidade {

	public Filme(int id) {
		super(id);
		// TODO Auto-generated constructor stub
	}
	
	public Audio audio;
	public String nacionalidade;
	
	@Override
	public boolean isValid() {
		// TODO Auto-generated method stub
		return false;
	}

}
