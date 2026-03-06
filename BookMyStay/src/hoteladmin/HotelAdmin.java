package hoteladmin;
import inventory.InventoryService;

public class HotelAdmin 
{
	static InventoryService is=new InventoryService();
	
	public static void addRoom(String roomType,int count)
	{
		is.addRoom(roomType,count);
	}
	public static void addPrice(String roomType,double price)
	{
		is.addPrice(roomType, price);
	}
	public static int getRoom(String roomType)
	{
		return is.getCount(roomType);
	}
	public static double getPrice(String roomType)
	{
		return is.getPrice(roomType);
	}
}
