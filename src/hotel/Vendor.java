package hotel;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Vendor {
	public static void Foodadd() {
		try {
			Connection c =ConnectionProvider.getConnection();
			PreparedStatement pst = c.prepareStatement("insert into food(name,foodtype,rate) values(?,?,?)");
	          
		    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		    
		    
		    System.out.println("FoodName:");
		     Food.Foodname = br.readLine();
		    System.out.println("FoodType:");
		     Food.Foodtype = br.readLine();
		    System.out.println("FoodRate:");
		   Food.Rate=Integer.parseInt(br.readLine());
		  
		    
		    
		    pst.setString(1,Food.Foodname);
		    pst.setString(2,Food.Foodtype);
		    pst.setInt(3,Food.Rate);
		    
		    
		    pst.executeUpdate();                

		    System.out.println( " Record is inserted");

		    pst.close();
		//	c.close();
			
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
		
	}
	public 	static void FoodDisplay() {
		try {
			Connection c =ConnectionProvider.getConnection();
			String q ="select * from food";
			Statement stmt =c.createStatement();
			ResultSet rs=stmt.executeQuery(q);
			System.out.println("FoodID"+"  "+"FoodName"+"       "+"FoodType"+"     "+"Rate");
			
			while(rs.next())  
				System.out.println(rs.getInt(1)+"        "+rs.getString(2)+"   "+rs.getString(3)+"        "+rs.getInt(4));
			//c.close();
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
	}
	

	

}
