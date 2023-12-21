package com.example.hollidayCottages.repositories;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Getter
public class DomkiDataCatalog implements ICatalogData {


    private final CottageRepository cottage;

    private final  CustomerRepository customer;

    private final PromotionRepository promotion;

    private final  ReservationRepository reservation;

    private final  ReviewRepository review;

    private final UserRepository user;

    private final UserTypeRepository userType;
}
