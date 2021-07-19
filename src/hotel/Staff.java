package hotel;

import java.sql.*;
import java.util.Scanner;
import java.io.*;

import java.io.BufferedReader;

public class Staff implements Person {
	public void Display() {
		try {
			Connection c =ConnectionProvider.getConnection();
			String q ="select * from Staff";
			Statement stmt =c.createStatement();
			ResultSet rs=stmt.executeQuery(q);
			System.out.println("StaffID"+"  "+"Name"+"  "+"Gender"+"  "+"Age"+"  "+"PhoneNumeber"+"  "+"Post"+"  "+"Salary");
			
			while(rs.next())  
				System.out.println(rs.getInt(1)+"        "+rs.getString(2)+"   "+rs.getString(3)+"        "+rs.getInt(4)+"  "+rs.getString(5)+"  "+rs.getString(6)+"  "+rs.getInt(7));
			//c.close();
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
	}
	public  void Add() {
		try {
			Connection c =ConnectionProvider.getConnection();
			PreparedStatement pst = c.prepareStatement("insert into Staff(name,gender,age,PhoneNumber,Post,Salary) values(?,?,?,?,?,?)");
	          
		    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		    
		    System.out.println("Name:");
		    String name = br.readLine();
		    System.out.println("Gender:");
		    String gender = br.readLine();
		    System.out.println("Age:");
		    int age = Integer.parseInt(br.readLine());
		    System.out.println("PhoneNubmer:");
		    String phonenumber = br.readLine();
		    System.out.println("Post:");
		    String post = br.readLine();
		    System.out.println("Salary:");
		    int salary = Integer.parseInt(br.readLine());
		    
		    pst.setString(1,name);
		    pst.setString(2,gender);
		    pst.setInt(3,age);
		    pst.setString(4,phonenumber);
		    pst.setString(5,post);
		    pst.setInt(6,salary);
		    
		    pst.executeUpdate();                

		    System.out.println( " Record is inserted");

		    pst.close();
		//	c.close();
			
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
		
	}
	public void Update() {
		 System.out.println("Enter StaffID:");
		 Scanner sc= new Scanner(System.in);
			
		 	int staffID=sc.nextInt();
		try {
			Connection c =ConnectionProvider.getConnection();
			PreparedStatement pst = c.prepareStatement("update Staff set Post=?,Salary=? where StaffID="+staffID);
	          
		    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		    
		    System.out.println("Enter New Post:");
		    String post=br.readLine();
		    System.out.println("Salary:");
		    int salary = Integer.parseInt(br.readLine());
		   
		   
		   
		   
		    
		    pst.setString(1,post);
		    pst.setInt(2,salary);
		    
		    
		    pst.executeUpdate();                

		    System.out.println( " Record is Updated");

		   pst.close();

			
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
		
	}
	public void Delete() {
		try {
			Connection c =ConnectionProvider.getConnection();
			String q ="delete from Staff where StaffID=?";
			PreparedStatement pst = c.prepareStatement(q);
	          
		    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		    
		    System.out.println("Enter StaffID:");
		    int staffID = Integer.parseInt(br.readLine());
		   
		    pst.setInt(1,staffID);
			
		    pst.executeUpdate();
		    
		    pst.close();
			//c.close();
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
	}
	
	
}