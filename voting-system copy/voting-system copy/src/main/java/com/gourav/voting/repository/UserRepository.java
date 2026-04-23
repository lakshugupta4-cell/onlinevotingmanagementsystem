package com.gourav.voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gourav.voting.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}