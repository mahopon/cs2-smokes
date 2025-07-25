package com.mahopon.cs2_smokes.auth.api.model;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

@Table(name = "auth", indexes={
    @Index(name = "idx_email", columnList = "email")
})
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Auth {
    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    private UUID id;
    @Column(nullable=false)
    private String email;
    private LocalDateTime lastLogin;
    @UpdateTimestamp
    private LocalDateTime lastUpdate;
}
