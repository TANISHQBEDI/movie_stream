package com.backend.iffatv.repository.movie;

import com.backend.iffatv.model.movie.*;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    Optional<Movie> findMovieById(long id);

    Optional<Movie> findMovieByName(String name);

    List<Movie> findMovieByReleaseYear(int year);

    List<Movie> findMovieByGenres(List<Genre> genres);

    List<Movie> findMovieByDirectors(List<Director> directors);

    List<Movie> findByCastsIn(List<Cast> casts);

    List<Movie> findByCastsContains(Cast cast);

    List<Movie> findMovieByLanguages(List<Language> languages);

    List<Movie> findMovieByCountries(List<Country> countries);

    List<Movie> findMovieByLikesBetween(Long likesAfter, Long likesBefore);

}
