package inventoy;
import java.util.*;
public class InventoryService 
{
	 static HashMap<String, Integer> roomCount=new HashMap<>();
	 HashMap<String, Double> roomPrice=new HashMap<>();
	
	public void addRoom(String roomType,int count)
	{
		roomCount.put(roomType, roomCount.getOrDefault(roomType,0)+count);
	}
	public void addPrice(String roomType,double price)
	{
		roomPrice.put(roomType,price);
	}
	public double getPrice(String roomType)
	{
		return roomPrice.get(roomType);
	}
	public int getCount(String roomType)
	{
		return roomCount.get(roomType);
	}
	public static boolean isAvailable(String roomType)
	{
		return roomCount.get(roomType)>0;
	}
	
}
