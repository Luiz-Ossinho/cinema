package prova3bi.Cinema.Data;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import prova3bi.Cinema.Data.Abstractions.Query;
import prova3bi.Cinema.Data.Abstractions.Table;
import prova3bi.Cinema.Data.Helpers.Conversor;
import prova3bi.Cinema.Domain.Entidades.Entidade;

public class DBContext {
	private DatabaseConnection DB;

	public DBContext() {
		try {
			var tabelas = getClasses();
			DB = new DatabaseConnection(tabelas);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public <T extends Entidade> T get(Query<T> fromQuery) {
		ResultSet results = null;
		T instance = null;
		try {
			results = DB.connection.createStatement().executeQuery(fromQuery.toString());
			instance = Conversor.convert(results, fromQuery.type).stream().findFirst().get();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| SQLException e) {
			e.printStackTrace();
		}
		return instance;
	};

	public <T extends Entidade> List<T> getAll(Query<T> fromQuery) {
		ResultSet results = null;
		List<T> instances = null;
		try {
			results = DB.connection.createStatement().executeQuery(fromQuery.toString());
			instances = Conversor.convert(results, fromQuery.type);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| SQLException e) {
			e.printStackTrace();
		}
		return instances;
	};

	public int execute(Query query) {
		int update = -1;
		int generatedKey = -1;
		try {
			PreparedStatement pstm = DB.connection.prepareStatement(query.toString(), Statement.RETURN_GENERATED_KEYS);
			update = pstm.executeUpdate();
			generatedKey = pstm.getGeneratedKeys().getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (generatedKey > 0) ? generatedKey : update;
	}

	private static ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

	private static Class[] getClasses() throws ClassNotFoundException, IOException {
		String packageName = "prova3bi.Cinema.Domain.Entidades";
		String path = packageName.replace('.', '/');
		Enumeration<URL> resources = classLoader.getResources(path);
		List<File> dirs = new ArrayList<File>();
		while (resources.hasMoreElements()) {
			URL resource = resources.nextElement();
			dirs.add(new File(resource.getFile()));
		}
		ArrayList<Class> classes = new ArrayList<Class>();
		for (File directory : dirs) {
			classes.addAll(findClasses(directory, packageName));
		}
		var filteredStream = classes.stream().filter(c -> c.isAnnotationPresent(Table.class));
		return filteredStream.toArray(Class[]::new);
	}

	private static List<Class> findClasses(File directory, String packageName) throws ClassNotFoundException {
		List<Class> classes = new ArrayList<Class>();
		if (!directory.exists()) {
			return classes;
		}
		File[] files = directory.listFiles();
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

}
