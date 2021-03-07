package prova3bi.Cinema.Data.Abstractions;

import java.util.Map;


// TA AQUI SO DE REFERENCIA PRA EU COPIAR O CODIGO DAS CHAVES ESTRANGEIRAS DPS
public abstract class TableObsolete {
	public String Nome;
	public String PrimaryKey;
	public Map<String, String> Atributos;
	public Map<String, String> ForeignKeys;

	public TableObsolete(String nome, Map<String, String> atributos, Map<String, String> FKs) {
		this.Nome = nome;
		this.PrimaryKey = nome + "ID";
		this.Atributos = atributos;
		this.ForeignKeys = FKs;
	}

	public String ToCreateScript() {
		String sql = "CREATE TABLE IF NOT EXISTS " + this.Nome + " \n";
		sql += "( \n";
		sql += " " + this.PrimaryKey + " INTEGER PRIMARY KEY AUTOINCREMENT" + ", \n";

		int j = 0;
		for (Map.Entry<String, String> atributo : this.Atributos.entrySet()) {
			sql += " " + atributo.getKey() + " " + atributo.getValue();
			if (j != (this.Atributos.size() - 1))
				sql += ", \n";
			j++;
		}
		int i = 0;
		if (this.ForeignKeys.isEmpty())
			sql += "\n";
		else {
			sql += ", \n";
			for (Map.Entry<String, String> chave : this.ForeignKeys.entrySet())
				sql += "\n" + chave.getKey() + " integer, \n";
			for (Map.Entry<String, String> chave : this.ForeignKeys.entrySet()) {
				sql += " FOREIGN KEY (" + chave.getKey() + ") " + " REFERENCES " + chave.getValue() + "( "
						+ chave.getValue() + "ID" + " ) ";
				if (i != (this.ForeignKeys.size() - 1))
					sql += ", \n";

				i++;
			}
		}
		sql += " ); \n";
		return sql;
	}

}
