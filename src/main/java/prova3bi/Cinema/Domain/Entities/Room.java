package prova3bi.Cinema.Domain.Entities;

import prova3bi.Cinema.Data.Abstractions.Builder;
import prova3bi.Cinema.Data.Abstractions.Column;
import prova3bi.Cinema.Data.Abstractions.Table;
import prova3bi.Cinema.Data.Abstractions.Builder.Is;
import prova3bi.Cinema.Domain.Validations.Error;
import prova3bi.Cinema.Domain.Validations.ErrorList;
import prova3bi.Cinema.Domain.Validations.Validator;

@Table(nome = "Rooms")
public class Room extends Entity {

	@Builder(Is.Insert)
	public Room(RoomType type, int chairsNum, int roomNum) {
		super(-1);
		this.tipo = type;
		this.numPoltronas = chairsNum;
		this.numeroSala = roomNum;
	}

	@Builder(Is.Read)
	public Room(int chairNum, int roomNum, int roomsId, RoomType roomType) {
		super(roomsId);
		this.tipo = roomType;
		this.numPoltronas = chairNum;
		this.numeroSala = roomNum;
	}

	@Builder(Is.Temp)
	public Room(int roomsId) {
		super(roomsId);
	}

	@Column(nome = "roomType", tipoSql = "INTEGER")
	public RoomType tipo;

	@Column(nome = "ChairNum", tipoSql = "INTEGER")
	public int numPoltronas;

	@Column(nome = "RoomNum", tipoSql = "INTEGER")
	public int numeroSala;

	private static Validator<Room> validator = new Validator<Room>()
			.add(
					room -> room.tipo != null, 
					room -> new Error("Type", "Chosen type: "+room.tipo+" is invalid"))
			.add(
					room -> room.numPoltronas > 0,
					room -> new Error("SeatsNumber", "!")
			);
	
	// new Error("SeatsNumber", "Number of seats: "+sala.numPoltronas+" should be greater than 0"
	
	@Override
	public ErrorList isValid() {
		return validator.TestAll(this);
	}
}
