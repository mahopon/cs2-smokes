package com.mahopon.cs2_smokes.map.api.service;

import com.mahopon.cs2_smokes.map.api.model.Location;
import com.mahopon.cs2_smokes.map.api.model.Map;

import java.util.List;

public interface IMapAPI {
    Map getMap(int mapId);
    List<Location> getLocations(long location1Id, long location2Id);
}
