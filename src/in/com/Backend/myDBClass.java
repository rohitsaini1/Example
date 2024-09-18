package in.com.Backend;

import java.sql.Connection;
import java.sql.DriverManager;

public class myDBClass {
	Connection con=null;
//	public Connection getMyConnection()throws Exception
//	
//	{
//		Class.forName("com.mysql.jdbc.Driver");
//		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/userdatabase", "root", "");
//		return con; 
//	}

	public Connection getMyConnection() throws Exception {
		// TODO Auto-generated method stub
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/userdatabase", "root", "");
		return con;
		
		//return null;
	}
	
}
