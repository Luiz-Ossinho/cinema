package prova3bi.Cinema.Domain.Entidades;

public class Poltrona extends Entidade{
	public Poltrona(int id) {
		super(id);
		// TODO Auto-generated constructor stub
	}
	public boolean ocupada;
	public Posicao posicao;
	public Seccao seccao;
	
	@Override
	public boolean isValid() {
		// TODO Auto-generated method stub
		return true;
	}

}
