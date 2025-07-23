package com.mahopon.cs2_smokes.lineup.internal.controller;

import com.mahopon.cs2_smokes.lineup.internal.model.Lineup;
import com.mahopon.cs2_smokes.lineup.internal.model.dto.NewLineupRequestDTO;
import com.mahopon.cs2_smokes.lineup.internal.service.ILineupService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/lineup")
public class LineupController {

    private final ILineupService lineupService;

    public LineupController(ILineupService lineupService) {
        this.lineupService = lineupService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lineup> getLineup(@PathVariable long lineupId) {
        Lineup lineup = lineupService.getLineup(lineupId);
        return ResponseEntity.ok(lineup);
    }

    @GetMapping("/map/{id}")
    public ResponseEntity<List<Lineup>> getLineupByMap(@PathVariable int mapId) {
        List<Lineup> lineups = lineupService.getLineupByMap(mapId);
        return ResponseEntity.ok(lineups);
    }

    @PostMapping("/lineup")
    public ResponseEntity<Void> postLineup(@Valid @ModelAttribute NewLineupRequestDTO lineup) throws Exception {
        lineupService.createLineup(lineup);
        return ResponseEntity.ok().build();
    }
}
