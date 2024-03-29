package com.aqualen.vsu.controllers.tournament;

import com.aqualen.vsu.dto.ParticipantResponse;
import com.aqualen.vsu.dto.TournamentForParticipant;
import com.aqualen.vsu.log.SimpleLog;
import com.aqualen.vsu.services.ParticipantsService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tournament/participant")
@AllArgsConstructor
public class TournamentParticipantsController {
    private final ParticipantsService participantsService;

    @SimpleLog
    @PostMapping
    @ApiOperation("Method for adding user to tournament")
    public void addParticipant(@RequestParam long tournamentId){
        participantsService.addParticipant(tournamentId);
    }

    @SimpleLog
    @DeleteMapping
    @ApiOperation("Method for deleting user from tournament")
    public void deleteParticipant(@RequestParam long tournamentId){
        participantsService.deleteParticipantFromTournament(tournamentId);
    }

    @SimpleLog
    @GetMapping("all")
    @ApiOperation("Method for getting all participants with their tasks to rate")
    public ResponseEntity<List<ParticipantResponse>> getAllParticipants(@RequestParam int tournamentId){
        return ResponseEntity.ok(participantsService.getAllParticipants(tournamentId));
    }

    @SimpleLog
    @GetMapping
    @ApiOperation("Method for getting all tournaments for participant")
    public ResponseEntity<List<TournamentForParticipant>> getTournaments(){
        return ResponseEntity.ok(participantsService.getTournaments());
    }

    @SimpleLog
    @PutMapping
    public void updateTask(@RequestParam long tournamentId, @RequestParam String task){
        participantsService.updateTask(tournamentId, task);
    }

    @SimpleLog
    @PutMapping("grade")
    public void gradeParticipants(@RequestParam long tournamentId, @RequestBody List<ParticipantResponse> participants){
        participantsService.gradeParticipants(tournamentId, participants);
    }
}
