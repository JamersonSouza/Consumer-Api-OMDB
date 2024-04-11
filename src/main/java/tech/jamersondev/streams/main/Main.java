package tech.jamersondev.streams.main;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import tech.jamersondev.streams.domain.Epsodio;
import tech.jamersondev.streams.domain.Serie;
import tech.jamersondev.streams.domain.SerieEpisodio;
import tech.jamersondev.streams.domain.SerieSeasons;
import tech.jamersondev.streams.services.ConsumerAPI;
import tech.jamersondev.streams.util.ConvertData;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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
            System.out.println("Digite o nome da série");
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
        System.out.println("==== ALL EPISODIOS THE SEASON: " + numberSession);
        //seasons.forEach(s -> s.episodios().forEach(e -> System.out.println(e.title())));
        //getTop5EpsodiosBySeason(seasons);
        //getEpsidiosBySeason(seasons);
        getEpsidiosByDate(seasons);
    }

    private static void getEpsidiosBySeason(List<SerieSeasons> seasons) {
        List<Epsodio> epsodios = seasons.stream()
                .flatMap(s -> s.episodios().stream()
                        .filter(e ->!e.avaliacao().equalsIgnoreCase("N/A"))
                        .map(e -> new Epsodio(s.number(), e))
                        .sorted(Comparator.comparing(Epsodio::getAvaliacao).reversed()))
                .toList();
        epsodios.forEach(System.out::println);
    }

    private void getEpsidiosByDate(List<SerieSeasons> seasons){
        System.out.println("A partir de qual ano você quer ver os Epsodios? ");
        int year = scan.nextInt();
        scan.nextLine();
        LocalDate findDate = LocalDate.of(year, 1,1);

        List<Epsodio> epsodios = seasons.stream()
                .flatMap(s -> s.episodios().stream()
                        .filter(e -> !e.avaliacao().equalsIgnoreCase("N/A"))
                        .peek(e -> System.out.println("Filtrando por epsódios com avaliação" + e))
                        .map(e -> new Epsodio(s.number(), e))
                        .sorted(Comparator.comparing(Epsodio::getAvaliacao).reversed()))
                        .peek(e -> System.out.println("Ordenando pela maior avaliação" + e))
                .toList();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyy");
        epsodios.stream().filter(e -> e.getDataLancamento() != null &&
                e.getDataLancamento().isAfter(findDate))
                .forEach(e -> System.out.println(
                        "Temporada: " + e.getNumberSeason()+ "\n" +
                                " Nome: " + e.getTitle() + "\n" +
                                " Episódio número: " + e.getNumberEp() + "\n" +
                                " Data Lançamento: " + e.getDataLancamento().format(formatter)
                ));

    }

    private static void getTop5EpsodiosBySeason(List<SerieSeasons> seasons) {
        List<SerieEpisodio> dadosEpsodios = seasons
                .stream().flatMap(t -> t.episodios().stream())
                //.collect(Collectors.toList());
                .toList();//list imutável
        //Top 5 ep ordenanando do maior para o menor com base na comparação de avaliação
        dadosEpsodios.stream()
                .filter(e -> !e.avaliacao().equalsIgnoreCase("N/A"))
                .sorted(Comparator.comparing(SerieEpisodio::avaliacao)
                .reversed()
        ).limit(5).forEach(System.out::println);
    }

    public void getEpisode() throws IOException, InterruptedException {
        String dataEpisode = consumerAPI.getData(URLOMDB+VALUEAPIKEY+"&t="+serieSelected.replace(" ", "+")+"&Season=15&episode=1&y="+yearSelected);
        SerieEpisodio data = conversor.getData(dataEpisode, SerieEpisodio.class);
        System.out.println(data);
    }

}
