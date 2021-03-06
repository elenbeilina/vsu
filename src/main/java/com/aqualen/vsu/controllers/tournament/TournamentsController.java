package com.aqualen.vsu.controllers.tournament;

import com.aqualen.vsu.log.SimpleLog;
import com.aqualen.vsu.entity.Tournament;
import com.aqualen.vsu.services.TournamentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tournaments")
@RequiredArgsConstructor
public class TournamentsController {

    private final TournamentsService tournamentsService;

    @SimpleLog
    @GetMapping
    public ResponseEntity<List<Tournament>> get() {
        return ResponseEntity.ok(tournamentsService.getAll());
    }

    @SimpleLog
    @GetMapping("sponsor")
    public ResponseEntity<List<Tournament>> getForSponsor(@RequestParam(required = false) Long sponsorId) {
        return ResponseEntity.ok(tournamentsService.getTournamentsForSponsor(sponsorId));
    }
}
