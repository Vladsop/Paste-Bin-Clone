package com.example.PasteBinClone.paste;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Configuration
public class PasteConfig {

    @Bean
    CommandLineRunner commandLineRunner(PasteRepository PasteRepository ) {
        return args -> {
            Paste firstpaste = new Paste(
                    "Vlad",
                    "Spring Boot Aplication with Mysql Database",
                    "This is a demo application developed with Spring Boot Java based framework and MySQL database." +
                            " Frontend written in Javascript with Bootstrap CSS framework.",
                    LocalDateTime.now(ZoneId.of("Europe/Bucharest"))
            );
            Paste secondpaste = new Paste(
                    "Vlad",
                    "New Paste",
                    "New Paste body text.",
                    LocalDateTime.now(ZoneId.of("Europe/Bucharest"))
            );
           PasteRepository.saveAll(
                    List.of(firstpaste, secondpaste)
            );
        };
    }

    @EnableWebMvc
    public class WebConfig implements WebMvcConfigurer {

        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST","PUT", "DELETE");
        }
    }
}
