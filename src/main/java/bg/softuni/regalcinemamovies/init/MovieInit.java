package bg.softuni.regalcinemamovies.init;

import bg.softuni.regalcinemamovies.service.impl.MovieServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MovieInit implements CommandLineRunner {

    private final MovieServiceImpl movieService;

    public MovieInit(MovieServiceImpl movieService) {
        this.movieService = movieService;
    }

    @Override
    public void run(String... args) throws Exception {

        this.movieService.init();
    }
}
