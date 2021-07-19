package hotel;

import java.util.*;

public class Hotel {
			public static void main(String[] args)
			{
			while(true)
				{
				System.out.println("Enter: 1.Admin \n       2.Receptionist\n       3.Food Vendor\n       4.to Exit");
				Scanner sc= new Scanner(System.in);
				int choose= sc.nextInt();
				switch(choose)
				{
					case 1 :System.out.println("1. Staff Details");
							System.out.println("2. Add Staff Members");
							System.out.println("3. Delete Staff Members");
							System.out.println("4. Update Staff Members");
							System.out.println("5. Add Room");
							System.out.println("6. Display Room Details");
							int option=sc.nextInt();
							Staff s=new Staff();
							switch(option)
							{
								case 1 :s.Display();
										break;
								case 2:s.Add();
										break;
								case 3:s.Delete();
										break;
								case 4:s.Update();
										break;
								case 5:Recptionist.addRoom();
										break;
								case 6:Recptionist.displayRoom();
										break;
								default:break;
							}
							break;
					case 2:	System.out.println("1. Display the Details of all Rooms");
							System.out.println("2. Book a Room");
							System.out.println("3. Checkout and Generate Total Bill");
							System.out.println("4. Display Record of all customers");
							System.out.println("5. Display Record of all customers stayed in a particular Room");
							System.out.println("6. Display list of all available Rooms");
							int Choice=sc.nextInt();
							switch(Choice)
							{
								case 1:Recptionist.displayRoom();
										break;
										
								case 2:Recptionist.bookRoom();;
										break;
								case 3:Recptionist.checkOut();
										break;
								case 4:Customer cs=new Customer();
										cs.Display();
										break;
								case 5:Customer cc=new Customer();
										System.out.println("Enter the Room number");
										int roomnumber=sc.nextInt();
										cc.displayRoom(roomnumber);;
										break;
								case 6:Recptionist.availableRoom();
										break;
								default:break;
							}
							
							
							break;
					case 3:	System.out.println("1. Display the Menu");
							System.out.println("2. Genrate the Food Bill");
							System.out.println("3. Display the Food Bill");
							System.out.println("4. Add Food Item to the Menu");
							
							int choice=sc.nextInt(); 
							switch(choice)
							{
								case 1:Vendor.FoodDisplay();
										break;
								case 2:System.out.println("Enter the customer ID");
										int customer_ID=sc.nextInt();
										Vendor.FoodDisplay();
										
										while(true)
										{
										
											System.out.println("Press the FoodIDs which you want to add in the Bill");
											System.out.println("Press 0 as to complete the Bill");
											int food_ID=sc.nextInt();
											if(food_ID==0)
												break;
											else
											{
												
												Bill.foodCharge(food_ID,customer_ID);
											}
										}
										break;
								case 3:	System.out.println("Enter the customer ID");
										int Customer_ID=sc.nextInt();
										Bill.displayBill(Customer_ID);
										break;
								
								case 4: Vendor.Foodadd();
										break;
										
								default: break;
							}
					
						
							break;
					case 4: System.exit(0);
							break;
					default:System.out.println("Wrong choice enter!! ");
        	            	break;
            
				}
				
			}
		
				
			}
			
			
}
