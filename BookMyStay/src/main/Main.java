package main;
import java.util.*;

import hoteladmin.HotelAdmin;
import inventoy.InventoryService;
public class Main {

	public static void main(String[] args) 
	{
		Scanner sc=new Scanner(System.in);
		HotelAdmin admin=new HotelAdmin();

		while(true)
		{
			System.out.print("Which room do you want to add:\n-Single\n-Double\n-Suite\n");
			String room=sc.nextLine();
			System.out.print("How many " +room+" rooms do you want to add-");
			int count=sc.nextInt();
			sc.nextLine();
			System.out.print("What shoudld be the price of the "+room+" room? - ");
			int price=sc.nextInt();
			sc.nextLine();
			admin.addRoom(room,count);
			admin.addPrice(room,price);
			System.out.println("Do you want to add another room?(y/n): ");
			String choice=sc.nextLine();
			if(choice.equals("n"))
			{
				break;
			}
		}
		System.out.println("Enter room type to check availability: ");
		String choice=sc.nextLine();
		System.out.println("Availability Status :"+InventoryService.isAvailable(choice));
	}

}
