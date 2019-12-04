package application;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection
{
	public static Connection dbConnector(DatabaseType type)
	{
		if(type == null)
			throw new IllegalArgumentException("Null Database Type");
		
		try
		{
			Class.forName("org.sqlite.JDBC");
			Connection connect = DriverManager.getConnection("jdbc:sqlite::resource:" + DatabaseConnection.class.getResource("/resources/databases/" + type.toString() + ".sqlite"));
			
			return connect;
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
}
