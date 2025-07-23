package com.mahopon.cs2_smokes.auth.internal.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mahopon.cs2_smokes.auth.api.model.Auth;

@Repository
public interface AuthRepository extends CrudRepository<Auth, UUID>{
    Optional<Auth> findByEmail(String email);
}
