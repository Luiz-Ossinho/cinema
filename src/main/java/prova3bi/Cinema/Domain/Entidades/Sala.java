package prova3bi.Cinema.Domain.Entidades;

import prova3bi.Cinema.Data.Abstractions.Builder;
import prova3bi.Cinema.Data.Abstractions.Column;
import prova3bi.Cinema.Data.Abstractions.Table;
import prova3bi.Cinema.Data.Abstractions.Builder.Is;

@Table(nome = "Rooms")
public class Sala extends Entidade {

	@Builder(Is.Insert)
	public Sala(TipoSala tipo, int numeroCadeiras, int numeroSala) {
		super(-1);
		this.tipo = tipo;
		this.numPoltronas = numeroCadeiras;
		this.numeroSala = numeroSala;
	}

	@Builder(Is.Read)
	public Sala(int ChairNum, int RoomNum, int RoomsId, TipoSala roomType) {
		super(RoomsId);
		this.tipo = roomType;
		this.numPoltronas = ChairNum;
		this.numeroSala = RoomNum;
	}

	@Builder(Is.Temp)
	public Sala(int RoomsId) {
		super(RoomsId);
	}

	@Column(nome = "roomType", tipoSql = "INTEGER")
	public TipoSala tipo;

	@Column(nome = "ChairNum", tipoSql = "INTEGER")
	public int numPoltronas;

	@Column(nome = "RoomNum", tipoSql = "INTEGER")
	public int numeroSala;

	@Override
	public boolean isValid() {
		// TODO Auto-generated method stub
		return false;
	}

}
