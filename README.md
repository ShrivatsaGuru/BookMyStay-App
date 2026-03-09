# BookMyStay-App
## Use Case 6: Booking History & Reporting

+ Use Case 6: Booking History & Reporting maintains a complete record of all confirmed reservations in the system.
+ The core data structure used is a List<Reservation>, which stores reservations in chronological order.
+ When a booking is confirmed by the booking service, the reservation is added to the booking history list.
+ Each reservation record includes details such as guest name, room type, room ID, number of nights, and booking status.
+ The system supports historical tracking, allowing both confirmed and cancelled bookings to remain in the history.
+ A dedicated ReportManager retrieves reservation data from the history service.
+ The reporting feature allows administrators to generate booking history reports directly from stored records.
+ This design ensures a reliable audit trail for all booking activities.
+ The history list can also be used for future analytics such as revenue reports or occupancy statistics.
+ Overall, this use case improves system transparency, reporting capabilities, and customer support operations.
