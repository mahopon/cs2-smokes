package com.mahopon.cs2_smokes.lineup.internal.repository;

import com.mahopon.cs2_smokes.lineup.internal.model.Lineup;
import com.mahopon.cs2_smokes.map.api.model.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LineupRepository extends JpaRepository<Lineup, Long> {
    @Query(value = "SELECT  from lineups where map.id = :mapId", nativeQuery = true)
    List<Lineup> findByMap(int mapId);
}
