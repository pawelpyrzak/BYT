package com.example.hollidayCottages.service;

import com.example.hollidayCottages.Exceptions.ExceptionWithMessage;
import com.example.hollidayCottages.model.Cottage;
import com.example.hollidayCottages.repositories.ICatalogData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MainService {
    private final ICatalogData data;

    public List<Cottage> getCottages(String start, String end, int numberOfPeople) throws ExceptionWithMessage {
        Date startDate = Date.valueOf(start);
        Date endDate = Date.valueOf(end);
        long daysDiff = ChronoUnit.DAYS.between(startDate.toLocalDate(), endDate.toLocalDate());
        if (daysDiff < 1)
            throw new ExceptionWithMessage("Za krótki okres, minimum 1 doba");
        else if (daysDiff > 14)
            throw new ExceptionWithMessage("Za długi okres, maksymalnie 14 dni");
        List<Cottage> list = new ArrayList<>();
        for (Cottage cottage : data.getCottage().findHousesByNumberOfPerson(numberOfPeople)) {
            if (data.getReservation().findByStartDateBetweenAndCottageId(startDate, endDate, cottage.getId()).isEmpty())
                list.add(cottage);
        }
        return list;
    }
}
