package com.example.hollidayCottages.service;

import com.example.hollidayCottages.Exceptions.ExceptionWithMessage;
import com.example.hollidayCottages.Validator;
import com.example.hollidayCottages.contract.CusRes;
import com.example.hollidayCottages.contract.ReservationDto;
import com.example.hollidayCottages.model.Cottage;
import com.example.hollidayCottages.model.Customer;
import com.example.hollidayCottages.model.Reservation;
import com.example.hollidayCottages.repositories.ICatalogData;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
@AllArgsConstructor
public class ReservationService {
    final ICatalogData data;

    public CusRes CheckData(ReservationDto reservation, int Customer_id, int Cottage_id) throws ExceptionWithMessage {
        String StartDate = reservation.getStartDate();
        String EndDate = reservation.getEndDate();
        String comment = "";
        if (reservation.getComments()!=null&&!reservation.getComments().isEmpty())
            comment = Validator.validate(reservation.getComments());
//doby

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(reservation.getStartDate(), formatter);
        LocalDate endDate = LocalDate.parse(reservation.getEndDate(), formatter);
        int doby = Math.toIntExact(ChronoUnit.DAYS.between(startDate, endDate));
        int people = reservation.getNumberOfPersons();
        if (doby < 1) {
            throw new ExceptionWithMessage("Za krótki okres, minimum 1 doba");
        } else if (doby > 14) {
            throw new ExceptionWithMessage("Za długi okres, maksymalnie 14 dni");
        }
        List<Reservation> reservations = data.getReservation().findOverlapReservations(reservation.getCottageId(),Date.valueOf(reservation.getStartDate()) , Date.valueOf(reservation.getEndDate()));
       System.out.println(reservations.size());
        if (!reservations.isEmpty()) {
            throw new ExceptionWithMessage("Domek jest zajęty w danym terminie");
        }
        Optional<Cottage> cottageOptional = data.getCottage().findById(Cottage_id);
        if (cottageOptional.isEmpty()) {
            throw new ExceptionWithMessage("Brak informacji o domku");
        }

        Cottage cottage = cottageOptional.get();
        int pricePerDay = cottage.getPricePerNight();
        int maxPeople = cottage.getMaxPersons();

        // Check number of people
        if (people < 1) {
            throw new ExceptionWithMessage("Minimum 1 osoba");
        } else if (maxPeople < people) {
            throw new ExceptionWithMessage("Maksymalnie " + maxPeople + " osób");
        }
        int price = pricePerDay * doby;

        // Check if the customer is a regular customer
        Optional<Customer> customerOptional = data.getCustomer().findByUserId(Customer_id);
        if (customerOptional.isPresent() && customerOptional.get().isRegularCustomer()) {
            price -= price * 0.1;
        }
        Reservation reservation1=new Reservation(Date.valueOf(startDate),Date.valueOf(endDate),price, people,comment,customerOptional.get().getId(),Cottage_id,"PENDING" );
        return new CusRes(reservation1,customerOptional.get());
    }

    public void saveRes(Reservation reservation) {
        data.getReservation().save(reservation);
    }
}
