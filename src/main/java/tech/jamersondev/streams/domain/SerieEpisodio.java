package tech.jamersondev.streams.domain;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SerieEpisodio(@JsonProperty("Title") String title,
                            @JsonProperty("episode") Integer number,
                            @JsonAlias("Year") Integer ano,
                            @JsonAlias("Runtime") String duracao,
                            @JsonProperty("Language") String linguagem,
                            @JsonProperty("imdbRating") String avaliacao) {
}
