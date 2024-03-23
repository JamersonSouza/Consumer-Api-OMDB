package tech.jamersondev.streams;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tech.jamersondev.streams.domain.SerieEpisodio;
import tech.jamersondev.streams.domain.SerieSeasons;
import tech.jamersondev.streams.main.Main;
import tech.jamersondev.streams.services.ConsumerAPI;
import tech.jamersondev.streams.util.ConvertData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class StreamsApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(StreamsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Main main = new Main();
		main.findSeries();
		main.getSeasons(15);
		main.getEpisode();
	}




}
