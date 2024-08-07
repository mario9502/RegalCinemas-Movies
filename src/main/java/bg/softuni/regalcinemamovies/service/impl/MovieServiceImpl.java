package bg.softuni.regalcinemamovies.service.impl;

import bg.softuni.regalcinemamovies.model.Movie;
import bg.softuni.regalcinemamovies.model.dto.AddMovieDto;
import bg.softuni.regalcinemamovies.model.dto.MovieDto;
import bg.softuni.regalcinemamovies.repo.MovieRepository;
import bg.softuni.regalcinemamovies.service.MovieService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    private final Logger LOGGER = LoggerFactory.getLogger(MovieServiceImpl.class);
    private final MovieRepository movieRepository;
    private final ModelMapper modelMapper;

    public MovieServiceImpl(MovieRepository movieRepository, ModelMapper modelMapper) {
        this.movieRepository = movieRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public MovieDto add(AddMovieDto addMovieDto) {

        if (movieRepository.existsByTitle(addMovieDto.getTitle())) {
            return null;
        }

        Movie mappedMovie = modelMapper.map(addMovieDto, Movie.class);

        Movie savedMovie = movieRepository.save(mappedMovie);

        return modelMapper.map(savedMovie, MovieDto.class);
    }

    @Override
    public MovieDto getById(Long id) {
        Optional<Movie> optionalMovie = this.movieRepository.findById(id);

        return optionalMovie.map(movie -> modelMapper.map(movie, MovieDto.class)).orElse(null);

    }

    @Override
    public MovieDto getByTitle(String title) {

        Optional<Movie> optionalMovie = this.movieRepository.findByTitle(title);

        return optionalMovie.map(movie -> modelMapper.map(movie, MovieDto.class)).orElse(null);
    }

    @Override
    public List<MovieDto> getAll() {

        return this.movieRepository.findAll()
        .stream()
        .map(movie -> this.modelMapper.map(movie, MovieDto.class))
        .toList();
    }

    @Override
    public void deleteById(Long id) {

        this.movieRepository.deleteById(id);
    }
}
