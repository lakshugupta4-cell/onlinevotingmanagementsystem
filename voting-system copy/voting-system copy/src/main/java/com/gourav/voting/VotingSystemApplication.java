package com.gourav.voting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import com.gourav.voting.repository.UserRepository;
import com.gourav.voting.repository.CandidateRepository;

@SpringBootApplication(exclude = {
    org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration.class,
    org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration.class
})
public class VotingSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(VotingSystemApplication.class, args);
    }

    // 🔥 AUTO CLEAR DATABASE ON START (only if database is configured)
    @Bean
    @ConditionalOnProperty(name = "spring.datasource.url", matchIfMissing = false)
    CommandLineRunner run(UserRepository userRepo, CandidateRepository candidateRepo) {
        return args -> {
            userRepo.deleteAll();
            candidateRepo.deleteAll();
            System.out.println("Database cleared on startup");
        };
    }
}