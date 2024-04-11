package tech.jamersondev.streams.domain;

import java.time.LocalDate;

public class Epsodio {
    private Integer numberSeason;
    private String title;
    private Integer numberEp;
    private Double avaliacao;
    private LocalDate dataLancamento;

    public Epsodio(Integer numberSeason, SerieEpisodio episodio) {
        this.numberSeason = numberSeason;
        this.title = episodio.title();
        try {
            this.avaliacao = Double.valueOf(episodio.avaliacao());
        }catch (NumberFormatException e){
            this.avaliacao = 0.0;
        }
        this.dataLancamento = LocalDate.parse(episodio.dataLancamento());
        this.numberEp = episodio.numberEp();
    }

    public Integer getNumberSeason() {
        return numberSeason;
    }

    public void setNumberSeason(Integer numberSeason) {
        this.numberSeason = numberSeason;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    public Double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public Integer getNumberEp() {
        return numberEp;
    }

    public void setNumberEp(Integer numberEp) {
        this.numberEp = numberEp;
    }

    @Override
    public String toString() {
        return
                "numberSeason=" + numberSeason +
                ", title='" + title + '\'' +
                ", numberEp=" + numberEp +
                ", avaliacao=" + avaliacao +
                ", dataLancamento=" + dataLancamento;

    }
}
