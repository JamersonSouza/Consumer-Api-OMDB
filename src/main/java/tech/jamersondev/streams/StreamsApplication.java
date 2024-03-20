package tech.jamersondev.streams;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tech.jamersondev.streams.domain.Serie;
import tech.jamersondev.streams.domain.SerieEpisodio;
import tech.jamersondev.streams.services.ConsumerAPI;
import tech.jamersondev.streams.util.ConvertData;

import java.io.IOException;

@SpringBootApplication
public class StreamsApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(StreamsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ConsumerAPI api = new ConsumerAPI();
		String data = api.getData("https://www.omdbapi.com/?apikey=327d093&t=supernatural&Season=15&y=2005");
		//String data1 = api.getData("https://coffee.alexflipnote.dev/random.json");
		//System.out.println(data1);
		ConvertData convert = new ConvertData();
		Serie data2 = convert.getData(data, Serie.class);
		System.out.println(data2);
		getEpisode(api, convert);
	}

	private static void getEpisode(ConsumerAPI api, ConvertData convert) throws IOException, InterruptedException {
		String dataEpisode = api.getData("https://www.omdbapi.com/?apikey=327d093&t=supernatural&Season=15&episode=1&y=2005");
		SerieEpisodio data = convert.getData(dataEpisode, SerieEpisodio.class);
		System.out.println(data);
	}
}
