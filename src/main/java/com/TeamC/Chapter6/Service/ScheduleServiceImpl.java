package com.TeamC.Chapter6.Service;


import com.TeamC.Chapter6.Helper.ExceptionHandler;
import com.TeamC.Chapter6.Model.Schedule;
import com.TeamC.Chapter6.Repository.ScheduleRepo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ScheduleServiceImpl implements ScheduleService{

    private ScheduleRepo scheduleRepo;

    public static Integer currentPage;

    //Get All
    public List<Schedule> getAll(){
        List<Schedule> optionalSchedule = this.scheduleRepo.findAll();
        if(optionalSchedule.isEmpty()){
            throw new ExceptionHandler("Schedule not exist");
        }
        return this.scheduleRepo.findAll();
    }

    //Get By ID
    public Optional<Schedule> getScheduleById(Integer Id) throws ExceptionHandler{
        Optional<Schedule> optionalSchedule = this.scheduleRepo.findById(Id);
        if(optionalSchedule.isEmpty()){
            throw new ExceptionHandler("Schedule not exist with id : " + Id);
        }
        return this.scheduleRepo.findById(Id);
    }

    //Post
    public Schedule createSchedule(Schedule schedule){
        return this.scheduleRepo.save(schedule);
    }

    //Update
    @Override
    public Schedule updateSchedule(Schedule schedule) {
        Optional<Schedule> optionalSchedule = scheduleRepo.findById(schedule.getScheduleId());
        if(optionalSchedule.isEmpty()){
            throw new ExceptionHandler("User not exist with id : " + schedule.getScheduleId());
        }
        return this.scheduleRepo.save(schedule);
    }

    //Get Reference By Id
    public Schedule getReferenceById (Integer id) {
        return this.scheduleRepo.getReferenceById(id);
    }

    //Delete
    public void deleteScheduleById(Integer Id){
        Optional<Schedule> optionalSchedule = scheduleRepo.findById(Id);
        if(optionalSchedule.isEmpty()){
            throw new ExceptionHandler("Schedule not exist with id : " + Id);
        }
            Schedule schedule = scheduleRepo.getReferenceById(Id);
        this.scheduleRepo.delete(schedule);
    }

    //Custom Select
    @Override
    public List<Schedule> getScheduleByFilmName(String name) {
        List<Schedule> getScheduleByFilmName = this.scheduleRepo.getScheduleByFilmName(name);
        if (getScheduleByFilmName.isEmpty()){
            throw new ExceptionHandler("Schedule with film name " + name + " is not exist!!!!!");
        }
        return this.scheduleRepo.getScheduleByFilmName(name);
    }

    @Override
    public List<Schedule> getScheduleByFilmNameLike(String name) {
        List<Schedule> getScheduleByFilmNameLike = this.scheduleRepo.getScheduleFilmsNameLike(name);
        if (getScheduleByFilmNameLike.isEmpty()){
            throw new ExceptionHandler("Schedule with film name " + name + " is not exist!!!!!");
        }
        return this.scheduleRepo.getScheduleFilmsNameLike(name);
    }

    @Override
    public Page<Schedule> search(String keyword, Integer page){

        if (keyword != null){
            return scheduleRepo.searchByName(keyword, null);
        } else if (page == null){
            return scheduleRepo.findAll(PageRequest.of(0, 10, Sort.by("films.name")));
        } else {
            return scheduleRepo.findAll(PageRequest.of(page, 10, Sort.by("films.name")));
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
