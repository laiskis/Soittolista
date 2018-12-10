package com.example.Musiclist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.Musiclist.domain.User;
import com.example.Musiclist.domain.Song;
import com.example.Musiclist.domain.SongRepository;
import com.example.Musiclist.domain.UserRepository;

@SpringBootApplication
public class MusiclistApplication {
	private static final Logger log = LoggerFactory.getLogger(MusiclistApplication.class);
	
	//a1600507
	public static void main(String[] args) {
		SpringApplication.run(MusiclistApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner songDemo(SongRepository songrepo, UserRepository userrepo) {
		return (args) -> {
			
			 /*
             * artist, songname, length, link
             * string, string, double, string
             */
			
			//tallennetaan demo data
			log.info("save songs");
			songrepo.save(new Song("Eminem", "Without Me", 4.59, "YVkUvmDQ3HY"));
			songrepo.save(new Song("Smash Mouth ", "All Star", 3.56, "L_jWHffIx5E"));
			songrepo.save(new Song("Rick Astley", "Never Gonna Give You Up", 3.32, "dQw4w9WgXcQ"));
			songrepo.save(new Song("Michael Jackson", "Beat It", 4.58, "oRdxUFDoQe0"));
			songrepo.save(new Song("Dave Rodgers", "Deja vu", 4.24, "dv13gl0a-FA"));
			
			//luodaan kaksi demo käyttäjää, käyttäjä ja admini
			User userN = new User("user", "$2a$10$ovXO51tKeLLkWIxZW2jX.e.EJ6UsHh09MrA8RcozWx9DBMbBC3xyS", "user.normal@hotmail.com", "USER");
			User userA = new User("admin", "$2a$10$3ss4HguyPHCoHAgajgaPPu5w76hJL7si5N1UBczytNL7C59zeHmjC", "user.admin@hotmail.com", "ADMIN");
			userrepo.save(userN);
			userrepo.save(userA);
			
			//näytetään demo data consolessa
			log.info("show all songs");
			for (Song song : songrepo.findAll()) {
				log.info(song.toString());
			}
		};
		
	}
}
