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

	public DatabaseConnection(Class<?>... tables) throws Exception {
		getDBConnection();
		EnsureTablesFunctional(tables);
	}

	private void EnsureTablesFunctional(Class<?>[] tables) throws Exception {
		var dependentTables = EnsureTablesCreated(tables);
		var alterTableQueries = new ArrayList<String>();
		for (var dependentTable : dependentTables) {
			Table annotation = (Table) dependentTable.getAnnotation(Table.class);
			for (var FKInformation : annotation.fks()) {
				alterTableQueries.add(AlterScript(FKInformation, annotation));
			}
		}
		for (String alterTableCommand : alterTableQueries) {
			try { 
			connection.createStatement().execute(alterTableCommand);
			}catch (SQLException e) {
				if(!e.getMessage().contains("duplicate")) {
					throw e;
				}
			}
		}
	}

	private List<Class<?>> EnsureTablesCreated(Class<?>[] tablesToCreate) throws SQLException {
		var dependentTables = new ArrayList<Class<?>>();
		var createTableQueries = new ArrayList<String>();
		for (Class<?> tableClass : tablesToCreate) {
			var annotation = (Table) tableClass.getAnnotation(Table.class);
			createTableQueries.add(CreateScript(tableClass, annotation));
			if (annotation.fks().length >= 1)
				dependentTables.add(tableClass);
		}
		for (String createTableCommand : createTableQueries) {
			connection.createStatement().execute(createTableCommand);
		}
		return dependentTables;
	}

	private String CreateScript(Class<?> tableClass, Table tableAnnotation) {
		String nomeTabela = tableAnnotation.nome();
		String pk = nomeTabela + "ID";
		List<Pair<String, String>> atributos = new ArrayList<Pair<String, String>>();
		for (Field field : tableClass.getDeclaredFields()) {
			if (field.isAnnotationPresent(Column.class)) {
				var annotation = field.getAnnotation(Column.class);
				if(!annotation.isFk())
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

	// ALTER TABLE nomeTabela
	// ADD COLUMN FKColumnName INTEGER REFERENCES
	// FKTargetTableName(FKTargetTablePK);
	private String AlterScript(String FKInformation, Table tableAnnotation) {
		String nomeTabela = tableAnnotation.nome();
		var split = FKInformation.split(";");
		var FKColumnName = split[0];
		var FKTargetTableName = split[1];
		var FKTargetTablePK = FKTargetTableName + "ID";
		String sql = "ALTER TABLE " + nomeTabela + " \n";
		sql += "ADD COLUMN " + FKColumnName + " INTEGER REFERENCES " + FKTargetTableName + "(" + FKTargetTablePK + ")";
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

	public boolean EnsureClosedConnection() throws Exception {
		if (!this.connection.isClosed()) {
			this.connection.close();
			return true;
		}
		return false;
	}
}