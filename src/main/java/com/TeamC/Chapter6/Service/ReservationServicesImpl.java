package com.TeamC.Chapter6.Service;

import com.TeamC.Chapter6.Helper.ResourceNotFoundException;
import com.TeamC.Chapter6.Model.Reservation;
import com.TeamC.Chapter6.Repository.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReservationServicesImpl implements ReservationServices{
    private ReservationRepository ReservationRepository;
    public static Integer currentPage;

    @Override
    public List<Reservation> getAll() {
        List<Reservation> optionalReservation = ReservationRepository.findAll();
        if(optionalReservation.isEmpty()){
            throw new ResourceNotFoundException("Data Reservation not exist");
        }
        return this.ReservationRepository.findAll();
    }

    @Override
    public Optional<Reservation> getReservationById(Long Id) throws ResourceNotFoundException {
        Optional<Reservation> optionalReservation = ReservationRepository.findById(Id);
        if(optionalReservation.isEmpty()){
            throw new ResourceNotFoundException("Reservation not exist with id " + Id);
        }
        return this.ReservationRepository.findById(Id);
    }

    @Override
    public Reservation updateReservation(Reservation Reservation) throws ResourceNotFoundException {
        Optional<Reservation> optionalReservation = ReservationRepository.findById(Reservation.getReservationId());
        if(optionalReservation.isEmpty()){
            throw new ResourceNotFoundException("Reservation not exist with id " + Reservation.getReservationId());
        }
        return this.ReservationRepository.save(Reservation);
    }

    @Override
    public Reservation getReferenceById(Long Id) {
        return this.ReservationRepository.getReferenceById(Id);
    }

    @Override
    public Reservation createReservation(Reservation Reservation) {
        return this.ReservationRepository.save(Reservation);
    }

    @Override
    public void deleteSReservationById(Long Id) throws ResourceNotFoundException{
        Optional<Reservation> optionalReservation = ReservationRepository.findById(Id);
        if(optionalReservation.isEmpty()){
            throw new ResourceNotFoundException("Reservation not exist with id " + Id);
        }
        Reservation Reservation = ReservationRepository.getReferenceById(Id);
        this.ReservationRepository.delete(Reservation);
    }

    //custom select
    @Override
    public List<Reservation> getReservationByFilmName(String name) {
        List<Reservation> optionalReservation = ReservationRepository.getReservationByFilmName(name);
        if(optionalReservation.isEmpty()){
            throw new ResourceNotFoundException("Reservation not exist with Filmname " +name);
        }
        return this.ReservationRepository.getReservationByFilmName(name);
    }

    @Override
    public Page<Reservation> getReservationFilm(String name, String page) {
        Integer pageNumber = null;

        //check null pointer
        if(page != null){
            pageNumber = pageUpdate(page);
        }
        if(name != null){
            return ReservationRepository.getReservationFilm(name, null);
        }else if(page == null){
            return ReservationRepository.findAll(PageRequest.of(0,10, Sort.by("schedule.films.name")));
        }else{
            return ReservationRepository.findAll(PageRequest.of(pageNumber,10,Sort.by("schedule.films.name")));
        }
    }

    @Override
    public Page<Reservation> getReservationId(Long id, String page) {
        Integer pageNumber = null;

        //check null pointer
        if(page != null){
            pageNumber = pageUpdate(page);
        }
        if(id != null){
            return ReservationRepository.getReservationId(id, null);
        }else if(page == null){
            return ReservationRepository.findAll(PageRequest.of(0,10,Sort.by("reservationId")));
        }else{
            return ReservationRepository.findAll(PageRequest.of(pageNumber,10,Sort.by("reservationId")));
        }
    }


    @Override
    public Page<Reservation> search(String keyword, String page) {
        Integer pageNumber = null;
        if(page != null){
            pageNumber = pageUpdate(page);
        }
        if(keyword != null){
            return ReservationRepository.search(keyword,null);
        }else if(page == null){
            return ReservationRepository.findAll(PageRequest.of(0,10,Sort.by("reservationId")));
        }else{
            return ReservationRepository.findAll(PageRequest.of(pageNumber,10,Sort.by("reservationId")));
        }
    }

    @Override
    public Integer pageUpdate(String page) {

        //container
        Integer pageNumber = null;

        //check params
        if(page.equals("prev")){
            currentPage--;
        }
        else if(page.equals("next")){
            currentPage++;
        } else{
            currentPage = Integer.parseInt(page);
        }

        if(currentPage == 0){
            currentPage = 1;
        }
        //page in bootstrap template starts from 0
        pageNumber = currentPage-1;
        return pageNumber;
    }

    @Override
    public Page<Reservation> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.ReservationRepository.findAll(pageable);
    }
}
