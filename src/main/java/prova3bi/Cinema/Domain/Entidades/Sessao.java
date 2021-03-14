package prova3bi.Cinema.Domain.Entidades;

import java.time.LocalDateTime;
import java.util.Collection;

import prova3bi.Cinema.Data.Abstractions.Builder;
import prova3bi.Cinema.Data.Abstractions.Column;

public class Sessao extends Entidade {

	@Column(nome = "DHStart", tipoSql = "NVARCHAR(32)")
	public LocalDateTime DHInicio;
	@Column(nome = "DHEnd", tipoSql = "NVARCHAR(32)")
	public LocalDateTime DHTermino;
	@Column(nome = "price", tipoSql = "DECIMAL(8,4)")
	public double preco;
	@Column(nome = "", tipoSql = "NVARCHAR(32)", isFK = true)
	public Sala sala;
	@Column(nome = "", tipoSql = "NVARCHAR(32)", isFK = true)
	public Filme filme;

	public Collection<Poltrona> poltronas;

	@Builder(Builder.Is.Insert)
	public Sessao(LocalDateTime dHInicio, LocalDateTime dHTermino, Sala sala, Filme filme, double preco) {
		super(-1);
		this.DHInicio = dHInicio;
		this.DHTermino = dHTermino;
		this.sala = sala;
		this.filme = filme;
		this.preco = preco;
	}

	@Builder(Builder.Is.Read)
	public Sessao(int Id, LocalDateTime dHInicio, LocalDateTime dHTermino, Sala sala, Filme filme, double preco) {
		super(Id);
		this.DHInicio = dHInicio;
		this.DHTermino = dHTermino;
		this.sala = sala;
		this.filme = filme;
		this.preco = preco;
	}
	
	@Override
	public boolean isValid() {
		return false;
	}

}
