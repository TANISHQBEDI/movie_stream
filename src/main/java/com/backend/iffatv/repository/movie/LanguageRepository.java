package com.backend.iffatv.repository.movie;

import com.backend.iffatv.model.movie.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Long> {

    Optional<Language> findLanguageById(Long id);

    Optional<Language> findLanguageByNameIgnoreCase(String name);

}
