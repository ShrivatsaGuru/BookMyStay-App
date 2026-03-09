# BookMyStay-App
## Use Case 3: Booking Request (First-Come-First-Served)

+ Use Case 3 implements a booking request system that processes reservations using the First-Come-First-Served (FCFS) principle.
+ The system stores booking requests inside a Queue<Reservation> implemented using LinkedList.
+ When a Guest submits a booking request, the request is added to the queue (enqueue).
+ Requests are processed strictly in arrival order, ensuring fair allocation of booking slots.
+ This approach prevents race conditions and unfair prioritization during high-traffic periods.
+ The BookingQueueService manages all queue operations such as adding and processing reservations.
+ Each Reservation object stores essential details like guest name and requested room type.
+ The queue ensures that the earliest request is always processed first (FIFO behavior).
+ This model closely reflects real-world booking systems used in ticketing, hotels, and reservations.
+ The design demonstrates the use of Queue data structures, service classes, and ordered request handling in Java.
