package hotel;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Recptionist {
	
	
	public static void displayRoom() {
		try {
			Connection c =ConnectionProvider.getConnection();
			String q ="select * from rooms";
			Statement stmt =c.createStatement();
			ResultSet rs=stmt.executeQuery(q);
			System.out.println("RoomNumber"+"  "+"Floor"+"  "+"Beds"+"  "+"ChargePerDay"+"  "+"RoomStatus"+"  "+"RoomType"+"  "+"CustomerID");
			
			while(rs.next())  
				System.out.println(rs.getInt(1)+"        "+rs.getInt(2)+"   "+rs.getInt(3)+"        "+rs.getInt(4)+"  "+rs.getString(5)+"  "+rs.getString(6)+"  "+rs.getInt(7));
		//	c.close();
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
	}
	
	
	public static void addRoom() {
		try {
			Connection c =ConnectionProvider.getConnection();
			PreparedStatement pst = c.prepareStatement("insert into rooms(RoomNumber,Floor,Beds,ChargePerDay,RoomType) values(?,?,?,?,?)");
	          
		    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		    
		    System.out.println("RoomNumber:");
		   Room.roomnumber = Integer.parseInt(br.readLine());
		   System.out.println("Floor:");
		   Room.floor = Integer.parseInt(br.readLine());
		    System.out.println("Beds:");
		    Room.beds = Integer.parseInt(br.readLine());
		    System.out.println("ChargePerDay:");
		    Room.Charge = Integer.parseInt(br.readLine());
		 
		    System.out.println("RoomType:");
		    Room.roomtype = br.readLine();
		  
		    pst.setInt(1,Room.roomnumber);
		    pst.setInt(2,Room.floor);
		    pst.setInt(3,Room.beds);
		    pst.setInt(4,Room.Charge);
		    pst.setString(5,Room.roomtype);
		    
		    
		    pst.executeUpdate();                

		    System.out.println( " Record is inserted");

		    pst.close();
		//	c.close();
			
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
		
	}

	public static void checkInRoom(int roomnumber ,int customerid) {
		try {
			Connection c =ConnectionProvider.getConnection();
			PreparedStatement pst = c.prepareStatement("update rooms set CustomerID=?,RoomStatus=? where RoomNumber=?");
	          
		    
		    //System.out.println("customerID:");
		    String status ="Occupied";
		    
		    pst.setInt(1,customerid);
		    pst.setString(2,status);
		    pst.setInt(3,roomnumber);
		    
		    
		    
		    pst.executeUpdate();                

		    System.out.println( " Checkin is done");

		    pst.close();
		//	c.close();
			
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
		
	}
	public static void checkOutRoom(int roomnumber) {
		try {
			Connection c =ConnectionProvider.getConnection();
			PreparedStatement pst = c.prepareStatement("update rooms set customerID=null, RoomStatus=? where RoomNumber=?");
	          
		    
		    //System.out.println("customerID:");
		    String status ="Unoccupied";
		    //int customerid=0;
		  // pst.setInt(1,customerid);
		    pst.setString(1,status);
		    pst.setInt(2,roomnumber);
		    
		    
		    
		    pst.executeUpdate();                

		    System.out.println( " checkout room");

		    pst.close();
		//	c.close();
			
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
		
	}

	public static void bookRoom()
	{
		System.out.println("The list of available Rooms");
		
		
			try {
				Connection c =ConnectionProvider.getConnection();
				String Status="Unoccupied";
				String q ="select * from rooms where RoomStatus= ?";
				PreparedStatement pst = c.prepareStatement(q);
				pst.setString(1,Status);
				ResultSet rs=pst.executeQuery();
				System.out.println("RoomNumber"+"  "+"Floor"+"  "+"Beds"+"  "+"ChargePerDay"+"  "+"RoomStatus"+"  "+"RoomType"+"  "+"CustomerID");
				
				while(rs.next())  
					System.out.println(rs.getInt(1)+"        "+rs.getInt(2)+"   "+rs.getInt(3)+"        "+rs.getInt(4)+"  "+rs.getString(5)+"  "+rs.getString(6)+"  "+rs.getInt(7));
			//	c.close();
			}
			catch(Exception e){
				e.printStackTrace();
				
			}
		
			
			Customer ct=new Customer();
			ct.Add();
			Bill B=new Bill();
			B.addBill();

	}
	public static void checkOut()
	{
		System.out.println("Enter customerId ");
		Scanner sc= new Scanner(System.in);
		int CustomerID=sc.nextInt();
		
		try {
			Connection c =ConnectionProvider.getConnection();
			PreparedStatement pst = c.prepareStatement("update customers set checkout=? where customerID=?");
			 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));  
		    
			System.out.println("Enter checkout date in yyyy-mm-dd format");
			String Checkout=br.readLine();
			
		    pst.setString(1,Checkout);
		    pst.setInt(2,CustomerID);
	
		    
		    
		    
		    pst.executeUpdate();                

		    System.out.println( " Record is updated");
		   
		    Bill.roomCharge(CustomerID);
		    Bill.TotalCharge(CustomerID);
		    Bill.customerDisplay(CustomerID);;
		    
		    Statement stmt =c.createStatement();
			ResultSet rs=stmt.executeQuery("select Roomnumber from rooms where customerID="+ CustomerID);
			rs.next();
			int y= rs.getInt(1);
			//System.out.print(y );
		    Recptionist.checkOutRoom(y);
		    
		    
		    stmt.close();
		    pst.close();
		//	c.close();
		   
			
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
		
	}
	public static void availableRoom()
	{
		System.out.println("The list of available Rooms");
		
		
			try {
				Connection c =ConnectionProvider.getConnection();
				String Status="Unoccupied";
				String q ="select * from rooms where RoomStatus= ?";
				PreparedStatement pst = c.prepareStatement(q);
				pst.setString(1,Status);
				ResultSet rs=pst.executeQuery();
				System.out.println("RoomNumber"+"  "+"Floor"+"  "+"Beds"+"  "+"ChargePerDay"+"  "+"RoomStatus"+"  "+"RoomType"+"  "+"CustomerID");
				
				while(rs.next())  
					System.out.println(rs.getInt(1)+"        "+rs.getInt(2)+"   "+rs.getInt(3)+"        "+rs.getInt(4)+"  "+rs.getString(5)+"  "+rs.getString(6)+"  "+rs.getInt(7));
			
			}
			catch(Exception e){
				e.printStackTrace();
				
			}
		
			
	

	}

		
		
	
}
