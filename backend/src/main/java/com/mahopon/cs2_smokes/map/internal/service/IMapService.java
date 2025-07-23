package com.mahopon.cs2_smokes.map.internal.service;

import java.util.List;

import com.mahopon.cs2_smokes.map.api.model.Map;
import com.mahopon.cs2_smokes.map.api.service.IMapAPI;

public interface IMapService extends IMapAPI {
    List<Map> getAllMaps();
}
