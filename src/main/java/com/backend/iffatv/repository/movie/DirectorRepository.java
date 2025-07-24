package com.backend.iffatv.repository.movie;

import com.backend.iffatv.model.movie.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Long> {

    Optional<Director> findDirectorById(Long id);

    Optional<Director> findDirectorByNameIgnoreCase(String name);

}
