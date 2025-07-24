package com.backend.iffatv.model.movie;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String name;

    @Column(length = 1500)
    private String description;

    @Column(name = "portrait_poster")
    private String portraitPoster;

    @Column(name = "landscape_poster")
    private String landscapePoster;

    private String youtubeTrailer;

    private String videoPath;

    private Date releaseDate;

    private int releaseYear;

    @ManyToMany
    private List<Director> directors;

    @ManyToMany
    private List<Cast> casts;

    @ManyToMany
    private List<Country> countries;

    @ManyToMany
    private List<Language> languages;

    @ManyToMany
    private List<Genre> genres;

    private Long likes = 0L;

    private Long dislikes = 0L;

    private Long views = 0L;

    private boolean featured = false;

    @UpdateTimestamp
    private LocalDate updatedAt;

}
