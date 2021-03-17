package prova3bi.Cinema.Domain.Entidades;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import prova3bi.Cinema.Data.Abstractions.Builder;
import prova3bi.Cinema.Data.Abstractions.Column;
import prova3bi.Cinema.Data.Abstractions.Table;

@Table(nome = "Sessions", fks = { "movie;Movies", "room;Rooms" })
public class Sessao extends Entidade {

	@Column(nome = "DHStart", tipoSql = "TEXT")
	public LocalDateTime DHInicio;
	@Column(nome = "DHEnd", tipoSql = "TEXT")
	public LocalDateTime DHTermino;
	@Column(nome = "price", tipoSql = "NUMERIC")
	public double preco;
	@Column(nome = "room", tipoSql = "INTEGER")
	public Sala sala;
	@Column(nome = "movie", tipoSql = "INTEGER")
	public Filme filme;

	public List<Poltrona> poltronas = new ArrayList<Poltrona>();

	public int numPoltronasVagas() {
		int counter = 0;
		for (var poltrona : poltronas) {
			if (!poltrona.ocupada)
				counter++;
		}
		return counter;
	}

	public EstadoSessao verEstado() {
		return EstadoSessao.obterEstado(this);
	}

	public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSSSSS");

	@Builder(Builder.Is.Insert)
	public Sessao(LocalDateTime dHInicio, LocalDateTime dHTermino, Sala sala, Filme filme, double preco) {
		super(-1);
		this.DHInicio = dHInicio;
		this.DHTermino = dHTermino;
		this.sala = sala;
		this.filme = filme;
		this.preco = preco;
	}

	// DHEnd DHStart SessionsId movie price room
	@Builder(Builder.Is.Read)
	public Sessao(String DHEnd, String DHStart, int SessionsId, int movie, double price, int room) {
		super(SessionsId);
		this.DHInicio = LocalDateTime.parse(DHStart, formatter);
		this.DHTermino = LocalDateTime.parse(DHEnd, formatter);
		this.sala = new Sala(room);
		this.filme = new Filme(movie);
		this.preco = price;
	}

	@Builder(Builder.Is.Temp)
	public Sessao(int SessionsId) {
		super(SessionsId);
	}

	@Override
	public boolean isValid() {
		return false;
	}

}
