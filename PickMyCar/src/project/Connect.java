package project;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
public class Connect
{  static Connection con=null;
	protected static Connection getConnect()  throws SQLException, ClassNotFoundException 
	{
		Class.forName("oracle.jdbc.driver.OracleDriver");
	    con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "ravindra", "abc");
	    return con;
	}
}
