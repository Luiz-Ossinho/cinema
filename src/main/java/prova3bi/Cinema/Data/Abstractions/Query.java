package prova3bi.Cinema.Data.Abstractions;

import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;
import prova3bi.Cinema.Data.Helpers.QueryHelper;
import prova3bi.Cinema.Domain.Entidades.Entidade;

public class Query<T extends Entidade> {
	public enum Modifiers {
		Limit1("LIMIT 1");

		private Modifiers(String value) {
			this.value = value;
		}

		private String value = "";

		public String value() {
			return value;
		}
	}

	public QueryComand comand;

	public List<String> properties = new ArrayList<String>();

	// Class of targe
	public Class<T> type;
	private String target;

	// Algo como LIMIT 5 ou Distinct
	private String modifier = "";

	// condicao booleana, de preferencia no formato (condicao)
	public List<String> conditions = new ArrayList<String>();

	// nome da coluna FK e nome da Tabela alvo
	public List<Join> Joins = new ArrayList<Join>();

	// Values para inserir no INSERT, NAO USADO EM OUTROS CASOS
	public List<Pair<String, Class<?>>> values = new ArrayList<Pair<String, Class<?>>>();

	public Query(QueryComand comand, Class<T> type) {
		this.comand = comand;
		this.type = type;
		this.modifier = "";
		target = type.getAnnotation(Table.class).nome();
	}

	public Query(QueryComand comand, String modifier, Class<T> type) {
		this.comand = comand;
		this.properties = new ArrayList<String>();
		this.type = type;
		this.modifier = modifier;
		target = type.getAnnotation(Table.class).nome();
	}

	public Query(QueryComand comand, Modifiers modifier, Class<T> type) {
		this.comand = comand;
		this.properties = new ArrayList<String>();
		this.type = type;
		this.modifier = modifier.value();
		target = type.getAnnotation(Table.class).nome();
	}

	public Query addCondition(String condition) {
		this.conditions.add(condition);
		return this;
	}

	public Query<T> value(String value, String fieldName) {
		var pair = QueryHelper.Value(value, fieldName, type);
		this.values.add((Pair<String, Class<?>>) pair);
		this.properties.add(fieldName);
		return this;
	}

	public Query<T> like(String value, String field) {
		this.conditions.add(QueryHelper.Like(value, field, type));
		return this;
	}

	public Query addNestedCondition(String compose, String condition) {
		this.conditions.add(QueryHelper.Compose(compose, condition));
		return this;
	}

	public Query addNestedCondition(Composite composite, String condition) {
		this.conditions.add(QueryHelper.Compose(composite.obj, condition));
		return this;
	}

	// region Properties Functions
	public Query addProperties(String[] properties) {
		for (String property : properties) {
			this.properties.add(property);
		}
		return this;
	}

	public Query addProperty(String property) {
		this.properties.add(property);
		return this;
	}
	// endregion

	public Query addPKCondition(String condition) {
		this.addCondition(this.target + "." + this.target + "ID " + condition);
		return this;
	}

	// region Join Functions
	public Query InnerJoin(String joined, String obj, String FKName) {
		this.Joins.add(new Join(joined, obj, this.target, FKName));
		return this;
	}

	public Query InnerJoin(Composite composite) {
		this.Joins.add(new Join(composite, this.target));
		return this;
	}

	public Query addJoin(Join join) {
		this.Joins.add(join);
		return this;
	}

	private String BuildInsert() {
		String query = this.comand.getCommand() + " ";
		query += this.getAnySuffix() + " ";
		query += this.target + "(";
		query += this.getAnyProperties() + ")\n";
		query += this.getAnyValues() + ")\n";
		return query;
	}

	private String BuildSelect() {
		String query = this.comand.getCommand() + " ";
		query += this.getAnyProperties() + " ";
		query += this.getAnySuffix() + " ";
		query += this.target + " ";
		query += this.getAnyJoins();
		query += this.getAnyConditions();
		//query += this.modifier + " ";
		return query;
	}

	@Override
	public String toString() {
		String query = null;
		switch (this.comand) {
		case Select:
			query = BuildSelect();
			break;
		case Insert:
			query = BuildInsert();
			break;

		default:
			break;
		}
		return query;
	}

	private String getAnySuffix() {
		String strComandSuffix = "";
		if (this.comand.hasSuffix())
			strComandSuffix += this.comand.getSuffix();
		return strComandSuffix;
	}

	private String getAnyProperties() {
		String strProperties = "";
		switch (this.comand) {
		case Insert:
			for (String property : this.properties) {
				strProperties += property + ", ";
			}
			strProperties = strProperties.substring(0, strProperties.length() - 2);
			break;
		case Select:
			if (this.properties.isEmpty())
				return "*" + " ";
			else {
				for (String property : this.properties) {
					strProperties += this.target + "." + property + ", ";
				}
				strProperties = strProperties.substring(0, strProperties.length() - 2);
			}
			break;
		}
		return strProperties;
	}

	private String getAnyValues() {
		String strValues = "";
		if (this.values.isEmpty())
			return strValues;
		else {
			strValues += "Values(";
			for (Pair<String, Class<?>> pair : this.values) {
				if (pair.getValue().getTypeName().equals(String.class.getTypeName()))
					strValues += "\"" + pair.getKey() + "\"" + ", ";
				else
					strValues += pair.getKey() + ", ";
			}
			strValues = strValues.substring(0, strValues.length() - 2);
			return strValues;
		}
	}

	private String getAnyConditions() {
		String strConditions = "";
		if (this.conditions.isEmpty())
			return strConditions;
		else {
			strConditions += "\nWhere \n";
			for (String condition : this.conditions) {
				strConditions += condition + " and ";
			}
			strConditions = strConditions.substring(0, strConditions.length() - 4);
			return strConditions;
		}
	}

	// JOIN Locais a1 ON Voos.LCPartidaID = a1.LocaisID
	private String getAnyJoins() {
		String strJoins = "";
		if (this.Joins.isEmpty())
			return strJoins;
		else {
			strJoins += "\n";
			for (Join join : this.Joins) {
				strJoins += "Join " + join.Joined + " " + join.obj + " ON " + join.condition + "\n";
			}
			return strJoins;
		}
	}
}
