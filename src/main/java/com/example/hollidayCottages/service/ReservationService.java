package com.example.hollidayCottages.service;

import com.example.hollidayCottages.entity.Reservation;
import com.example.hollidayCottages.infrastructure.dto.request.reservation.ReservationRequest;
import com.example.hollidayCottages.infrastructure.dto.request.reservation.UpdateReservationRequest;
import com.example.hollidayCottages.infrastructure.model.ReservationDto;
import com.example.hollidayCottages.repository.ReservationRepository;
import javassist.NotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    ReservationDto createReservation(LocalDate start_date, LocalDate end_date, int price
    , int number_of_persons){
        Reservation reservation = new Reservation();
        reservation.setStart_date(start_date);
        reservation.setEnd_date(end_date);
        reservation.setPrice(price);
        reservation.setNumber_of_persons(number_of_persons);
        Reservation saveReservation = reservationRepository.save(reservation);
        return mapReservationToDto(saveReservation);
    }
        ReservationDto mapReservationToDto(Reservation reservation) {
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setReservationId(Math.toIntExact(reservation.getReservationId()));
        reservationDto.setStart_date(reservation.getStart_date());
        reservationDto.setEnd_date(reservation.getEnd_date());
        reservationDto.setPrice(reservation.getPrice());
        reservationDto.setNumber_of_persons(reservation.getNumber_of_persons());
        reservationDto.setCustomer_id(reservation.getCustomer_id());
        reservationDto.setCottage_id(reservation.getCottage_id());
        reservationDto.setStatus(reservation.isStatus());

        return reservationDto;
    }
    void updateReservation(Long reservationId, UpdateReservationRequest request) throws NotFoundException {
        Reservation reservationToUpdate = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new NotFoundException("Reservation not found"));
        reservationToUpdate.setStart_date(request.getStartDate());
        reservationToUpdate.setEnd_date(request.getEndDate());
        reservationToUpdate.setNumber_of_persons(request.getNumberOfPersons());
        reservationToUpdate.setCustomer_id((long) Math.toIntExact(request.getCustomerId()));
        reservationToUpdate.setCottage_id((long) Math.toIntExact(request.getCottageId()));
        reservationToUpdate.setStatus(request.isNewStatus());

        reservationRepository.save(reservationToUpdate);


    }
    void cancelReservation(Long reservationId) throws NotFoundException {
        Reservation reservationCancel = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new NotFoundException("Reservation not found"));
        reservationCancel.setStatus(false);
        reservationRepository.save(reservationCancel);

    }
    ReservationDto getReservationById(Long reservationId) throws NotFoundException {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(()-> new NotFoundException("Reservation not found"));
        return mapReservationToDto(reservation);


    }
    List<ReservationDto> getAllReservation(Long customerId) throws NotFoundException {

        List<Reservation> reservations = reservationRepository.findByCustomerId(customerId);
        if(reservations.isEmpty()){
            throw new NotFoundException("No reservations found for customer with ID:" + customerId);
        }
        List<ReservationDto> reservationDtos = reservations.stream()
                .map(this::mapReservationToDto)
                .collect(Collectors.toList());

        return reservationDtos;
    }
    int calculateReservationPrice(ReservationRequest request){
        LocalDate startDate = request.getStartDate();
        LocalDate endDate = request.getEndDate();
        int numberOfPersons = request.getNumberOfPersons();

        int basePrice = calculateBasePrice(startDate, endDate);
        int additionalPersonPrice = calculateAdditionalPersonPrice(numberOfPersons);

        int totalPrice = basePrice + additionalPersonPrice;
        return totalPrice;
    }

    private int calculateAdditionalPersonPrice(int numberOfPersons) {
        return numberOfPersons;
    }

    private int calculateBasePrice(LocalDate startDate, LocalDate endDate) {
        return 0;
    }

    boolean validateReservation(ReservationRequest request){
        LocalDate startDate = request.getStartDate();
        LocalDate endDate = request.getEndDate();
        int numberOfPersons = request.getNumberOfPersons();

        if (startDate == null || endDate == null || startDate.isAfter(endDate)) {
            return false;
        }
        if (numberOfPersons <= 0) {
            return false;
        }
        return true;
    }
    void updateReservationStatus(Long reservationId, UpdateReservationRequest updateReservationRequest) throws NotFoundException {
        Reservation reservationToUpdate = reservationRepository.findById((long) reservationId)
                .orElseThrow(() ->  new NotFoundException("Reservation not found"));
        reservationToUpdate.setStatus(updateReservationRequest.isNewStatus());
        reservationRepository.save(reservationToUpdate);

    }


}
