package com.mahopon.cs2_smokes.map.internal.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mahopon.cs2_smokes.map.api.model.Map;
import com.mahopon.cs2_smokes.map.internal.service.IMapService;

@RestController
@RequestMapping("/map")
public class MapController {
    private final IMapService mapService;

    public MapController(IMapService mapService) {
        this.mapService = mapService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Map>> getAllMaps() {
        List<Map> mapList = mapService.getAllMaps();
        return ResponseEntity.ok(mapList);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Map> getMap(@PathVariable("id") int mapId)  {
        Map map = mapService.getMap(mapId);
        return ResponseEntity.ok(map);
    }
    
}