package com.example.hollidayCottages.service;

import com.example.hollidayCottages.Exceptions.ExceptionWithMessage;
import com.example.hollidayCottages.model.Cottage;
import com.example.hollidayCottages.repositories.ICatalogData;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CottagePageService {
    private final ICatalogData data;
    public int getLastId() {
        return data.getCottage().findLastCottageId();
    }
    public Cottage getCottage(int id) throws ExceptionWithMessage {
        Optional<Cottage> cottage = data.getCottage().findById(id);
       if(cottage.isPresent()){
           return cottage.get();
       }
       throw new ExceptionWithMessage("Nie znaleziono domu");
    }
}
