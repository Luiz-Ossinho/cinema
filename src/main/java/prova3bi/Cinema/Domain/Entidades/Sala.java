package prova3bi.Cinema.Domain.Entidades;

import prova3bi.Cinema.Data.Abstractions.Builder;
import prova3bi.Cinema.Data.Abstractions.Column;
import prova3bi.Cinema.Data.Abstractions.Table;
import prova3bi.Cinema.Data.Abstractions.Builder.Is;
import prova3bi.Cinema.Domain.Validations.Error;
import prova3bi.Cinema.Domain.Validations.ErrorList;
import prova3bi.Cinema.Domain.Validations.Validator;

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

	private static Validator<Sala> validator = new Validator<Sala>()
			.add(
					sala -> sala.tipo != null, 
					sala -> new Error("Type", "Chosen type: "+sala.tipo+" is invalid"))
			.add(
					sala -> sala.numPoltronas > 0,
					sala -> new Error("SeatsNumber", "!")
			);
	
	// new Error("SeatsNumber", "Number of seats: "+sala.numPoltronas+" should be greater than 0"
	
	@Override
	public ErrorList isValid() {
		return validator.TestAll(this);
	}
}
