package com.mahopon.cs2_smokes.map.internal.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.mahopon.cs2_smokes.map.api.model.Location;
import com.mahopon.cs2_smokes.map.internal.repository.LocationRepository;
import org.springframework.stereotype.Service;

import com.mahopon.cs2_smokes.map.api.model.Map;
import com.mahopon.cs2_smokes.map.internal.repository.MapRepository;
import com.mahopon.cs2_smokes.map.internal.service.IMapService;

@Service("MapService")
public class MapService implements IMapService {

    private final MapRepository mapRepository;
    private final LocationRepository locationRepository;

    public MapService(MapRepository mapRepository, LocationRepository locationRepository) {
        this.mapRepository = mapRepository;
        this.locationRepository = locationRepository;
    }

    @Override
    public List<Location> getLocations(long location1Id, long location2id) {
        return locationRepository.findTwoLocationIds(location1Id, location2id);
    }

    @Override
    public List<Map> getAllMaps() {
        return new ArrayList<>();
    }

    @Override
    public Map getMap(int mapId) {
        return new Map();
    }
}