package com.TeamC.Chapter6.Service;

import com.TeamC.Chapter6.Model.Schedules;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface SchedulesService {
        List<Schedules> getAll();
        Optional<Schedules> getSchedulesById(Integer Id);
        Schedules createSchedules(Schedules Schedules);
        void deleteSchedulesById(Integer Id);
        Schedules updateSchedules(Schedules Schedules);
        Schedules getReferenceById (Integer id);
        List<Schedules> getSchedulesByFilmName(String name);
        List<Schedules> getSchedulesByFilmNameLike(String name);
        Page<Schedules> search(String name, Integer page);

}
