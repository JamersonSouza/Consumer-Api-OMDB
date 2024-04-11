package tech.jamersondev.streams.domain;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SerieEpisodio(@JsonProperty("Title") String title,
                            @JsonProperty("Year") Integer ano,
                            @JsonProperty("Episode") Integer numberEp,
                            @JsonProperty("imdbRating") String avaliacao,
                            @JsonProperty("Released") String dataLancamento) {
}
