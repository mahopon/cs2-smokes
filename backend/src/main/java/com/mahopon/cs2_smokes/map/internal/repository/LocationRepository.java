package com.mahopon.cs2_smokes.map.internal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mahopon.cs2_smokes.map.api.model.Location;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Integer> {
    @Query("SELECT * from locations WHERE id = :location1Id or id = :location2Id")
    List<Location> findTwoLocationIds(long location1Id, long location2Id);
}
