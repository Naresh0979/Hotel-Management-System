package hotel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class Bill {
	
	
	
	
	public static void Display() {
		try {
			Connection c =ConnectionProvider.getConnection();
			String q ="select * from bill";
			Statement stmt =c.createStatement();
			ResultSet rs=stmt.executeQuery(q);
			System.out.println("BillNumber"+"  "+"CustomerID"+"  "+"FoodCharge"+"  "+"RoomCharge"+"  "+"TotalBill");
			
			while(rs.next())  
				System.out.println(rs.getInt(1)+"        "+rs.getInt(2)+"   "+rs.getInt(3)+"        "+rs.getInt(4)+"  "+rs.getInt(5));
		//	c.close();
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
	}
	public static void customerDisplay(int CustomerID) {
		try {
			Connection c =ConnectionProvider.getConnection();
			String q ="select * from bill where customerID="+CustomerID;
			Statement stmt =c.createStatement();
			ResultSet rs=stmt.executeQuery(q);
			System.out.println("BillNumber"+"  "+"CustomerID"+"  "+"FoodCharge"+"  "+"RoomCharge"+"  "+"TotalBill");
			
			while(rs.next())  
				System.out.println(rs.getInt(1)+"        "+rs.getInt(2)+"   "+rs.getInt(3)+"        "+rs.getInt(4)+"  "+rs.getInt(5));
		//	c.close();
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
	}
	
	public static void displayBill(int CustomerID) {
		try {
			Connection c =ConnectionProvider.getConnection();
			String q ="select billNumber,customerID,Food_Bill from bill where customerID="+CustomerID;
			Statement stmt =c.createStatement();
			ResultSet rs=stmt.executeQuery(q);
			System.out.println("BillNumber"+"  "+"CustomerID"+"  "+"FoodCharge");
			
			while(rs.next())  
				System.out.println(rs.getInt(1)+"              "+rs.getInt(2)+"            "+rs.getInt(3));
		//	c.close();
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
	}
	
	
	public static void roomCharge(int CustomerID)
	 {		
		  
		 try {
				Connection c =ConnectionProvider.getConnection();
				String q ="select datediff(checkout,checkin) from customers Where customerID="+CustomerID;
				Statement stmt =c.createStatement();
				ResultSet rs=stmt.executeQuery(q);
				
				 rs.next();

				 	int days=rs.getInt(1)+1;
				 	
			
					stmt =c.createStatement();
					rs=stmt.executeQuery("select ChargePerDay from rooms where customerID="+CustomerID);
					
					 rs.next();
					int charge=rs.getInt(1);
					//System.out.println("charge"+charge);
					int TotalCharge=charge*days;
					//System.out.println("TotalCharge"+TotalCharge);
					stmt.close();
					PreparedStatement pst = c.prepareStatement("update bill set Room_Charge=? where customerID="+CustomerID);
					pst.setLong(1,TotalCharge);
					pst.executeUpdate();                

					System.out.println( " charge is inserted");

					pst.close();

			
				 
		
				
		}
		catch(Exception e){
				
			e.printStackTrace();
				
		}
		 
		
	}
	
	public static void foodCharge(int foodID,int CustomerID)
	 {		
		  
		 try {
				Connection c =ConnectionProvider.getConnection();
				String q ="select rate from food Where foodID="+foodID;
				Statement stmt =c.createStatement();
				ResultSet rs=stmt.executeQuery(q);
				rs.next();
				    int foodrate=rs.getInt(1); 
				    
					stmt =c.createStatement();
					int pbill=0;
				
					rs=stmt.executeQuery("select Food_Bill from bill where customerID="+CustomerID);
						
					rs.next();
					pbill=rs.getInt(1);
					
					//System.out.println("pbill"+pbill);
					int totalcharge=pbill+foodrate;
					//System.out.println("TotalCharge"+TotalCharge);
					stmt.close();
					PreparedStatement pst = c.prepareStatement("update bill set Food_Bill=? where customerID="+CustomerID);
					pst.setLong(1,totalcharge);
					pst.executeUpdate();                

					System.out.println("Food charge is inserted");

					pst.close();
					
				
			
				 
		
				
		}
		catch(Exception e){
				
			e.printStackTrace();
				
		}
		 
		
	}

	public static void TotalCharge(int CustomerID)
	 {		
		  
		 try {
				Connection c =ConnectionProvider.getConnection();
				String q ="select Food_Bill, Room_Charge from bill where customerID="+CustomerID;
				Statement stmt =c.createStatement();
				ResultSet rs=stmt.executeQuery(q);
				rs.next();
				int foodrate=rs.getInt(1);
				int roomrate=rs.getInt(2);
				System.out.println( "foodrate"+foodrate);
				System.out.println( "roomrate"+roomrate);
			
					int Totalcharge=roomrate+foodrate;
					System.out.println( "Totalcharge"+Totalcharge);
					stmt.close();
					PreparedStatement pst = c.prepareStatement("update bill set Total_Bill=? where customerID="+CustomerID);
					pst.setLong(1,Totalcharge);
					pst.executeUpdate();                

					System.out.println( "charge is inserted");
					
					pst.close();

					
				 
		
				
		}
		catch(Exception e){
				
			e.printStackTrace();
				
		}
		 
		
	}
	 void addBill()
	 {		
		  
		 try {
				Connection c =ConnectionProvider.getConnection();
				String q="SELECT customerID FROM customers ORDER BY customerID DESC LIMIT 1";
			    
			    Statement stmt =c.createStatement();
				ResultSet rs=stmt.executeQuery(q);
				rs.next();
			    int Customerid=rs.getInt(1);
			    stmt.close();
				PreparedStatement pst = c.prepareStatement("insert into bill(customerID) values(?)");
				pst.setLong(1,Customerid);
				pst.executeUpdate();                

				System.out.println( " customeris added to bill");
				

				pst.close();

			    
			    
				
				 
		
				
		}
		catch(Exception e){
				
			e.printStackTrace();
				
		}
		 
		
	}


}
