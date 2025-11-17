package org.example.conferenceservice;

import org.example.conferenceservice.entities.Conference;
import org.example.conferenceservice.entities.Review;
import org.example.conferenceservice.repositories.ConferenceRepo;
import org.example.conferenceservice.repositories.ReviewRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Date;

import static org.example.conferenceservice.enums.Type.ACADEMIC;
import static org.example.conferenceservice.enums.Type.COMMERCIAL;

@SpringBootApplication
@EnableDiscoveryClient
public class ConferenceServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConferenceServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner seedData(ConferenceRepo repo, ReviewRepo reviewRepo) {
        return args -> {
            Conference conference1 = new Conference().builder()
                            .titre("Spring Boot Conference")
                            .type(ACADEMIC)
                            .date(java.sql.Date.valueOf(LocalDate.now().plusDays(30)))
                            .duree(3)
                            .nbrinscripts(0)
                            .score(0.0)
                            .build();
            Conference conference2 = new Conference().builder()
                    .titre("Andrew tate  Conference")
                    .type(COMMERCIAL)
                    .date(java.sql.Date.valueOf(LocalDate.now().plusDays(30)))
                    .duree(52)
                    .nbrinscripts(3)
                    .score(12.0)
                    .build();

            Conference conference3 = new Conference().builder()
                    .titre("Tristan tate  Conference")
                    .type(COMMERCIAL)
                    .date(java.sql.Date.valueOf(LocalDate.now().plusDays(30)))
                    .duree(5)
                    .nbrinscripts(64)
                    .score(112.0)
                    .build();

            Review review1 = new Review().builder()
                            .datereview(new Date("2023/01/01"))
                            .score(5)
                            .text("Great conference!")
                            .conference(conference1)
                            .build();

            Review review2 = new Review().builder()
                    .datereview(new Date("2023/02/15"))
                    .score(4)
                    .text("Very informative sessions.")
                    .conference(conference1)
                    .build();

            Review review3 = new Review().builder()
                    .datereview(new Date("2023/03/10"))
                    .score(3)
                    .text("Good, but could be better.")
                    .conference(conference2)
                    .build();

            Review review4 = new Review().builder()
                    .datereview(new Date("2023/04/05"))
                    .score(5)
                    .text("Outstanding speakers!")
                    .conference(conference3)
                    .build();

            Review review5 = new Review().builder()
                    .datereview(new Date("2023/05/20"))
                    .score(2)
                    .text("Logistics need improvement.")
                    .conference(conference2)
                    .build();


            repo.save(conference3);
            repo.save(conference2);
            repo.save(conference1);
            reviewRepo.save(review1);
            reviewRepo.save(review2);
            reviewRepo.save(review3);
            reviewRepo.save(review4);
            reviewRepo.save(review5);

        };
    }
}
