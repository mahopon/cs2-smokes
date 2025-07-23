package com.mahopon.cs2_smokes.lineup.internal.service.impl;

import com.mahopon.cs2_smokes.lineup.internal.model.Lineup;
import com.mahopon.cs2_smokes.map.api.model.Location;
import com.mahopon.cs2_smokes.lineup.internal.model.dto.NewLineupRequestDTO;
import com.mahopon.cs2_smokes.lineup.internal.repository.LineupRepository;
import com.mahopon.cs2_smokes.lineup.internal.service.ILineupService;
import com.mahopon.cs2_smokes.map.api.model.Map;
import com.mahopon.cs2_smokes.map.api.service.IMapAPI;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("LineupService")
public class LineupService implements ILineupService {

    private final LineupRepository lineupRepository;
    private final IMapAPI mapService;

    public LineupService(LineupRepository lineupRepository, IMapAPI mapService) {
        this.lineupRepository = lineupRepository;
        this.mapService = mapService;
    }

    @Override
    public List<Lineup> getAllLineups() {
        return lineupRepository.findAll();
    }

    @Override
    public List<Lineup> getLineupByMap(int mapId) {
        return lineupRepository.findByMap(mapId);
    }

    @Override
    public Lineup getLineup(long lineupId) {
        return lineupRepository.findById(lineupId).get();
    }

    @Override
    public void createLineup(NewLineupRequestDTO dto) throws Exception {
        long locTo = dto.getFromLocId();
        long locFrom = dto.getToLocId();
        if (locTo == locFrom) {
            throw new Exception("From and To cannot be the same");
        }
        List<Location> locs = mapService.getLocations(dto.getFromLocId(), dto.getToLocId());
        Map map = mapService.getMap(dto.getMapId());
        Lineup newLineup = Lineup.builder()
                .from(locs.get(0))
                .to(locs.get(1))
                .map(map)
                .imageUrl(dto.getImageUrl())
                .videoUrl(dto.getVideoUrl())
                .build();
        lineupRepository.save(newLineup);
    }

    @Override
    public void deleteLineup(long lineupId) {
        lineupRepository.deleteById(lineupId);
    }
}
