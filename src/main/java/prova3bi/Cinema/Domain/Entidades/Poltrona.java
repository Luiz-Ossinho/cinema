package prova3bi.Cinema.Domain.Entidades;

import prova3bi.Cinema.Data.Abstractions.Builder;
import prova3bi.Cinema.Data.Abstractions.Builder.Is;

public class Poltrona extends Entidade{
	
	@Builder(Is.Insert)
	public Poltrona(Sessao sessao, String column, int row) {
		super(-1);
		this.sessao = sessao;
		this.column = column;
		this.row = row;
		this.ocupada = false;
	}

	public boolean ocupada;
	
	// Depois talvez implementar tabela posicoes para dimuir gastos em armazenamento
	//public Posicao posicao;
	
	public Sessao sessao;
	public String column;
	public int row;
	
	@Override
	public boolean isValid() {
		// TODO Auto-generated method stub
		return true;
	}

}
