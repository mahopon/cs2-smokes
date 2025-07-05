package com.mahopon.cs2_smokes.auth;

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

@Table(name = "auth", indexes={
    @Index(name = "idx_email", columnList = "email")
})
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Auth {
    @GeneratedValue(strategy=GenerationType.UUID)
    @Id
    private UUID id;
    @Column(nullable=false)
    private String email;
    @Column(nullable=false)
    private String hashedPassword;
    private LocalDateTime lastLogin;
    private LocalDateTime lastUpdate;
}
