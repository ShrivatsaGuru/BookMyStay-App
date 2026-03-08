package main;

import hoteladmin.*;
import java.util.*;
import booking.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HotelAdmin admin = new HotelAdmin();
        boolean exit = false;

        while (!exit) {
            System.out.println("Select which operation to perform :\n-1) Add Room\n-2) Get Room Count\n-3) Get Room Price\n-4) Check Rooms\n-5) Add Guest\n-6) Process Booking Request\n-7) Exit");
            int operation = sc.nextInt();
            sc.nextLine();

            switch (operation) {
                case 1:
                    System.out.print("Which room do you want to add:\n-Single\n-Double\n-Suite\n");
                    String room = sc.nextLine();
                    System.out.print("How many " + room + " rooms do you want to add-");
                    int count = sc.nextInt();
                    sc.nextLine();
                    System.out.print("What should be the price of the " + room + " room? - ");
                    int price = sc.nextInt();
                    sc.nextLine();
                    admin.addRoom(room, count);
                    admin.addPrice(room, price);
                    System.out.println("Room added successfully.");
                    break;
                case 2:
                    System.out.print("Which room count do you want to get:\n-Single\n-Double\n-Suite\n");
                    String roomType = sc.nextLine();
                    int roomCount = admin.getRoom(roomType);
                    System.out.println("Number of " + roomType + " rooms: " + roomCount);
                    break;
                case 3:
                    System.out.print("Which room price do you want to get:\n-Single\n-Double\n-Suite\n");
                    String priceRoomType = sc.nextLine();
                    double roomPrice = admin.getPrice(priceRoomType);
                    System.out.println("Price of " + priceRoomType + " room: " + roomPrice);
                    break;
                case 4:
                    System.out.print("Which room type do you want to check availability for:\n-Single\n-Double\n-Suite\n");
                    String checkRoomType = sc.nextLine();
                    boolean isAvailable = admin.checkRooms(checkRoomType);
                    if (isAvailable) {
                        System.out.println(checkRoomType + " rooms are available.");
                    } else {
                        System.out.println(checkRoomType + " rooms are not available.");
                    }
                    break;
                case 5:
                    System.out.print("Enter guest name: ");
                    String guestName = sc.nextLine();
                    Guest guest = new Guest(guestName);
                    System.out.print("Which type of room does the guest want to book:\n-Single\n-Double\n-Suite\n");
                    String bookingRoomType = sc.nextLine();
                    admin.addBookingRequest(guest, bookingRoomType);
                    System.out.println("Guest added successfully.");
                    break;
                case 6:
                    admin.processBookingRequest();
                    System.out.println("Booking processed successfully.");
                    break;
                case 7:
                    System.out.println("Exiting...");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid operation. Please try again.");
            }
        }
    }
}