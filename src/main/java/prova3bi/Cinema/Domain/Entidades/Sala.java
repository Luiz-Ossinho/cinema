package prova3bi.Cinema.Domain.Entidades;

public class Sala extends Entidade {

	public Sala(int id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	public TipoSala tipo;
	public int numPoltronas;
	
	@Override
	public boolean isValid() {
		// TODO Auto-generated method stub
		return false;
	}

}
