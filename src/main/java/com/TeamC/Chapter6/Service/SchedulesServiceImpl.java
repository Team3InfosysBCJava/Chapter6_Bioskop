package com.TeamC.Chapter6.Service;


import com.TeamC.Chapter6.Helper.ExceptionHandler;
import com.TeamC.Chapter6.Model.Schedules;
import com.TeamC.Chapter6.Repository.SchedulesRepo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SchedulesServiceImpl implements SchedulesService{

    private SchedulesRepo SchedulesRepo;

    public static Integer currentPage;

    //Get All
    public List<Schedules> getAll(){
        List<Schedules> optionalSchedules = this.SchedulesRepo.findAll();
        if(optionalSchedules.isEmpty()){
            throw new ExceptionHandler("Schedules not exist");
        }
        return this.SchedulesRepo.findAll();
    }

    //Get By ID
    public Optional<Schedules> getSchedulesById(Integer Id) throws ExceptionHandler{
        Optional<Schedules> optionalSchedules = this.SchedulesRepo.findById(Id);
        if(optionalSchedules.isEmpty()){
            throw new ExceptionHandler("Schedules not exist with id : " + Id);
        }
        return this.SchedulesRepo.findById(Id);
    }

    //Post
    public Schedules createSchedules(Schedules Schedules){
        return this.SchedulesRepo.save(Schedules);
    }

    //Update
    @Override
    public Schedules updateSchedules(Schedules Schedules) {
        Optional<Schedules> optionalSchedules = SchedulesRepo.findById(Schedules.getScheduleId());
        if(optionalSchedules.isEmpty()){
            throw new ExceptionHandler("User not exist with id : " + Schedules.getScheduleId());
        }
        return this.SchedulesRepo.save(Schedules);
    }

    //Get Reference By Id
    public Schedules getReferenceById (Integer id) {
        return this.SchedulesRepo.getReferenceById(id);
    }

    //Delete
    public void deleteSchedulesById(Integer Id){
        Optional<Schedules> optionalSchedules = SchedulesRepo.findById(Id);
        if(optionalSchedules.isEmpty()){
            throw new ExceptionHandler("Schedules not exist with id : " + Id);
        }
            Schedules Schedules = SchedulesRepo.getReferenceById(Id);
        this.SchedulesRepo.delete(Schedules);
    }

    //Custom Select
    @Override
    public List<Schedules> getSchedulesByFilmName(String name) {
        List<Schedules> getSchedulesByFilmName = this.SchedulesRepo.getSchedulesByFilmName(name);
        if (getSchedulesByFilmName.isEmpty()){
            throw new ExceptionHandler("Schedules with film name " + name + " is not exist!!!!!");
        }
        return this.SchedulesRepo.getSchedulesByFilmName(name);
    }

    @Override
    public List<Schedules> getSchedulesByFilmNameLike(String name) {
        List<Schedules> getSchedulesByFilmNameLike = this.SchedulesRepo.getSchedulesFilmsNameLike(name);
        if (getSchedulesByFilmNameLike.isEmpty()){
            throw new ExceptionHandler("Schedules with film name " + name + " is not exist!!!!!");
        }
        return this.SchedulesRepo.getSchedulesFilmsNameLike(name);
    }

    @Override
    public Page<Schedules> search(String keyword, Integer page){

        if (keyword != null){
            return SchedulesRepo.searchByName(keyword, null);
        } else if (page == null){
            return SchedulesRepo.findAll(PageRequest.of(0, 10, Sort.by("films.name")));
        } else {
            return SchedulesRepo.findAll(PageRequest.of(page, 10, Sort.by("films.name")));
        }
    }

    public Integer pageUpdate(String page) {

        //container
        Integer pageNumber = null;

        //check params
        if(page.equals("prev")){
            currentPage--;
        }
        else if(page.equals("next")){
            currentPage++;
        } else {
            currentPage = Integer.parseInt(page);
        }

        if(currentPage == 0){
            currentPage = 1;
        }

        //page in bootstrap template starts from 0
        pageNumber = currentPage-1;

        return pageNumber;
    }
}
