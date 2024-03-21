package tech.jamersondev.streams.main;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import tech.jamersondev.streams.services.ConsumerAPI;

import java.util.Scanner;

public class Main {

        @Value("${key.omdb}")
        private String valueApiKey;
        private final String URLOMDB = "https://www.omdbapi.com/?apikey=";
        private Scanner scan = new Scanner(System.in);
        private ConsumerAPI consumerAPI = new ConsumerAPI();
        public void showMenu(){
            System.out.println("Digite o nome da série ou filme");
            String name = scan.nextLine();
            System.out.println("Digite o ano de lançamento");
            int year = scan.nextInt();
            String urlRequest = URLOMDB+valueApiKey+"&t="+name.replace(" ", "+")+"&y="+year;
        }

}
