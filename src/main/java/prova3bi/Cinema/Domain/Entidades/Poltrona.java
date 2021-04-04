package prova3bi.Cinema.Domain.Entidades;

import prova3bi.Cinema.Data.Abstractions.Builder;
import prova3bi.Cinema.Data.Abstractions.Column;
import prova3bi.Cinema.Data.Abstractions.Table;
import prova3bi.Cinema.Data.Abstractions.Builder.Is;
import prova3bi.Cinema.Domain.Validations.ErrorList;

@Table(nome = "Poltronas", fks = { "session;Sessions" })
public class Poltrona extends Entidade {

	@Builder(Is.Insert)
	public Poltrona(Sessao sessao, String column, int row) {
		super(-1);
		this.sessao = sessao;
		this.column = column;
		this.row = row;
		this.ocupada = false;
	}

	// PoltronasID column isOccupied row session
	@Builder(Is.Read)
	public Poltrona(int PoltronasID, String column, int isOccupied, int row, int sessao) {
		super(PoltronasID);
		this.sessao = new Sessao(sessao);
		this.column = column;
		this.row = row;
		this.ocupada = isOccupied == 0 ? false : true;
	}

	@Builder(Is.Temp)
	public Poltrona(int PoltronasID) {
		super(PoltronasID);
	}

	@Column(nome = "session", tipoSql = "INTEGER", isFk = true)
	public Sessao sessao;
	@Column(nome = "isOccupied", tipoSql = "BOOLEAN")
	public boolean ocupada;
	@Column(nome = "column", tipoSql = "TEXT")
	public String column;
	@Column(nome = "row", tipoSql = "INTEGER")
	public int row;

	public void OccupyChair() {
		this.ocupada = true;
	}
	
	@Override
	public ErrorList isValid() {
		// TODO Auto-generated method stub
		return null;
	}

}
