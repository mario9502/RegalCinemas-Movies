package bg.softuni.regalcinemamovies.service;

import bg.softuni.regalcinemamovies.model.dto.AddMovieDto;
import bg.softuni.regalcinemamovies.model.dto.MovieDto;

import java.util.List;

public interface MovieService {

    void init();

    MovieDto add(AddMovieDto addMovieDto);

    MovieDto getById(Long id);

    List<MovieDto> getAll();

    void deleteById(Long id);

    MovieDto getByTitle(String title);
}
