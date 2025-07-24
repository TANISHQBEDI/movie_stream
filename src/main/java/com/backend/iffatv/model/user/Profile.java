package com.backend.iffatv.model.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Profile {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String avatarUrl;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

//    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<WatchHistory> watchHistory;
//
//    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<LikedContent> likedContent;

}
