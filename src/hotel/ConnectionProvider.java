package hotel;
import java.sql.Connection;
import java.sql.DriverManager;


public class ConnectionProvider {
	private static Connection Con;
   public static Connection getConnection(){  
	try{  
		if(Con==null)
		{
			Class.forName("com.mysql.jdbc.Driver");  
			Con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Hotel","root","password");  
 
		  
      }
	}
	catch(Exception e)
	{ 
		System.out.println(e);
	} 
	return Con;
  }  
} 