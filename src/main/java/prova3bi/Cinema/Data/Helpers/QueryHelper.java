package prova3bi.Cinema.Data.Helpers;

import javafx.util.Pair;
import prova3bi.Cinema.Data.Abstractions.Column;
import prova3bi.Cinema.Data.Abstractions.Table;

public class QueryHelper {
	public static String nestingParticle(String mother, String nested) {
		return mother + "." + nested;
	}
	
	private static String containsParticle(String value) {
		return " like \'%" + value + "%\'";
	}
	
	private static String likeParticle(String value) {
		return " like \'" + value + "\'";
	}

	private static String equalsParticle(String left, String right) {
		return left + " = " + right;
	}

	public static <T> String contains(String value, String fieldName, Class<T> type) {
		String columnName = "";
		String tableName = type.getAnnotation(Table.class).nome();
		try {
			columnName = type.getField(fieldName).getAnnotation(Column.class).nome();
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		return nestingParticle(tableName,columnName) + containsParticle(value);
	}

	public static <T> String like(String value, String fieldName, Class<T> type) {
		String columnName = "";
		String tableName = type.getAnnotation(Table.class).nome();
		try {
			columnName = type.getField(fieldName).getAnnotation(Column.class).nome();
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		return nestingParticle(tableName, columnName) + likeParticle(value);
	}

	public static <T> String equal(String value, String fieldName, Class<T> type) {
		String columnName = "";
		String tableName = type.getAnnotation(Table.class).nome();
		try {
			columnName = type.getField(fieldName).getAnnotation(Column.class).nome();
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		return equalsParticle(nestingParticle(tableName, columnName), value);
	}
	
	public static <T> String PKEquals(String value, Class<T> type) {
		String tableName = type.getAnnotation(Table.class).nome();
		return equalsParticle(nestingParticle(tableName, tableName+"ID"), value);
	}

	public static <T> Pair<String, Class<?>> Value(String value, String fieldName, Class<T> type) {
		Class<?> columnType = null;
		try {
			columnType = type.getField(fieldName).getType();
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		return new Pair<String, Class<?>>(value, columnType);
	}
}
