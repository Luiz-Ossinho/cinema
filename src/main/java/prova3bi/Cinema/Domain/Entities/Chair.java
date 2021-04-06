package prova3bi.Cinema.Domain.Entities;

import prova3bi.Cinema.Data.Abstractions.Builder;
import prova3bi.Cinema.Data.Abstractions.Column;
import prova3bi.Cinema.Data.Abstractions.IEnumColumn;
import prova3bi.Cinema.Data.Abstractions.Table;
import prova3bi.Cinema.Data.Abstractions.Builder.Is;
import prova3bi.Cinema.Domain.Validations.ErrorList;

@Table(nome = "Poltronas", fks = { "session;Sessions" })
public class Chair extends Entity {

	public enum State implements IEnumColumn {
		Occupied(0), Available(1), Pending(3);

		State(int value) {
			this.value = value;
		}

		private int value;

		@Override
		public int valor() {
			return value;
		}

	}

	@Builder(Is.Insert)
	public Chair(Session sessao, String column, int row) {
		super(-1);
		this.sessao = sessao;
		this.column = column;
		this.row = row;
		this.state = State.Available;
	}

	// [1, A, 1, 1, Available]
	@Builder(Is.Read)
	public Chair(int PoltronasID, String column, int row, int sessao, State state) {
		super(PoltronasID);
		this.sessao = new Session(sessao);
		this.column = column;
		this.row = row;
		this.state = state;
	}

	@Builder(Is.Temp)
	public Chair(int PoltronasID) {
		super(PoltronasID);
	}

	@Column(nome = "session", tipoSql = "INTEGER", isFk = true)
	public Session sessao;
	@Column(nome = "state", tipoSql = "INTEGER")
	public State state;
	@Column(nome = "Pos_Column", tipoSql = "TEXT")
	public String column;
	@Column(nome = "Pos_row", tipoSql = "INTEGER")
	public int row;

	public void OccupyChair() {
		this.state = State.Occupied;
	}

	@Override
	public ErrorList isValid() {
		// TODO Auto-generated method stub
		return null;
	}

}
