package com.example.hollidayCottages.service;

import com.example.hollidayCottages.Exceptions.ExceptionWithMessage;
import com.example.hollidayCottages.model.Customer;
import com.example.hollidayCottages.model.Reservation;
import com.example.hollidayCottages.repositories.ICatalogData;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ClientService {
    private final ICatalogData data;

    public List<Reservation> getAllReservation(int id) throws ExceptionWithMessage {
        Optional<Customer> customer  =data.getCustomer().findCustomerByUserId(id);
        List<Reservation> list= data.getReservation().findAllByCustomerId(customer.get().getId());
        if(list.isEmpty()){
            throw new ExceptionWithMessage("Brak rezerwacji");
        }
        return list;
    }
    public Reservation getReservationById(int id) throws ExceptionWithMessage {
        Optional<Reservation> res= data.getReservation().findById(id);
        if (res.isEmpty()) throw new AssertionError("Nie znaleziono rezerwacji");
        return res.get();
    }

    public void cancelReservation(int id) throws ExceptionWithMessage {
        Optional<Reservation> reservationOptional= data.getReservation().findById(id);
        if (reservationOptional.isPresent()) {
            Reservation res = reservationOptional.get();
            res.setStatus("Cancel");
            data.getReservation().save(res);
        }
        else {
            throw new ExceptionWithMessage("Reservation not found");
        }
    }
}
