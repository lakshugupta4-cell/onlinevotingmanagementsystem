package com.gourav.voting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.gourav.voting.model.*;
import com.gourav.voting.repository.*;
import com.gourav.voting.service.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class VotingController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private CandidateRepository candidateRepo;

    @Autowired
    private VotingService votingService;

    // REGISTER USER
    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userRepo.save(user);
    }

    // ADD CANDIDATE
    @PostMapping("/candidate")
    public Candidate addCandidate(@RequestBody Candidate c) {
        return candidateRepo.save(c);
    }

    // GET ALL CANDIDATES
    @GetMapping("/candidates")
    public List<Candidate> getAll() {
        return candidateRepo.findAll();
    }

    // VOTE
    @PostMapping("/vote")
    public String vote(@RequestParam Long userId,
                       @RequestParam Long candidateId) {
        return votingService.vote(userId, candidateId);
    }

    // GET WINNER
@GetMapping("/result")
public Candidate getWinner() {

    List<Candidate> list = candidateRepo.findAll();

    Candidate winner = null;

    for (Candidate c : list) {
        if (winner == null || c.getVotes() > winner.getVotes()) {
            winner = c;
        }
    }

    return winner;
}
}