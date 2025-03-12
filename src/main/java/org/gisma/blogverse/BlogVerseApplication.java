package org.gisma.blogverse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing // Enables automatic date fields
public class BlogVerseApplication {

    public static void main(String[] args) {

        SpringApplication.run(BlogVerseApplication.class, args);
    }

}
