package hotel;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

public class Customer implements Person {
	
	public void Display() {
		try {
			Connection c =ConnectionProvider.getConnection();
			String q ="select * from customers";
			Statement stmt =c.createStatement();
			ResultSet rs=stmt.executeQuery(q);
			System.out.println("CustomerID"+"  "+"Name"+"  "+"Gender"+"   "+"Age"+"  "+"RoomNumber"+"  "+"PhoneNumber"+"  "+"CheckIN"+"  "+"CheckOut");
			
			while(rs.next())  
				System.out.println(rs.getInt(1)+"        "+rs.getString(2)+"   "+rs.getString(3)+"        "+rs.getInt(4)+"   "+ rs.getInt(5)+"   "+rs.getString(6)+"  "+rs.getString(7)+"  "+rs.getString(8));
			//c.close();
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
	}
	
	public void displayRoom(int roomnumber) {
		try {
			Connection c =ConnectionProvider.getConnection();
			String q ="select * from customers where RoomNumber="+roomnumber;
			Statement stmt =c.createStatement();
			ResultSet rs=stmt.executeQuery(q);
			System.out.println("CustomerID"+"  "+"Name"+"  "+"Gender"+"   "+"Age"+"  "+"RoomNumber"+"  "+"PhoneNumber"+"  "+"CheckIN"+"  "+"CheckOut");
			
			while(rs.next())  
				System.out.println(rs.getInt(1)+"        "+rs.getString(2)+"   "+rs.getString(3)+"        "+rs.getInt(4)+"   "+ rs.getInt(5)+"   "+rs.getString(6)+"  "+rs.getString(7)+"  "+rs.getString(8));
			//c.close();
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
	}
	
	public void Add() {
		try {
			Connection c =ConnectionProvider.getConnection();
			PreparedStatement pst = c.prepareStatement("insert into customers(RoomNumber,Name,gender,age,PhoneNumber,Checkin) values(?,?,?,?,?,?)");
	          
		    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		    
		  
		    System.out.println("Enter RoomNumber from the above list:");
		    int Roomnumber = Integer.parseInt(br.readLine());
		    System.out.println("Name:");
		    String name = br.readLine();
		    System.out.println("Gender:");
		    String gender = br.readLine();
		    System.out.println("Age:");
		    int age = Integer.parseInt(br.readLine());
		    
		    System.out.println("PhoneNubmer:");
		    String phonenumber = br.readLine();
		    System.out.println("Enter CheckINDate in yyyy-mm-dd format:");
		    String CheckIn = br.readLine();
		    
		    
		    
		    pst.setInt(1,Roomnumber);
		    pst.setString(2,name);
		    pst.setString(3,gender);
		    pst.setInt(4,age);
		    
		    pst.setString(5,phonenumber);
		    pst.setString(6,CheckIn);
		    
		    
		    pst.executeUpdate();                

		    System.out.println( " Record is inserted");
		    String q="SELECT customerID FROM customers ORDER BY customerID DESC LIMIT 1";
		    
		    Statement stmt =c.createStatement();
			ResultSet rs=stmt.executeQuery(q);
			rs.next();
		    int Customerid=rs.getInt(1);
			Recptionist.checkInRoom(Roomnumber,Customerid);
			stmt.close();
		    pst.close();
		    
		//	c.close();
			
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
		
	}

	public void Delete() {
		try {
			Connection c =ConnectionProvider.getConnection();
			String q ="delete from customers where customerID=?";
			PreparedStatement pst = c.prepareStatement(q);
	          
		    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		    
		    System.out.println("Enter customerID:");
		    int CustomerID = Integer.parseInt(br.readLine());
		   
		    pst.setInt(1,CustomerID);
			
		    pst.executeUpdate();
		    
		    pst.close();
			//c.close();
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
	}
	


}
