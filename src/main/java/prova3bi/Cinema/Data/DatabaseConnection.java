package prova3bi.Cinema.Data;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;
import prova3bi.Cinema.Data.Abstractions.Column;
import prova3bi.Cinema.Data.Abstractions.Table;

public class DatabaseConnection {

	private static final Logger logger = Logger.getLogger(DatabaseConnection.class.getName());
	public Connection connection;
	private String DB_DRIVER = "org.sqlite.JDBC";
	private String DB_CONNECTION = "jdbc:sqlite:cinema.db";

	public DatabaseConnection(Class... tables) throws Exception {
		getDBConnection();
		List<Class> compositeTables = new ArrayList<Class>();
		List<String> createTablesSql =  new ArrayList<String>();
		for (Class tableClass : tables) {
			try {
				Table annotation = (Table) tableClass.getAnnotation(Table.class);
				createTablesSql.add(CreateScript(tableClass, annotation));
				if (annotation.fks().length >= 1)
					compositeTables.add(tableClass);
			} catch (Exception e) {
				throw e;
			}
		}
		for (String createTableCommand : createTablesSql) {
			connection.createStatement().execute(createTableCommand);
		}
	}

	private String CreateScript(Class tableClass, Table tableAnnotation) {
		String nomeTabela = tableAnnotation.nome();
		String pk = nomeTabela + "ID";
		List<Pair<String, String>> atributos = new ArrayList<Pair<String, String>>();
		for (Field field : tableClass.getDeclaredFields()) {
			if (field.isAnnotationPresent(Column.class)) {
				var annotation = field.getAnnotation(Column.class);
				atributos.add(new Pair<String, String>(annotation.nome(), annotation.tipoSql()));
			}
		}
		String sql = "CREATE TABLE IF NOT EXISTS " + nomeTabela + " \n";
		sql += "( \n";
		sql += " " + pk + " INTEGER PRIMARY KEY AUTOINCREMENT" + ", \n";
		sql += getAnyAtributes(atributos);
		sql += " ); \n";
		return sql;
	}

	private String getAnyAtributes(List<Pair<String, String>> atributes) {
		String atributesSql = "";
		int j = 0;
		for (Pair<String, String> atributo : atributes) {
			atributesSql += " " + atributo.getKey() + " " + atributo.getValue();
			if (j != (atributes.size() - 1))
				atributesSql += ", \n";
			j++;
		}
		return atributesSql;
	}

	public void getDBConnection() throws SQLException {
		try {
			Class.forName(DB_DRIVER);
		} catch (ClassNotFoundException exception) {
			logger.log(Level.SEVERE, exception.getMessage());
		}
		try {
			this.connection = DriverManager.getConnection(DB_CONNECTION);
		} catch (SQLException exception) {
			logger.log(Level.SEVERE, exception.getMessage());
		}
	}

	private void DisconnectDB() {
		try {
			if (!this.connection.isClosed()) {
				this.connection.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
	}
}