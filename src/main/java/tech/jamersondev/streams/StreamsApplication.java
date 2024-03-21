package tech.jamersondev.streams;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tech.jamersondev.streams.domain.Serie;
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
		/*ConsumerAPI api = new ConsumerAPI();
		String data = api.getData("https://www.omdbapi.com/?apikey=327d093&t=supernatural&Season=15&y=2005");
		//String data1 = api.getData("https://coffee.alexflipnote.dev/random.json");
		//System.out.println(data1);
		ConvertData convert = new ConvertData();
		Serie data2 = convert.getData(data, Serie.class);
		System.out.println(data2);
		getEpisode(api, convert);
		getSeasons(api, convert, data2.totalSeasons());*/
		Main main = new Main();
		main.showMenu();
	}

	private static void getSeasons(ConsumerAPI api, ConvertData convert, Integer totalSeasons) throws IOException, InterruptedException {
		List<SerieSeasons> seasons = new ArrayList<>();
		for(int i = 0; i <= totalSeasons; i++){
			String dataEpisode = api.getData("https://www.omdbapi.com/?apikey=327d093&t=supernatural&Season="+i+"&y=2005");
			SerieSeasons data = convert.getData(dataEpisode, SerieSeasons.class);
			seasons.add(data);
		}
		seasons.forEach(System.out::println);
	}

	private static void getEpisode(ConsumerAPI api, ConvertData convert) throws IOException, InterruptedException {
		String dataEpisode = api.getData("https://www.omdbapi.com/?apikey=327d093&t=supernatural&Season=15&episode=1&y=2005");
		SerieEpisodio data = convert.getData(dataEpisode, SerieEpisodio.class);
		System.out.println(data);
	}
}
