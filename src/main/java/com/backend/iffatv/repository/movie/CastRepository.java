package com.backend.iffatv.repository.movie;

import com.backend.iffatv.model.movie.Cast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CastRepository extends JpaRepository<Cast, Long> {

    Optional<Cast> findCastById(Long id);

    Optional<Cast> getCastByNameIgnoreCase(String name);
}