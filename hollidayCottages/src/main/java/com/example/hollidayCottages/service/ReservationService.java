package com.example.hollidayCottages.service;

import com.example.hollidayCottages.Exceptions.ExceptionWithMessage;
import com.example.hollidayCottages.Validator;
import com.example.hollidayCottages.contract.CusRes;
import com.example.hollidayCottages.contract.ReservationDto;
import com.example.hollidayCottages.model.Cottage;
import com.example.hollidayCottages.model.Customer;
import com.example.hollidayCottages.model.Reservation;
import com.example.hollidayCottages.repositories.ICatalogData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ICatalogData data;

    public CusRes CheckData(Reservation reservation, int Customer_id) throws ExceptionWithMessage {
        String comment = "";
        if (reservation.getComments() != null && !reservation.getComments().isEmpty())
            comment = Validator.validate(reservation.getComments());
        int price = Math.toIntExact(getPrice(reservation));
        Optional<Customer> customerOptional = data.getCustomer().findByUserId(Customer_id);
        if (customerOptional.isEmpty())
            throw new ExceptionWithMessage("Brak informacji o Customer");
        if (customerOptional.get().isRegularCustomer()) {
            price -= price * 0.1;
        }
        Reservation reservation1 = new Reservation(reservation.getStartDate(), reservation.getEndDate(), price, reservation.getNumberOfPersons(), comment, customerOptional.get().getId(), reservation.getCottageId(), "PENDING");
        return new CusRes(reservation1, customerOptional.get());
    }

    private long getPrice(Reservation reservation) throws ExceptionWithMessage {
        Optional<Cottage> cottageOptional = data.getCottage().findById(reservation.getCottageId());
        if (cottageOptional.isEmpty()) throw new ExceptionWithMessage("Brak informacji o domku");
        Cottage cottage = cottageOptional.get();
        int people = reservation.getNumberOfPersons();
        int maxPeople = cottage.getMaxPersons();
        if (people < 1)
            throw new ExceptionWithMessage("Minimum 1 osoba");
        else if (maxPeople < people)
            throw new ExceptionWithMessage("Maksymalnie " + maxPeople + " osób");
        return cottage.getPricePerNight() * getDays(reservation);
    }

    private Long getDays(Reservation reservation) throws ExceptionWithMessage {
        Date startDate = reservation.getStartDate();
        Date endDate = reservation.getEndDate();
        long daysDiff = ChronoUnit.DAYS.between(startDate.toLocalDate(), endDate.toLocalDate());
        if (daysDiff < 1)
            throw new ExceptionWithMessage("Za krótki okres, minimum 1 doba");
         else if (daysDiff > 14)
            throw new ExceptionWithMessage("Za długi okres, maksymalnie 14 dni");

        List<Reservation> reservations = data.getReservation().findOverlapReservations(reservation.getCottageId(), startDate, endDate);
        if (!reservations.isEmpty())
            throw new ExceptionWithMessage("Domek jest zajęty w danym terminie");
        return daysDiff;
    }

    public void saveRes(Reservation reservation) {
        data.getReservation().save(reservation);
    }
}
