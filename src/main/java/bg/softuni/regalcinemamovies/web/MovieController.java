package bg.softuni.regalcinemamovies.web;

import bg.softuni.regalcinemamovies.model.dto.AddMovieDto;
import bg.softuni.regalcinemamovies.model.dto.MovieDto;
import bg.softuni.regalcinemamovies.service.impl.MovieServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieServiceImpl movieService;

    public MovieController(MovieServiceImpl movieService) {
        this.movieService = movieService;
    }

    @PostMapping("/add")
    public ResponseEntity<MovieDto> add(@RequestBody AddMovieDto addMovieDto){

        if (addMovieDto == null) {
            return ResponseEntity.notFound().build();
        }

        MovieDto addedMovie = this.movieService.add(addMovieDto);

        return ResponseEntity.created(
                ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(addedMovie.getId())
                .toUri()
        ).body(addedMovie);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDto> getById(@PathVariable Long id){

        MovieDto infoDto = this.movieService.getById(id);

        if (infoDto == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(infoDto);
    }

    @GetMapping("/title={title}")
    public ResponseEntity<MovieDto> getByTitle(@PathVariable String title){

        title = title.replace("+", " ");

        MovieDto infoDto = this.movieService.getByTitle(title);

        if (infoDto == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(infoDto);
    }

    @GetMapping("/all")
    public ResponseEntity<List<MovieDto>> getAll(){

        List<MovieDto> all = this.movieService.getAll();

        return ResponseEntity.ok(all);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MovieDto> deleteById(@PathVariable Long id) {

        this.movieService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
