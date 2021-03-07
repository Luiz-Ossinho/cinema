package prova3bi.Cinema.Data.Helpers;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import prova3bi.Cinema.Data.Abstractions.Builder;
import prova3bi.Cinema.Data.Abstractions.Column;
import prova3bi.Cinema.Data.Abstractions.Table;
import prova3bi.Cinema.Domain.Entidades.Entidade;

public class Conversor {

	public static <T extends Entidade> List<T> convert(ResultSet from, Class<T> to) throws SQLException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		List<T> intances = new ArrayList<T>();
		var columns = getColumns(to);
		var idName = to.getAnnotation(Table.class).nome() + "ID";
		columns.add(idColumn(idName));
		Collections.sort(columns, Column.comp);
		while (from.next()) {
			Collection<Object> parametrosConstrutor = new ArrayList<Object>();
			for (Column column : columns)
				parametrosConstrutor.add(getObject(from, column.nome()));
			
			intances.add((T) getReadConstructor(to).newInstance(parametrosConstrutor.toArray()));
		}
		return intances;
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

	private static <T extends Entidade> Constructor<?> getReadConstructor(Class<T> toClass) {
		Constructor<?> readCtor = null;
		for (Constructor<?> ctor : toClass.getConstructors()) {
			if (ctor.getAnnotation(Builder.class).value() == Builder.Is.Read) {
				readCtor = ctor;
				break;
			}
		}
		return readCtor;
	}

	private static <T extends Entidade> ArrayList<Column> getColumns(Class<T> toClass) {
		ArrayList<Column> columns = new ArrayList<Column>();
		for (Field field : toClass.getDeclaredFields()) {
			if (field.isAnnotationPresent(Column.class))
				columns.add(field.getAnnotation(Column.class));
		}
		return columns;
	}

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
			public Class<?> tipo() {
				return Integer.class;
			}

			@Override
			public String nome() {
				return tableID;
			}
		};

		return annotation;
	}
}
