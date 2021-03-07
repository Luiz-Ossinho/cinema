package prova3bi.Cinema.Data.Helpers;

import javafx.util.Pair;
import prova3bi.Cinema.Data.Abstractions.Column;
import prova3bi.Cinema.Data.Abstractions.Table;

public class QueryHelper {
	public static String Compose(String composite,String expression) {
		return composite+"."+expression;
	}
	
	public static String Equals(String value) {
		return "= " + value;
	}
	
	public static String Equals(String left, String right) {
		return left+" = " + right;
	}

	public static String Equals(int value) {
		return "= " + value;
	}

	public static String Contains(String value) {
		return " like \'%" + value + "%\'";
	}
	
	public static <T> String Like(String value,String fieldName,Class<T> type) {
		String columnName = "";
		String tableName = type.getAnnotation(Table.class).nome();
		try {
			columnName = type.getField(fieldName).getAnnotation(Column.class).nome();
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		return tableName+"."+columnName + " like \'" + value + "\'";
	}
	
	public static <T> Pair<String, ?> Value(String value,String fieldName,Class<T> type) {
		Class<?> columnType = null;
		try {
			columnType = type.getField(fieldName).getAnnotation(Column.class).tipo();
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		return new Pair<String, Object>(value,columnType);
	}
}
