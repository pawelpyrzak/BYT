package com.example.hollidayCottages.service;

import com.example.hollidayCottages.Exceptions.ExceptionWithMessage;
import com.example.hollidayCottages.contract.CusRes;
import com.example.hollidayCottages.model.Customer;
import com.example.hollidayCottages.model.Reservation;
import com.example.hollidayCottages.repositories.ICatalogData;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AdminService {
    private final ICatalogData data;

    public List<CusRes> getReservationAndCustomer(String startDate, String endDate, int cottageId) {
        List<Reservation> reservations = data.getReservation().findByStartDateBetweenAndCottageId(Date.valueOf(startDate), Date.valueOf(endDate), cottageId);
        List<CusRes> cusResList = new ArrayList<>();
        reservations.forEach(reservation -> {
            data.getCustomer().findById(reservation.getCustomerId()).ifPresent(customer -> {
                CusRes cusRes = new CusRes(reservation, customer);
                cusResList.add(cusRes);
            });
        });
        return cusResList;
    }

    public int getLastId() {
        return data.getCottage().findLastCottageId();
    }

    public void changeStatusOfReservation(int id,String status) throws ExceptionWithMessage {
        var reservationOptional = data.getReservation().findById(id);
        if (reservationOptional.isPresent()) {
            Reservation res = reservationOptional.get();
            res.setStatus(status);
            data.getReservation().save(res);
        }
        else {
            throw new ExceptionWithMessage("Reservation not found");
        }
    }
}
