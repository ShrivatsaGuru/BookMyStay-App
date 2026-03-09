# BookMyStay-App
## Use Case 5: Add-On Service Selection

+ Use Case 5: Add-On Service Selection allows guests to attach optional services (like Breakfast Service, Spa Service, or Airport Pickup Service) to an existing reservation.
+ The system maintains a Map<String, List<Service>> that links a reservation ID to multiple selected services.
+ Each reservation can have multiple add-on services, enabling flexible booking customization.
+ A Service model is used to represent each service with attributes like service name, description, and price.
+ When a guest selects a service, it is added to a list and mapped to the reservation ID.
+ The system automatically calculates the total additional cost based on selected services.
+ The design uses composition, where a reservation contains a list of services.
+ This approach keeps the reservation-service mapping clean and scalable.
+ New services can easily be added in the future without changing existing reservation logic.
+ Overall, this use case enhances the booking experience by allowing customizable hotel services with dynamic pricing.
