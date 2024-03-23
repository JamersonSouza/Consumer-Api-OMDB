package tech.jamersondev.streams.main;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import tech.jamersondev.streams.domain.Serie;
import tech.jamersondev.streams.domain.SerieEpisodio;
import tech.jamersondev.streams.domain.SerieSeasons;
import tech.jamersondev.streams.services.ConsumerAPI;
import tech.jamersondev.streams.util.ConvertData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

        //@Value("${key.omdb}")
        private static final String VALUEAPIKEY = "327d093";
        private static final String URLOMDB = "https://www.omdbapi.com/?apikey=";
        private Scanner scan = new Scanner(System.in);
        private ConsumerAPI consumerAPI = new ConsumerAPI();
        private ConvertData conversor = new ConvertData();
        private String serieSelected = "";
        private int yearSelected;
        public void findSeries() throws IOException, InterruptedException {
            System.out.println("Digite o nome da série ou filme");
            String name = scan.nextLine();
            serieSelected = name;
            System.out.println("Digite o ano de lançamento");
            int year = scan.nextInt();
            yearSelected = year;
            String urlRequest = URLOMDB+VALUEAPIKEY+"&t="+name.replace(" ", "+")+"&y="+year;
            String data = consumerAPI.getData(urlRequest);
            Serie serie = conversor.getData(data, Serie.class);
            System.out.println(serie);
        }

    public void getSeasons(Integer numberSession) throws IOException, InterruptedException {
        List<SerieSeasons> seasons = new ArrayList<>();
        for(int i = 1; i <= numberSession; i++){
            String dataEpisode = consumerAPI.getData(URLOMDB+VALUEAPIKEY+"&t="+serieSelected.replace(" ", "+")+"&Season="+i+"&y="+yearSelected);
            SerieSeasons data = conversor.getData(dataEpisode, SerieSeasons.class);
            seasons.add(data);
        }
       // seasons.forEach(System.out::println);
        System.out.println("==== ALL NAME EPISODIOS THE SEASON: " + numberSession);
        seasons.forEach(s -> s.episodios().forEach(e -> System.out.println(e.title())));
    }

    public void getEpisode() throws IOException, InterruptedException {
        String dataEpisode = consumerAPI.getData(URLOMDB+VALUEAPIKEY+"&t="+serieSelected.replace(" ", "+")+"&Season=15&episode=1&y="+yearSelected);
        SerieEpisodio data = conversor.getData(dataEpisode, SerieEpisodio.class);
        System.out.println(data);
    }

}
