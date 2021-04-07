package prova3bi.Cinema.Domain.Entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import prova3bi.Cinema.Data.Abstractions.Builder;
import prova3bi.Cinema.Data.Abstractions.Column;
import prova3bi.Cinema.Data.Abstractions.Table;
import prova3bi.Cinema.Domain.Validations.Error;
import prova3bi.Cinema.Domain.Validations.ErrorList;
import prova3bi.Cinema.Domain.Validations.ValidationHelper;
import prova3bi.Cinema.Domain.Validations.Validator;
import prova3bi.Cinema.Util.Utils;

@Table(nome = "Sessions", fks = { "movie;Movies", "room;Rooms" })
public class Session extends Entity {

	@Column(nome = "DHStart", tipoSql = "TEXT")
	public LocalDateTime DHInicio;
	@Column(nome = "DHEnd", tipoSql = "TEXT")
	public LocalDateTime DHTermino;
	@Column(nome = "price", tipoSql = "NUMERIC")
	public double preco;
	@Column(nome = "room", tipoSql = "INTEGER", isFk = true)
	public Room sala;
	@Column(nome = "movie", tipoSql = "INTEGER", isFk = true)
	public Movie filme;

	// public List<Chair> chair = new ArrayList<Chair>();
	public Chair[][] chairs;

	public int numPoltronasVagas() {
		int counter = 0;
		for (int i = 0; i < chairs.length; i++) {
			for (int j = 0; j < chairs[0].length; j++) {
				if (chairs[i][j] != null)
					if (chairs[i][j].state != Chair.State.Occupied)
						counter++;
			}
		}
		return counter;
	}

	public State verEstado() {
		State estado = null;
		if (this.DHInicio.isAfter(LocalDateTime.now()))
			estado = State.VaiComecar;
		else if (this.DHInicio.isBefore(LocalDateTime.now()) && this.DHTermino.isAfter(LocalDateTime.now()))
			estado = State.EmAndamento;
		else if (this.numPoltronasVagas() <= 0)
			estado = State.Lotada;
		else if (this.DHTermino.isBefore(LocalDateTime.now()))
			estado = State.JaTerminou;
		return estado;
	}

	@Builder(Builder.Is.Insert)
	public Session(LocalDateTime dHInicio, LocalDateTime dHTermino, Room sala, Movie filme, double preco) {
		super(-1);
		this.DHInicio = dHInicio;
		this.DHTermino = dHTermino;
		this.sala = sala;
		this.filme = filme;
		this.preco = preco;
	}

	// DHEnd DHStart SessionsId movie price room
	@Builder(Builder.Is.Read)
	public Session(String DHEnd, String DHStart, int SessionsId, int movie, double price, int room) {
		super(SessionsId);
		this.DHInicio = LocalDateTime.parse(DHStart, Utils.dateTimeFormatter);
		this.DHTermino = LocalDateTime.parse(DHEnd, Utils.dateTimeFormatter);
		this.sala = new Room(room);
		this.filme = new Movie(movie);
		this.preco = price;
	}

	@Builder(Builder.Is.Temp)
	public Session(int SessionsId) {
		super(SessionsId);
	}

	public enum State {
		VaiComecar, EmAndamento, Lotada, JaTerminou;
	}

	private static Validator<Session> validator = new Validator<Session>()
			.add(session -> ValidationHelper.Test(session.DHInicio), session -> new Error("itime", "!"))
			.add(session -> ValidationHelper.Test(session.DHTermino), session -> new Error("ftime", "!"))
			.add(session -> session.preco > 0, session -> new Error("price", "!"));

	@Override
	public ErrorList isValid() {
		return validator.TestAll(this).addAll(this.sala.isValid()).addAll(this.filme.isValid());
	}

}
