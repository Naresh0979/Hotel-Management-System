package hotel;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Facilities {
	public void Display() {
		try {
			Connection c =ConnectionProvider.getConnection();
			String q ="select * from facilities";
			Statement stmt =c.createStatement();
			ResultSet rs=stmt.executeQuery(q);
			System.out.println("FacilityID"+"  "+"FacilityName"+"  "+"Charge");
			
			while(rs.next())  
				System.out.println(rs.getInt(1)+"        "+rs.getString(2)+"    "+rs.getInt(3));
			c.close();
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
	}
	
	
	public void Add() {
		try {
			Connection c =ConnectionProvider.getConnection();
			PreparedStatement pst = c.prepareStatement("insert into facilities(Name,charge) values(?,?)");
	          
		    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		    
		    
		    System.out.println("FacilityName:");
		    String Facilityname = br.readLine();
		    
		    System.out.println("FacilityCharge:");
		    int Charge=Integer.parseInt(br.readLine());
		  
		    
		    
		    pst.setString(1,Facilityname);
		    
		    pst.setInt(2,Charge);
		    
		    
		    pst.executeUpdate();                

		    System.out.println( " Record is inserted");

		    pst.close();
		//	c.close();
			
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
		
	}


}
