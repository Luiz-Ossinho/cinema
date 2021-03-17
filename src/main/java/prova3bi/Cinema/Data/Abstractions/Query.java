package prova3bi.Cinema.Data.Abstractions;

import java.time.LocalDateTime;
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

	// condicao booleana, de preferencia no formato (condicao)
	public List<String> conditions = new ArrayList<String>();

	// Values para inserir no INSERT, NAO USADO EM OUTROS CASOS
	public List<Pair<String, Class<?>>> values = new ArrayList<Pair<String, Class<?>>>();

	private String modifier;

	public Query(QueryComand comand, Class<T> type) {
		this.comand = comand;
		this.type = type;
		target = type.getAnnotation(Table.class).nome();
	}

	public Query(QueryComand comand, Modifiers modifier, Class<T> type) {
		this.comand = comand;
		this.properties = new ArrayList<String>();
		this.type = type;
		this.modifier = modifier.value();
		target = type.getAnnotation(Table.class).nome();
	}

	public Query<T> addCondition(String condition) {
		this.conditions.add(condition);
		return this;
	}

	public Query<T> value(Object value, String fieldName) {
		var pair = QueryHelper.Value(value.toString(), fieldName, type);
		this.values.add((Pair<String, Class<?>>) pair);
		String columnName = "";
		try {
			columnName = type.getField(fieldName).getAnnotation(Column.class).nome();
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		this.properties.add(columnName);
		return this;
	}

	public Query<T> value(String value, String fieldName) {
		var pair = QueryHelper.Value(value, fieldName, type);
		this.values.add((Pair<String, Class<?>>) pair);
		String columnName = "";
		try {
			columnName = type.getField(fieldName).getAnnotation(Column.class).nome();
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		this.properties.add(columnName);
		return this;
	}

	public Query<T> value(IEnumColumn enumColumn, String fieldName) {
		var pair = QueryHelper.Value(enumColumn.valor() + "", fieldName, type);
		this.values.add((Pair<String, Class<?>>) pair);
		String columnName = "";
		try {
			columnName = type.getField(fieldName).getAnnotation(Column.class).nome();
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		this.properties.add(columnName);
		return this;
	}

	public Query<T> like(String value, String field) {
		this.conditions.add(QueryHelper.like(value, field, type));
		return this;
	}

	public Query<T> equal(String value, String field) {
		this.conditions.add(QueryHelper.equal(value, field, type));
		return this;
	}

	public Query<T> PKEquals(String value) {
		this.conditions.add(QueryHelper.PKEquals(value, type));
		return this;
	}

	public Query<T> addPKCondition(String condition) {
		this.addCondition(this.target + "." + this.target + "ID " + condition);
		return this;
	}

	private String BuildInsert() {
		var query = this.comand.getCommand() + " ";
		query += this.getAnySuffix() + " ";
		query += this.target + "(";
		query += this.getAnyProperties() + ")\n";
		query += this.getAnyValues() + ")\n";
		return query;
	}

	private String BuildSelect() {
		var query = this.comand.getCommand() + " ";
		query += this.getAnyProperties() + " ";
		query += this.getAnySuffix() + " ";
		query += this.target + " ";
		// Joins nao implementados ainda
		// query += this.getAnyJoins();
		query += this.getAnyConditions();
		query += this.getAnyModifier();
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
		var strComandSuffix = "";
		if (this.comand.hasSuffix())
			strComandSuffix += this.comand.getSuffix();
		return strComandSuffix;
	}

	private String getAnyProperties() {
		var strProperties = "";
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
		var strValues = "";
		if (this.values.isEmpty())
			return strValues;
		else {
			strValues += "Values(";
			for (Pair<String, Class<?>> pair : this.values) {
				var valueTypeName = pair.getValue().getTypeName();
				if (valueTypeName.equals(String.class.getTypeName()))
					strValues += "\"" + pair.getKey() + "\"" + ", ";
				else if (valueTypeName.equals(LocalDateTime.class.getTypeName()))
					strValues += "\"" + pair.getKey().replace('T', ' ') + "\"" + ", ";
				else
					strValues += pair.getKey() + ", ";
			}
			strValues = strValues.substring(0, strValues.length() - 2);
			return strValues;
		}
	}

	private String getAnyConditions() {
		var strConditions = "";
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

	private String getAnyModifier() {
		var strModifier = "";
		if (this.modifier.isEmpty())
			return this.modifier;
		else {
			strModifier += "\n" + this.modifier;
			return strModifier;
		}
	}
}
