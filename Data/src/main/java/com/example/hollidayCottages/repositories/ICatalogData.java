package com.example.hollidayCottages.repositories;

public interface ICatalogData {
 CottageRepository getCottage();
 CustomerRepository getCustomer();
 ReservationRepository getReservation();
 UserRepository getUser();
 UserTypeRepository getUserType();
}
