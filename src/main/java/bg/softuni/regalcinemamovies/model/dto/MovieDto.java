package bg.softuni.regalcinemamovies.model.dto;

import bg.softuni.regalcinemamovies.model.enums.Audio;
import bg.softuni.regalcinemamovies.model.enums.Genre;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class MovieDto {

    private long id;

    @NotBlank
    @Size(min = 2, max = 50)
    private String title;

    @NotBlank
    @Size(min = 3, max = 50)
    private String directorName;

    @Size(min = 10, max = 100)
    private String actors;

    @NotBlank
    @Size(min = 20, max = 200)
    private String description;

    @Positive
    private int lengthInMinutes;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate premiere;

    @Size(min = 10, max = 200)
    private String videoUrl;

    @Size(min = 10, max = 200)
    private String imageUrl;

    private Audio audio;

    private Genre genre;

    public MovieDto() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public int getLengthInMinutes() {
        return lengthInMinutes;
    }

    public void setLengthInMinutes(int lengthInMinutes) {
        this.lengthInMinutes = lengthInMinutes;
    }

    public LocalDate getPremiere() {
        return premiere;
    }

    public void setPremiere(LocalDate premiere) {
        this.premiere = premiere;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public Audio getAudio() {
        return audio;
    }

    public void setAudio(Audio audio) {
        this.audio = audio;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
