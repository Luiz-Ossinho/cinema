package prova3bi.Cinema.Data.Helpers;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javafx.util.Pair;
import prova3bi.Cinema.Data.Abstractions.Builder;
import prova3bi.Cinema.Data.Abstractions.Column;
import prova3bi.Cinema.Data.Abstractions.IEnumColumn;
import prova3bi.Cinema.Data.Abstractions.Table;
import prova3bi.Cinema.Domain.Entities.Entity;

public class Converter {

	private static Comparator<Pair<Field, Column>> alphabeticComparator = new Comparator<Pair<Field, Column>>() {
		@Override
		public int compare(Pair<Field, Column> o1, Pair<Field, Column> o2) {
			String a = o1.getValue().nome();
			String b = o2.getValue().nome();
			int i = a.compareTo(b);
			return i;
		}
	};

	public static <T extends Entity> List<T> convert(ResultSet from, Class<T> to) throws SQLException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		var intances = new ArrayList<T>();
		var columnPairs = getColumns(to);
		var readContrucotr = getReadConstructor(to);
		while (from.next()) {
			var parametrosConstrutor = new ArrayList<Object>();
			for (var pair : columnPairs) {
				var column = pair.getValue();
				var fieldType = pair.getKey().getType();
				if (IEnumColumn.class.isAssignableFrom(fieldType)) {
					parametrosConstrutor.add(getEnumObject(from, fieldType.getEnumConstants(), column.nome()));
				} else {
					parametrosConstrutor.add(getObject(from, column.nome()));
				}
			}
			T instance = ((T) readContrucotr.newInstance(parametrosConstrutor.toArray()));
			intances.add(instance);
		}

		return intances;
	}

	private static <T extends Entity> List<Pair<Field, Column>> getColumns(Class<T> toClass) {
		var columnPairs = getDeclaredColumns(toClass);
		addIdColumn(columnPairs, toClass);
		columnPairs.sort(alphabeticComparator);

		return columnPairs;
	}

	private static <T> void addIdColumn(List<Pair<Field, Column>> columnPairs, Class<T> to) {
		try {
			var idColumn = idColumn(to.getAnnotation(Table.class).nome() + "ID");
			var mockField = Converter.class.getField("mockField");
			var pair = new Pair<Field, Column>(mockField, idColumn);
			columnPairs.add(pair);
		} catch (NoSuchFieldException | SecurityException e) {
		}
	}

	private static Object getObject(ResultSet results, String columnName) {
		Object obj = null;
		try {
			obj = results.getObject(columnName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}

	private static Object getEnumObject(ResultSet results, Object[] enumConstants, String columnName) {
		int obj = 0;
		Object enumConstant = null;
		try {
			obj = results.getInt(columnName);
			for (int i = 0; i < enumConstants.length; i++) {
				var enumColumn = (IEnumColumn) enumConstants[i];
				if (enumColumn.valor() == obj) {
					enumConstant = enumConstants[i];
					break;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return enumConstant;
	}

	private static <T extends Entity> Constructor<?> getReadConstructor(Class<T> toClass) {
		Constructor<?> readCtor = null;
		for (var ctor : toClass.getConstructors()) {
			if (ctor.getAnnotation(Builder.class).value() == Builder.Is.Read) {
				readCtor = ctor;
				break;
			}
		}
		return readCtor;
	}

	private static <T extends Entity> List<Pair<Field, Column>> getDeclaredColumns(Class<T> toClass) {
		var columns = new ArrayList<Pair<Field, Column>>();
		for (Field field : toClass.getDeclaredFields()) {
			if (field.isAnnotationPresent(Column.class)) {
				columns.add(new Pair<Field, Column>(field, field.getAnnotation(Column.class)));
			}
		}
		return columns;
	}

	public static final int mockField = 0;

	private static Column idColumn(final String tableID) {
		var annotation = new Column() {
			@Override
			public Class<? extends Annotation> annotationType() {
				return Column.class;
			}

			@Override
			public String tipoSql() {
				return "INTEGER";
			}

			@Override
			public String nome() {
				return tableID;
			}

			@Override
			public boolean isFk() {
				return false;
			}
		};

		return annotation;
	}
}
