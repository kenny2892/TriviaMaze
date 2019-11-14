package application;

import java.sql.Connection;
import java.sql.DriverManager;

public class JavaDatabaseConnection
{
	public static Connection dbConnector()
	{
		try
		{
			Class.forName("org.sqlite.JDBC");
			Connection connect = DriverManager.getConnection("jdbc:sqlite::resource:" + JavaDatabaseConnection.class.getResource("/resources/databases/myqs.db"));
			
			return connect;
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
}
