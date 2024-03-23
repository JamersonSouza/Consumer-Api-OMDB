package tech.jamersondev.streams.domain;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Serie(@JsonAlias("Title") String title,
                    @JsonProperty("Released") String lançamento,
                    @JsonAlias("imdbRating") String avaliacao) {
}
