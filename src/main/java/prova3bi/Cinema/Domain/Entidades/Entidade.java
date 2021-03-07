package prova3bi.Cinema.Domain.Entidades;

import java.sql.ResultSet;
import java.sql.SQLException;

import prova3bi.Cinema.Data.Abstractions.Column;

public abstract class Entidade {
	@Column(nome = "Id", tipo = Integer.class, tipoSql = "INTEGER")
	private int Id;
	
	public abstract boolean isValid();
	
	public int getId() {
		return Id;
	}
	
	protected Entidade(int id) {
		this.Id = id;
	}
	protected Entidade(ResultSet results) {
		try {
			this.Id = results.getInt("Id");
		} catch (SQLException e) {}
	}

	public void setId(int id) {
		Id = id;
	}
}
