package com.gourav.voting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gourav.voting.model.*;
import com.gourav.voting.repository.*;

@Service
public class VotingService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private CandidateRepository candidateRepo;

    public String vote(Long userId, Long candidateId) {

        User user = userRepo.findById(userId).orElse(null);

        if (user == null) return "User not found";

        if (user.isHasVoted()) return "Already voted!";

        Candidate c = candidateRepo.findById(candidateId).orElse(null);

        if (c == null) return "Candidate not found";

        c.setVotes(c.getVotes() + 1);
        user.setHasVoted(true);

        candidateRepo.save(c);
        userRepo.save(user);

        return "Vote successful!";
    }
}