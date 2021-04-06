package prova3bi.Cinema.Data;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import prova3bi.Cinema.Data.Abstractions.Query;
import prova3bi.Cinema.Data.Abstractions.Table;
import prova3bi.Cinema.Data.Helpers.Converter;
import prova3bi.Cinema.Domain.Entities.Entity;

public class DBContext implements AutoCloseable {
	private DatabaseConnection DBConnection;

	public DBContext() {
		try {
			var tabelas = getClasses();
			DBConnection = new DatabaseConnection(tabelas);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public <T extends Entity> T get(Query<T> fromQuery) {
		ResultSet results = null;
		T instance = null;
		try {
			results = DBConnection.connection.createStatement().executeQuery(fromQuery.toString());
			instance = Converter.convert(results, fromQuery.type).stream().findFirst().get();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| SQLException e) {
			e.printStackTrace();
		}
		return instance;
	};

	public <T extends Entity> List<T> getAll(Query<T> fromQuery) {
		List<T> instances = new ArrayList<T>();
		try {
			var results = DBConnection.connection.createStatement().executeQuery(fromQuery.toString());
			instances.addAll(Converter.convert(results, fromQuery.type));
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| SQLException e) {
			e.printStackTrace();
		}
		return instances;
	};

	public int execute(Query<?> query) {
		int update = -1;
		int generatedKey = -1;
		try {
			String queryStr = query.toString();
			var pstm = DBConnection.connection.prepareStatement(queryStr, Statement.RETURN_GENERATED_KEYS);

			update = pstm.executeUpdate();
			generatedKey = pstm.getGeneratedKeys().getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (generatedKey > 0) ? generatedKey : update;
	}

	private static ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

	private static Class<?>[] getClasses() throws ClassNotFoundException, IOException {
		var packageName = "prova3bi.Cinema.Domain.Entities";
		var path = packageName.replace('.', '/');
		var resources = classLoader.getResources(path);
		var dirs = new ArrayList<File>();
		while (resources.hasMoreElements()) {
			var resource = resources.nextElement();
			dirs.add(new File(resource.getFile()));
		}
		var classes = new ArrayList<Class<?>>();
		for (File directory : dirs) {
			classes.addAll(findClasses(directory, packageName));
		}
		var filteredStream = classes.stream().filter(c -> c.isAnnotationPresent(Table.class));
		return filteredStream.toArray(Class[]::new);
	}

	private static List<Class<?>> findClasses(File directory, String packageName) throws ClassNotFoundException {
		var classes = new ArrayList<Class<?>>();
		if (!directory.exists()) {
			return classes;
		}
		var files = directory.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				assert !file.getName().contains(".");
				classes.addAll(findClasses(file, packageName + "." + file.getName()));
			} else if (file.getName().endsWith(".class")) {
				classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6),
						false, classLoader));
			}
		}
		return classes;
	}

	@Override
	public void close() throws Exception {
		if (!DBConnection.EnsureClosedConnection())
			throw new Exception("Nao foi possivel fechar conexao com o banco de dados");
	}

}
