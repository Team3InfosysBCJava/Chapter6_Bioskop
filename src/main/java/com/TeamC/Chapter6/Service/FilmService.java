package com.TeamC.Chapter6.Service;

import com.TeamC.Chapter6.Helper.ResourceNotFoundException;
import com.TeamC.Chapter6.Model.Film;
import com.TeamC.Chapter6.Repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FilmService {

    @Autowired
    public FilmRepository filmRepository;

    //CREATE
    public Film addFilm(Film film){
        return filmRepository.save(film);
    }

    //READ
    public List<Film> findAllFilm(){
        List<Film> films = filmRepository.findAll();
        if(films.isEmpty()){
            throw new ResourceNotFoundException("table film is empty");
        } else {
            return films;
        }
    }

    //GET BY ID
    public Optional<Film> findFilmById(Long id){
        return filmRepository.findById(id);
    }

    public Film getReferenceById (Long id) {
        return this.filmRepository.getReferenceById(id);
    }

    //GET BY NAME
    public List<Film> findFilmByName(String name){
        List<Film> filmByName = filmRepository.getFilmByName(name);
        if(filmByName.isEmpty()){
            return filmRepository.findAll();
        } else {
            return filmRepository.getFilmByName(name);
        }
    }

    //UPDATE
    public Film updateFilm(Film film){
        return filmRepository.save(film);
    }
    //DELETE
    public void deleteFilm(Long filmid){
        filmRepository.deleteById(filmid);
    }

    //get by isPlayinh
    public List<Film> getFilmByStatus(Boolean isPlaying){
        if(isPlaying == null){
            return filmRepository.findAll();
        } else {
            return filmRepository.getFilmByStatus(isPlaying);
        }
    }
}
