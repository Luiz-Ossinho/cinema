package prova3bi.Cinema.Domain.Entidades;

import java.time.LocalDateTime;
import java.util.Collection;

public class Seccao extends Entidade {

	public Seccao(int id) {
		super(id);
	}
	
	public LocalDateTime DHInicio;
	public LocalDateTime DHTermino;
	public Sala sala;
	public Filme filme;
	public double preco;
	public Collection<Poltrona> poltronas;
		
	@Override
	public boolean isValid() {
		// TODO Auto-generated method stub
		return false;
	}

}
