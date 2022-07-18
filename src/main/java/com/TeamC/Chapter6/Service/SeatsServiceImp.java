package com.TeamC.Chapter6.Service;

import com.TeamC.Chapter6.Model.Seats;
import com.TeamC.Chapter6.Repository.SeatsRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.print.attribute.standard.PageRanges;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SeatsServiceImp implements SeatsService {

    private final SeatsRepository seatsRepository;
    public static Integer currentPage;

    @Override
    public List<Seats> findAll() {
        return seatsRepository.findAll();
    }

    @Override
    public Optional<Seats> findById(Long id) {
        return seatsRepository.findById(id);
    }

    @Override
    public Seats getReferenceById(Long id) {
        return seatsRepository.getReferenceById(id);
    }

    @Override
    public Seats createSeat(Seats seats) {
        return seatsRepository.save(seats);
    }

    @Override
    public Seats updateSeat(Seats seats) {
        return seatsRepository.save(seats);
    }

    @Override
    public void deleteSeat(Long id) {
        seatsRepository.deleteAllById(Collections.singleton(id));
    }

    @Override
    public List<Seats> getSeatsAvailable(Boolean isAvailable) {
        return seatsRepository.getSeatAvailable(isAvailable);
    }

    @Override
    public Page<Seats> search(String keyword, Integer page) {
        if(keyword != null){
            return seatsRepository.Search(keyword,null);
        }else if(page==null){
            return seatsRepository.findAll(PageRequest.of(0,10, Sort.by("studioName")));
        }else {
            return seatsRepository.findAll(PageRequest.of(page,10,Sort.by("studioName")));
        }
    }

    @Override
    public Integer pageUpdate(String page) {
        Integer pageNumber = null;
        if(page.equals("prev")){
            currentPage--;
        }else if(page.equals("next")){
            currentPage++;
        }else {
            currentPage = Integer.parseInt(page);
        }
        if(currentPage==0){
            currentPage = 1;
        }
        //page in bootstrap template starts from 0
        pageNumber = currentPage -1;

        return pageNumber;
    }
}
