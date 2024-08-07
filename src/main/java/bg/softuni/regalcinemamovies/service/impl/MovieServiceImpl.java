package bg.softuni.regalcinemamovies.service.impl;

import bg.softuni.regalcinemamovies.model.Movie;
import bg.softuni.regalcinemamovies.model.dto.AddMovieDto;
import bg.softuni.regalcinemamovies.model.dto.MovieDto;
import bg.softuni.regalcinemamovies.model.enums.Audio;
import bg.softuni.regalcinemamovies.model.enums.Genre;
import bg.softuni.regalcinemamovies.repo.MovieRepository;
import bg.softuni.regalcinemamovies.service.MovieService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    private static final List<Movie> MOVIE_LIST = List.of(
            new Movie("Home Alone",
                    "Chris Columbus",
                    "Macaulay Culkin, Joe Pesci, Daniel Stern",
                    "An eight-year-old troublemaker, mistakenly left home alone, must defend his home against a pair of burglars on Christmas Eve.",
                    103,
                    LocalDate.of(1996, 11, 16),
                    "https://m.media-amazon.com/images/M/MV5BMzFkM2YwOTQtYzk2Mi00N2VlLWE3NTItN2YwNDg1YmY0ZDNmXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_.jpg",
                    Audio.BULGARIAN,
                    Genre.COMEDY
            ),
            new Movie("Jurassic Park",
                    "Steven Spielberg",
                    "Sam Neill, Laura Dern, Jeff Goldblum",
                    "An industrialist invites some experts to visit his theme park of cloned dinosaurs. After a power failure, the creatures run loose, putting everyone's lives, including his grandchildren's, in danger.",
                    127,
                    LocalDate.of(1993, 5, 2),
                    "https://i.ebayimg.com/images/g/-ggAAOSwACNgsOd~/s-l1600.jpg",
                    Audio.BULGARIAN,
                    Genre.ACTION
            ),
            new Movie("Jumanji",
                    "Joe Johnston",
                    "Robin Williams, Kirsten Dunst, Bonnie Hunt",
                    "When two kids find and play a magical board game, they release a man trapped in it for decades - and a host of dangers that can only be stopped by finishing the game.",
                    104,
                    LocalDate.of(1995, 8, 29),
                    "https://m.media-amazon.com/images/I/91PgRrknlJL._AC_UF894,1000_QL80_.jpg",
                    Audio.BULGARIAN,
                    Genre.FANTASY
            ),
            new Movie("Godzilla",
                    "Roland Emmerich",
                    "Matthew Broderick, Jean Reno, Maria Pitillo",
                    "French nuclear tests irradiate an iguana into a giant monster that heads off to New York City. The American military must chase the monster across the city to stop it before it reproduces.",
                    139,
                    LocalDate.of(1998, 3, 13),
                    "https://m.media-amazon.com/images/M/MV5BNjFlOTI2OGQtMzg0YS00ZGE4LTkwMjEtZDUzYThlOTU5YjQ5XkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_FMjpg_UX1000_.jpg",
                    Audio.ENGLISH,
                    Genre.ACTION
            )
    );

    private final Logger LOGGER = LoggerFactory.getLogger(MovieServiceImpl.class);
    private final MovieRepository movieRepository;
    private final ModelMapper modelMapper;

    public MovieServiceImpl(MovieRepository movieRepository, ModelMapper modelMapper) {
        this.movieRepository = movieRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public void init() {

        if (this.movieRepository.count() < 1) {
            this.movieRepository.saveAll(MOVIE_LIST);
        }
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
