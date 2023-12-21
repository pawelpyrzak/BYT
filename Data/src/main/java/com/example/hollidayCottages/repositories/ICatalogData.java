package com.example.hollidayCottages.repositories;

public interface ICatalogData {
 CottageRepository getCottage();
 CustomerRepository getCustomer();
 PromotionRepository getPromotion();
 ReservationRepository getReservation();
 ReviewRepository getReview();
 UserRepository getUser();
 UserTypeRepository getUserType();
}
