package com.gourav.voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gourav.voting.model.Candidate;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
}