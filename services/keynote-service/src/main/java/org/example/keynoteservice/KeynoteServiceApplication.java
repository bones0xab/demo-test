package org.example.keynoteservice;

import jakarta.ws.rs.BeanParam;
import org.example.keynoteservice.entities.Keynote;
import org.example.keynoteservice.repositories.KeynoteRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class KeynoteServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(KeynoteServiceApplication.class, args);
    }


    @Bean
    CommandLineRunner seedData(KeynoteRepo keynoteRepo) {
        return args -> {
                Keynote keynote1 = new Keynote().builder()
                        .nom("Keynote 1")
                        .prenom("Speaker 1")
                        .email("keynote1@gmail.com")
                        .fonction("Developer")
                        .build();

            Keynote keynote2 = new Keynote().builder()
                    .nom("Keynote 2")
                    .prenom("Speaker 2")
                    .email("keynote2@gmail.com")
                    .fonction("Architect")
                    .build();

            Keynote keynote3 = new Keynote().builder()
                    .nom("Keynote 3")
                    .prenom("Speaker 3")
                    .email("keynote3@gmail.com")
                    .fonction("Team Lead")
                    .build();

            Keynote keynote4 = new Keynote().builder()
                    .nom("Keynote 4")
                    .prenom("Speaker 4")
                    .email("keynote4@gmail.com")
                    .fonction("Evangelist")
                    .build();

            Keynote keynote5 = new Keynote().builder()
                    .nom("Keynote 5")
                    .prenom("Speaker 5")
                    .email("keynote5@gmail.com")
                    .fonction("Consultant")
                    .build();


            keynoteRepo.save(keynote1);
            keynoteRepo.save(keynote2);
            keynoteRepo.save(keynote3);
            keynoteRepo.save(keynote4);
            keynoteRepo.save(keynote5);
        };
    }

}
