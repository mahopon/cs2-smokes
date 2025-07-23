package com.mahopon.cs2_smokes.lineup.internal.model;

import com.mahopon.cs2_smokes.map.api.model.Location;
import com.mahopon.cs2_smokes.map.api.model.Map;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "lineups")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lineup {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Location from;

    @ManyToOne
    private Location to;

    @NotNull
    @NotEmpty
    private String videoUrl;

    @Null
    private String imageUrl;

    @NotNull
    @CreationTimestamp
    private LocalDateTime createdAt;

    @ManyToOne
    private Map map;
}
