package tech.jamersondev.streams.domain;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SerieSeasons(@JsonAlias("Season") Integer number,
                           @JsonProperty("Episodes") List<SerieEpisodio> episodios) {
}
