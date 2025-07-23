package com.mahopon.cs2_smokes.user.internal.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mahopon.cs2_smokes.user.internal.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {
}
