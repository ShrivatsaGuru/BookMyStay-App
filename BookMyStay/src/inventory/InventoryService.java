package inventory;
import java.util.*;
public class InventoryService 
{
	 static HashMap<String, Integer> roomCount=new HashMap<>();
	 static HashMap<String, Double> roomPrice=new HashMap<>();
	
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
	public static void checkRooms() {
	    System.out.println("Available Rooms:");
	    for (String roomType : roomCount.keySet()) {
	        int count = roomCount.get(roomType);
	        if (count > 0) {
	            double price = roomPrice.getOrDefault(roomType, 5110.0);
	            System.out.println(roomType + " | Count: " + count + " | Price: " + price);
	        }
	    }
	}
	
}
