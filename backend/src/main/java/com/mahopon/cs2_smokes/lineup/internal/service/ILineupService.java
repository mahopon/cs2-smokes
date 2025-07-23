package com.mahopon.cs2_smokes.lineup.internal.service;

import com.mahopon.cs2_smokes.lineup.internal.model.Lineup;
import com.mahopon.cs2_smokes.lineup.internal.model.dto.NewLineupRequestDTO;

import java.util.List;

public interface ILineupService {
    List<Lineup> getAllLineups();
    List<Lineup> getLineupByMap(int mapId);
    Lineup getLineup(long lineupId);
    void createLineup(NewLineupRequestDTO dto) throws Exception;
    void deleteLineup(long lineupId);
}
