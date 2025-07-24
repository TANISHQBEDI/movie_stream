package com.backend.iffatv.repository.movie;

import com.backend.iffatv.model.movie.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

    Optional<Genre> findGenreById(int id);

    Optional<Genre> findGenreByNameIgnoreCase(String name);

    Optional<Genre> findByName(String name);
}
