package com.backend.iffatv.repository.movie;

import com.backend.iffatv.model.movie.Country;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    Optional<Country> findCountryById(int id);

    Optional<Country> findCountryByName(String name);
}
